package fun.puppet17.earendil.domain.receive.service.engine;


import fun.puppet17.earendil.domain.receive.model.BehaviorMatter;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public interface Engine {

    /**
     * 过滤器
     * @param request       入参
     * @return              出参
     * @throws Exception    异常
     */
    String process(final BehaviorMatter request) throws Exception;

}
