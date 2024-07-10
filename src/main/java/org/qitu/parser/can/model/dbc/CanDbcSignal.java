package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;
import org.qitu.parser.can.model.dbc.enums.CanDbcSignalMultiplexerType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信号 （Signal）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:20
 */
public class CanDbcSignal extends CanDbcPart {

    public CanDbcSignal() {
        setKeyword(CanDbcPartType.SIGNAL.name);
    }

    /**
     * 信号名（SignalName）
     * */
    private String signalName;

    /**
     * 多路复用器类型（multiplexerType）
     * */
    private CanDbcSignalMultiplexerType multiplexerIndicator;



    /**
     * 多路复用器信号 （multiplexed signals）
     * <p>
     * 当本信号为多路复用器开关信号时，此项包含属于它的多路复用器信号,key为信号匹配的开关值
     * </p>
     * <p>
     * If this signal is a multiplexer signal,its multiplexed signals will be here
     * </p>
     * */
    private Map<String,List<CanDbcSignal>> multiplexedSignalMap;

    /**
     * 信号起始位 （start_bit）
     * <p>
     * start_bit值指定信号在的数据字段中的位置,(intel小端序给出的是低位地址，Motorola大端序给出的是高位地址)<br>
     * 以锯齿形方式对比特进行计数。起始位范围：[0 ~（8*message_size-1）]
     * </p>
     * <p>
     * The start_bit value specifies the position of the signal within the data field of the
     * frame. For signals with byte order Intel (little endian) the position of the least significant bit is given.
     * For signals with byte order Motorola (big endian) the position of the most significant bit is given.
     * The bits are counted in a sawtooth manner.
     * The start_bit has to be in the range of 0 to (8 * message_size - 1).
     * </p>
     * */
    private Integer startBit;

    /**
     * 信号长度（signal size）
     * <p>
     * The signal_size specifies the size of the signal in bits
     * </p>
     * */
    private Integer signalSize;

    /**
     * 数据模式 (byte order mode)
     * <p>
     * true: (Motorola)big endian <br>
     * false: (intel)little endian
     * </p>
     * */
    private Boolean byteOrder;

    /**
     * 数据类型 (signed or unsigned)
     * <p>
     * true: unsigned <br>
     * false: signed
     * </p>
     * */
    private Boolean valueType;

    /**
     * 系数 (coefficient)
     * <p>
     * real_value =  can_value × coefficient
     * </p>
     * */
    private BigDecimal factor;

    /**
     * 偏移量 (offset)
     * <p>
     * real_value =  can_value + offset
     * </p>
     * */
    private BigDecimal offset;

    /**
     * 最小值 (min value)
     * */
    private BigDecimal minValue;

    /**
     * 最大值 (max value)
     * */
    private BigDecimal maxValue;

    /**
     * 单位 (unit desc)
     * */
    private String unit;

    /**
     * 接收器网络节点名 (receiver)
     * <p>
     * default：Vector__XXX
     * </p>
     * */
    private CanDbcNode receiver;


    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }

    public CanDbcSignalMultiplexerType getMultiplexerIndicator() {
        return multiplexerIndicator;
    }

    public void setMultiplexerIndicator(CanDbcSignalMultiplexerType multiplexerIndicator) {
        this.multiplexerIndicator = multiplexerIndicator;
    }

    public List<CanDbcSignal> getMultiplexedSignalListBySwitchValue(String switchValue) {
        if (multiplexedSignalMap == null) {
            multiplexedSignalMap = new HashMap<>();
        }
        return multiplexedSignalMap.get(switchValue);
    }

    public void setMultiplexedSignalBySwitchValue(String switchValue, CanDbcSignal signal) {
        if (multiplexedSignalMap == null) {
            multiplexedSignalMap = new HashMap<>();
        }
        if (!multiplexedSignalMap.containsKey(switchValue)) {
            multiplexedSignalMap.put(switchValue, new ArrayList<>());
        }
        multiplexedSignalMap.get(switchValue).add(signal);
    }

    public Integer getStartBit() {
        return startBit;
    }

    public void setStartBit(Integer startBit) {
        this.startBit = startBit;
    }

    public Integer getSignalSize() {
        return signalSize;
    }

    public void setSignalSize(Integer signalSize) {
        this.signalSize = signalSize;
    }

    public Boolean getByteOrder() {
        return byteOrder;
    }

    public void setByteOrder(Boolean byteOrder) {
        this.byteOrder = byteOrder;
    }

    public Boolean getValueType() {
        return valueType;
    }

    public void setValueType(Boolean valueType) {
        this.valueType = valueType;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public BigDecimal getOffset() {
        return offset;
    }

    public void setOffset(BigDecimal offset) {
        this.offset = offset;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public CanDbcNode getReceiver() {
        return receiver;
    }

    public void setReceiver(CanDbcNode receiver) {
        this.receiver = receiver;
    }
}
