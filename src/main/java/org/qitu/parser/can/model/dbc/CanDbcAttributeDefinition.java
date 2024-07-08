package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcAttributeValueType;
import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;

import java.math.BigDecimal;
import java.util.List;

/**
 * 属性定义
 * <br>
 * 自定义的属性
 * @author zoudingyun
 * @since 2024/7/8 15:54
 */
public class CanDbcAttributeDefinition {

    /**
     * 自定义属性的标定类型
     * <br>
     * 标定是哪种数据项的自定义属性，为空则是通用属性
     * */
    private CanDbcPartType objectType;

    /**
     * 属性名称
     * */
    private String attributeName;

    /**
     * 数据类型
     * */
    private CanDbcAttributeValueType attributeValueType;

    /**
     * 最小值
     * */
    private BigDecimal minimum;

    /**
     * 最大值
     * */
    private BigDecimal maximum;

    /**
     * 默认值
     * */
    private Object defaultValue;

    /**
     * 枚举对象
     * */
    private List<String> enumValues;


    public CanDbcPartType getObjectType() {
        return objectType;
    }

    public void setObjectType(CanDbcPartType objectType) {
        this.objectType = objectType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public CanDbcAttributeValueType getAttributeValueType() {
        return attributeValueType;
    }

    public void setAttributeValueType(CanDbcAttributeValueType attributeValueType) {
        this.attributeValueType = attributeValueType;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public BigDecimal getMaximum() {
        return maximum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(List<String> enumValues) {
        this.enumValues = enumValues;
    }
}
