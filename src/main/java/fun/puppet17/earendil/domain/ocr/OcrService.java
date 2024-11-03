package fun.puppet17.earendil.domain.ocr;

import fun.puppet17.earendil.domain.access.AccessTokenService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author xyx
 * @date 2024/11/4
 */
@Service
public class OcrService {
    
    private static final Logger logger = Logger.getLogger(OcrService.class.getName());
    private static final String SERVICE_INVOKE_URL = "https://api.weixin.qq.com/wxa/servicemarket";
    
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private RestTemplate restTemplate;
    
    public Map<String,Object> getOcrResult(String url){
        
        Map<String, Object> data = new HashMap<>();
        data.put("img_url", url );
        data.put("data_type", 3 );
        data.put("ocr_type", 4 );
        return invokeService("wx79ac3de8be320b71", "OcrAllInOne",
                data, UUID.randomUUID().toString(), false);
    }
    
    /**
     * 调用服务平台 API
     * @param service 服务 ID
     * @param api 接口名
     * @param data JSON 格式的数据
     * @param clientMsgId 调用方请求的唯一标识
     * @param async 是否异步
     * @return 返回 API 调用结果
     */
    public Map<String, Object> invokeService(String service, String api, Map<String, Object> data,
                                             String clientMsgId, boolean async) {
        // 获取 access_token
        String accessToken = accessTokenService.getAccessToken();
        if (accessToken == null) {
            throw new IllegalStateException("无法获取 access_token");
        }
        
        // 构建完整的请求 URL
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_INVOKE_URL)
                .queryParam("access_token", accessToken)
                .toUriString();
        
        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("service", service);
        requestBody.put("api", api);
        requestBody.put("data", data);
        requestBody.put("client_msg_id", clientMsgId);
        if (async) {
            requestBody.put("async", true);
        }
        
        try {
            // 发送 POST 请求并接收响应
            Map<String, Object> response = restTemplate.postForObject(url, requestBody, Map.class);
            
            if (response != null) {
                // 检查返回码和错误信息
                Integer errcode = (Integer) response.get("errcode");
                String errmsg = (String) response.get("errmsg");
                
                if (errcode != null && errcode == 0) {
                    logger.info("API 调用成功：" + response);
                    return response;
                } else {
                    logger.warning("API 调用失败，错误码：" + errcode + "，错误信息：" + errmsg);
                }
            } else {
                logger.warning("API 调用失败，响应为空");
            }
        } catch (Exception e) {
            logger.severe("调用服务平台 API 时出错：" + e.getMessage());
        }
        return null;
    }
}
