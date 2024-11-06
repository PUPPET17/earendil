package fun.puppet17.earendil.domain.access;

import java.util.concurrent.*;
import java.util.logging.Logger;

import fun.puppet17.earendil.infrastructure.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author PUPPET17
 */
@Service
@DependsOn({"redisConfig","appConfig"})
public class AccessTokenService {
    
    @Value("${wx.config.appid}")
    private String appId;
    @Value("${wx.config.appsecret}")
    private String appSecret;
    private static final Logger logger = Logger.getLogger(AccessTokenService.class.getName());
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private String accessToken;
    private long expiresAt;
    private final ReentrantLock lock = new ReentrantLock();
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisUtil redisUtil;
    private static ScheduledExecutorService scheduler;
    
    @PostConstruct
    public void init() {
        if (restTemplate == null) {
            throw new IllegalStateException("RestTemplate 未初始化");
        }
        logger.info("RestTemplate 已初始化");
        String tokenFromRedis = (String) redisUtil.get("wechat_access_token");
        String expiresAtStr = (String) redisUtil.get("wechat_access_token_expires_at");
        if (expiresAtStr != null) {
            expiresAt = Long.parseLong(expiresAtStr);
        }
        if (tokenFromRedis != null && System.currentTimeMillis() < expiresAt) {
            logger.info("已从 Redis 获取到未过期的 access_token.");
            long initialDelay = Math.max(0, expiresAt - System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5));
            initScheduler(initialDelay);
        } else {
            logger.info("初始化类时未找到有效的 access_token, 已刷新.");
            refreshAccessToken();
            initScheduler(0);
        }
    }
    
    private void initScheduler(long initialDelay) {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(this::refreshAccessToken, initialDelay, TimeUnit.HOURS.toMillis(1), TimeUnit.MILLISECONDS);
            logger.info("启动定时任务刷新 access_token，将在 " + initialDelay / 1000 + " 秒后开始运行.");
        }
    }
    
    /**
     * 获取 access_token
     */
    public String getAccessToken() {
        if (accessToken != null && System.currentTimeMillis() < expiresAt) {
            return accessToken;
        }
        
        lock.lock();
        try {
            if (System.currentTimeMillis() >= expiresAt) {
                refreshAccessToken();
            }
            return accessToken;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * 刷新 access_token
     */
    private void refreshAccessToken() {
        String url = UriComponentsBuilder.fromHttpUrl(ACCESS_TOKEN_URL)
                .queryParam("grant_type", "client_credential")
                .queryParam("appid", appId)
                .queryParam("secret", appSecret)
                .toUriString();
        
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            
            if (response != null && response.containsKey("access_token") && response.containsKey("expires_in")) {
                accessToken = (String) response.get("access_token");
                int expiresIn = (int) response.get("expires_in");
                expiresAt = System.currentTimeMillis() + (expiresIn - 300) * 1000L;  // 提前5分钟刷新
                
                redisUtil.set("wechat_access_token", accessToken, expiresIn - 300);
                redisUtil.set("wechat_access_token_expires_at", String.valueOf(expiresAt));
                
                logger.info("成功刷新 access_token 并更新至 Redis: " + accessToken);
            } else {
                logger.warning("获取 access_token 失败: " + response);
            }
        } catch (Exception e) {
            logger.severe("刷新 access_token 时出错: " + e.getMessage());
        }
    }
    
    /**
     * 提供一个手动刷新 access_token 的方法
     */
    public void manualRefreshAccessToken() {
        lock.lock();
        try {
            refreshAccessToken();
        } finally {
            lock.unlock();
        }
    }
}
