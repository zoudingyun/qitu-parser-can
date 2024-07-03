package org.qitu.parser.can.model.dbc.enums;

/**
 * 多路类型（multiplexer_indicator）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:49
 */
public enum CanDbcSignalMultiplexerType {

    /**
     * 普通信号（signal）
     * */
    SIGNAL("signal"),

    /**
     * 多路复用器开关（multiplexer switch）
     * <p>
     * 一个信号内只能同时存在一个 multiplexer
     * </p>
     * <p>
     * Only one signal within a
     * single message can be the multiplexer switch.
     * </p>
     * */
    MULTIPLEXER("multiplexer"),

    /**
     * 多路复用信号（multiplexed）
     * <p>
     * 如果该信号定义的复用值等于multiplexer信号的开关值，则该复用信号进行传输
     * </p>
     * <p>
     * The multiplexed signal is transferred in the message if the switch value of
     * the multiplexer signal is equal to its multiplexer_switch_value.
     * </p>
     * */
    MULTIPLEXED("multiplexed");

    public final String name;

    CanDbcSignalMultiplexerType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}