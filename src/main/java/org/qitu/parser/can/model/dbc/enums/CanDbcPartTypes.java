package org.qitu.parser.can.model.dbc.enums;

/**
 * dbc 中的数据项类型
 *
 * @author zoudingyun
 * @since 2024/7/5 11:13
 */
public enum CanDbcPartTypes {

    /**
     * 版本
     * <br>
     * version
     * */
    VERSION ("VERSION"),

    /**
     * 新节点
     * <br>
     * New Symbol
     * */
    NEW_SYMBOLS ("NS_"),

    /**
     * 位计时器（波特率）
     * <br>
     * Bit Timing
     * */
    BIT_TIMING ("BS_:"),

    /**
     * 网络节点
     * <br>
     * Node
     * */
    NODES ("BU_:"),

    /**
     * 值表
     * <br>
     * Value Tables
     * */
    VALUE_TABLE ("VAL_TABLE_"),

    /**
     * 消息
     * <br>
     * Message
     * */
    MESSAGE ("BO_"),

    /**
     * 信号
     * <br>
     * Signal
     * */
    SIGNAL ("SG_"),

    /**
     * 消息发送器
     * <br>
     * Message Transmitter
     * */
    MESSAGE_TRANSMITTER ("BO_TX_BU_"),

    /**
     * 描述（信号值描述或环境变量描述）
     * <br>
     * Descriptions (value_descriptions_for_signal | value_descriptions_for_env_var)
     * */
    VALUE_DESCRIPTIONS ("VAL_"),

    /**
     * 环境变量
     * <br>
     * Environment Variable
     * */
    ENVIRONMENT_VARIABLE ("EV_"),

    /**
     * 信号类型
     * <br>
     * Environment Variable
     * */
    SIGNAL_TYPE ("SGTYPE_"),

    /**
     * 信号组
     * <br>
     * Signal group
     * */
    SIGNAL_GROUPS ("SIG_GROUP_"),

    /**
     * 备注
     * <br>
     * Comment
     * */
    COMMENT ("CM_"),

    /**
     * 自定义属性
     * <br>
     * Attribute Definitions
     * */
    ATTRIBUTE_DEFINITION ("BA_DEF_"),

    /**
     * 自定义属性值
     * <br>
     * Attribute Values
     * */
    ATTRIBUTE_VALUES ("BA_"),

    /**
     * 未知
     * <br>
     * Unknown
     * */
    UNKNOWN ("UNKNOWN");

    public final String name;

    CanDbcPartTypes(String name) {
        this.name = name;
    }

    public static CanDbcPartTypes fromValue(String value) {
        for (CanDbcPartTypes type : CanDbcPartTypes.values()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @Override
    public String toString() {
        return this.name;
    }


}
