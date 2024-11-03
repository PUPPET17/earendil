package fun.puppet17.earendil.domain.receive.service.logic.impl;

import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;
import fun.puppet17.earendil.domain.receive.service.logic.LogicFilter;
import org.springframework.stereotype.Service;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
@Service("subscribe")
public class SubscribeFilter implements LogicFilter {

    @Override
    public String filter(BehaviorMatter request) {
        return "感谢关注";
    }

}