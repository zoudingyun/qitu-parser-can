package org.qitu.parser.can.model.dbc.enums;

/**
 * 自定义属性数据类型
 *
 * @author zoudingyun
 * @since 2024/7/8 16:04
 */
public enum CanDbcAttributeValueType {

    /**
     * 整型
     * <br>
     * 需要默认值
     * <br>
     * 需要最大最小值
     * */
    INT ("INT"),

    /**
     * 16进制数据
     * <br>
     * 需要默认值
     * <br>
     * 需要最大最小值
     * */
    HEX ("HEX"),

    /**
     * 浮点数
     * <br>
     * 需要默认值
     * <br>
     * 需要最大最小值
     * */
    FLOAT ("FLOAT"),

    /**
     * 字符串
     * <br>
     * 需要默认值
     * */
    STRING ("STRING"),


    /**
     * 枚举
     * <br>
     * 需要定义枚举内容（可为空），用英文逗号隔开： "aa","bb","cc"
     * <br>
     * 需要默认值
     * */
    ENUM ("ENUM"),

    /**
     * 未知
     * <br>
     * Unknown
     * */
    UNKNOWN ("");

    public final String name;

    CanDbcAttributeValueType(String name) {
        this.name = name;
    }

    public static CanDbcAttributeValueType fromValue(String value) {
        for (CanDbcAttributeValueType type : CanDbcAttributeValueType.values()) {
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
