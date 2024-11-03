package fun.puppet17.earendil.domain.receive.service.engine;

import fun.puppet17.earendil.domain.receive.service.logic.LogicFilter;
import fun.puppet17.earendil.domain.receive.service.logic.impl.SubscribeFilter;
import fun.puppet17.earendil.domain.receive.service.logic.impl.UnsubscribeFilter;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public class EngineConfig {

    @Resource
    @Qualifier("subscribe")
    private SubscribeFilter subscribeFilter;

    @Resource
    @Qualifier("unsubscribe")
    private UnsubscribeFilter unsubscribeFilter;

    protected static Map<String, Map<String, LogicFilter>> logicFilterMap = new HashMap<>();

    @PostConstruct
    public void init() {
        logicFilterMap.put("event", new HashMap<String, LogicFilter>() {
            {
                put("subscribe", subscribeFilter);
                put("unsubscribe", unsubscribeFilter);
            }
        });
    }

}
