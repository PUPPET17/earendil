package fun.puppet17.earendil.domain.receive.service.logic.impl;

import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;
import fun.puppet17.earendil.domain.receive.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
@Service("unsubscribe")
public class UnsubscribeFilter implements LogicFilter {

    private Logger logger = LoggerFactory.getLogger(UnsubscribeFilter.class);

    @Override
    public String filter(BehaviorMatter request) {
        logger.info("用户{}已取消关注", request.getOpenId());
        return null;
    }

}
