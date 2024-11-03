package fun.puppet17.earendil.application;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public interface IWxValidateService {

    /**
     * 验签
     * @param signature 签名
     * @param timestamp 时间
     * @param nonce     当前
     * @return          结果
     */
    boolean checkSign(String signature, String timestamp, String nonce);

}
