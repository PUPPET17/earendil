package fun.puppet17.earendil.application;


import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public interface IWxReceiveService {

    /**
     * 接收信息
     *
     * @param behaviorMatter    入参
     * @return                  出惨
     * @throws Exception        异常
     */
    String doReceive(BehaviorMatter behaviorMatter) throws Exception;

}
