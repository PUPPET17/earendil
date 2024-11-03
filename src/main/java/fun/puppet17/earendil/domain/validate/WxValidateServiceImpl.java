package fun.puppet17.earendil.domain.validate;

import fun.puppet17.earendil.application.IWxValidateService;
import fun.puppet17.earendil.infrastructure.utils.sdk.SignatureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
@Service
public class WxValidateServiceImpl implements IWxValidateService {

    @Value("${wx.config.token}")
    private String token;

    @Override
    public boolean checkSign(String signature, String timestamp, String nonce) {
        return SignatureUtil.check(token, signature, timestamp, nonce);
    }

}