package org.qitu.parser.can.model.dbc;

/**
 * DBC文件关键字池
 * <p>
 * DBC keyword pool
 * @author zoudingyun
 * @since 2024/7/4 10:50
 */
public interface CanDbcKeywordPool {

    /**
     * 版本
     * <br>
     * version
     * */
    public static final String VERSION = "VERSION";

    /**
     * 新节点
     * <br>
     * New Symbol
     * */
    public static final String NEW_SYMBOLS = "NS_";

    /**
     * 位计时器（波特率）
     * <br>
     * Bit Timing
     * */
    public static final String BIT_TIMING = "BS_:";

    /**
     * 网络节点
     * <br>
     * Node
     * */
    public static final String NODES = "BU_:";

    /**
     * 值表
     * <br>
     * Value Tables
     * */
    public static final String VALUE_TABLE = "VAL_TABLE_";

    /**
     * 消息
     * <br>
     * Message
     * */
    public static final String MESSAGE = "BO_";

    /**
     * 信号
     * <br>
     * Signal
     * */
    public static final String SIGNAL = "SG_";

    /**
     * 消息发送器
     * <br>
     * Message Transmitter
     * */
    public static final String MESSAGE_TRANSMITTER = "BO_TX_BU_";

    /**
     * 描述（信号值描述或环境变量描述）
     * <br>
     * Descriptions (value_descriptions_for_signal | value_descriptions_for_env_var)
     * */
    public static final String VALUE_DESCRIPTIONS = "VAL_";

    /**
     * 环境变量
     * <br>
     * Environment Variable
     * */
    public static final String ENVIRONMENT_VARIABLE = "EV_";

    /**
     * 信号类型
     * <br>
     * Environment Variable
     * */
    public static final String SIGNAL_TYPE = "SGTYPE_";

    /**
     * 信号组
     * <br>
     * Signal group
     * */
    public static final String SIGNAL_GROUPS = "SIG_GROUP_";

    /**
     * 备注
     * <br>
     * Comment
     * */
    public static final String COMMENT = "CM_";

    /**
     * 自定义属性
     * <br>
     * Attribute Definitions
     * */
    public static final String ATTRIBUTE_DEFINITION = "BA_DEF_";

    /**
     * 自定义属性值
     * <br>
     * Attribute Values
     * */
    public static final String ATTRIBUTE_VALUES = "BA_";

}
