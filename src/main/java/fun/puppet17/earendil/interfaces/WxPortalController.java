package fun.puppet17.earendil.interfaces;

import fun.puppet17.earendil.application.IWxReceiveService;
import fun.puppet17.earendil.application.IWxValidateService;
import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;
import fun.puppet17.earendil.domain.receive.model.MessageTextEntity;
import fun.puppet17.earendil.infrastructure.utils.XmlUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/17
 */
@RestController
@RequestMapping("/wx/portal/{appid}")
public class WxPortalController {
    private Logger logger = LoggerFactory.getLogger(WxPortalController.class);

    @Resource
    private IWxValidateService wxValidateService;
    @Resource
    private IWxReceiveService wxReceiveService;

    @GetMapping(produces = "text/plain;charset=utf-8")
    public String validate(@PathVariable String appid,
                           @RequestParam(value = "signature", required = false) String signature,
                           @RequestParam(value = "timestamp", required = false) String timestamp,
                           @RequestParam(value = "nonce", required = false) String nonce,
                           @RequestParam(value = "echostr", required = false) String echostr) {
        try {
            logger.info("微信公众号验签信息{}开始 [{}, {}, {}, {}]", appid, signature, timestamp, nonce, echostr);
            if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
                throw new IllegalArgumentException("请求参数非法，请核实!");
            }
            boolean check = wxValidateService.checkSign(signature, timestamp, nonce);
            logger.info("微信公众号验签信息{}完成 check：{}", appid, check);
            if (!check) {
                return null;
            }
            return echostr;
        } catch (Exception e) {
            logger.error("微信公众号验签信息{}失败 [{}, {}, {}, {}]", appid, signature, timestamp, nonce, echostr, e);
            return null;
        }
    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@PathVariable String appid,
                       @RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        try {
            logger.info("接收微信公众号信息请求{}开始 {}", openid, requestBody);
            MessageTextEntity message = XmlUtils.xmlToBean(requestBody, MessageTextEntity.class);
            BehaviorMatter behaviorMatter = new BehaviorMatter();
            behaviorMatter.setOpenId(openid);
            behaviorMatter.setFromUserName(message.getFromUserName());
            behaviorMatter.setMsgType(message.getMsgType());
            behaviorMatter.setContent(StringUtils.isBlank(message.getContent()) ? null : message.getContent().trim());
            behaviorMatter.setEvent(message.getEvent());
            behaviorMatter.setCreateTime(new Date(Long.parseLong(message.getCreateTime()) * 1000L));
            // 处理消息
            String result = wxReceiveService.doReceive(behaviorMatter);
            logger.info("接收微信公众号信息请求{}完成 {}", openid, result);
            return result;
        } catch (Exception e) {
            logger.error("接收微信公众号信息请求{}失败 {}", openid, requestBody, e);
            return "";
        }
    }

}
