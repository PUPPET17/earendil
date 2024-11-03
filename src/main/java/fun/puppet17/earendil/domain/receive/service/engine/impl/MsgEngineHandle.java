package fun.puppet17.earendil.domain.receive.service.engine.impl;

import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;
import fun.puppet17.earendil.domain.receive.model.MessageTextEntity;
import fun.puppet17.earendil.domain.receive.service.engine.EngineBase;
import fun.puppet17.earendil.domain.receive.service.logic.LogicFilter;
import fun.puppet17.earendil.infrastructure.utils.XmlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
@Service("msgEngineHandle")
public class MsgEngineHandle extends EngineBase {

    @Value("${wx.config.originalid:}")
    private String originalId;

    @Override
    public String process(BehaviorMatter request) throws Exception {
        LogicFilter router = super.router(request);
        if (null == router) {
            return null;
        }
        String resultStr = router.filter(request);
        if (StringUtils.isBlank(resultStr)) {
            return "";
        }

        //反馈信息[文本]
        MessageTextEntity res = new MessageTextEntity();
        res.setToUserName(request.getOpenId());
        res.setFromUserName(originalId);
        res.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000L));
        res.setMsgType("text");
        res.setContent(resultStr);
        return XmlUtils.beanToXml(res);
    }

}
