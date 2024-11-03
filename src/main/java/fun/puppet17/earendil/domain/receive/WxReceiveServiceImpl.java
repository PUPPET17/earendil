package fun.puppet17.earendil.domain.receive;

import fun.puppet17.earendil.application.IWxReceiveService;
import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;
import fun.puppet17.earendil.domain.receive.service.engine.Engine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
@Service
public class WxReceiveServiceImpl implements IWxReceiveService {

    @Resource(name = "msgEngineHandle")
    private Engine msgEngineHandle;

    /**
     * 接收信息
     *
     * @param behaviorMatter 入参
     * @return 出参
     * @throws Exception 异常
     */
    @Override
    public String doReceive(BehaviorMatter behaviorMatter) throws Exception {
        return msgEngineHandle.process(behaviorMatter);
    }
}
