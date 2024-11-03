package fun.puppet17.earendil.domain.receive.service.logic;

import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public interface LogicFilter {

    /**
     * Filter string.
     *
     * @param request the request
     * @return the string
     */
    String filter(BehaviorMatter request);

}
