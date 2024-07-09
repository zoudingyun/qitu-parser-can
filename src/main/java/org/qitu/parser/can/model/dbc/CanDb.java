package org.qitu.parser.can.model.dbc;

/**
 * CanDb 对象
 * <p>
 * canDb object
 * @author zoudingyun
 * @since 2024/7/5 10:59
 */
public class CanDb {

    /**
     * canDb 解析后的配置信息
     * <br>
     * Properties of canDbc file.
     * */
    private CanDbcProperties canDbcProperties;


    public CanDb(CanDbcProperties canDbcProperties){
        this.canDbcProperties = canDbcProperties;
    }

    public CanDbcProperties getCanDbcProperties() {
        return canDbcProperties;
    }
}
