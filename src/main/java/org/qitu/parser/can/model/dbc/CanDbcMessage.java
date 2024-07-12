package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.exceptions.CanDbcInvalidParameterException;
import org.qitu.parser.core.util.StrUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 消息（Message）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:08
 */
public class CanDbcMessage implements CanDbcAttributeCreator{

    /**
     * 消息id （message_id）
     * */
    private BigInteger messageId;

    /**
     * 消息名 （message_name）
     * */
    private String messageName;

    /**
     * 消息长度 (message_size)
     * */
    private Integer messageSize;

    /**
     * 发送器节点名 （transmitter）
     * <p>
     * 指定发送消息的节点的名称。发送器名称必须在节点中是已定义的。
     * 如果没有发送器，则必须给出字符串“Vector__XXX”在这里
     * </p>
     * <p>
     *     The transmitter name specifies the name of the node transmitting the message.
     * The sender name has to be defined in the set of node names in the node section.
     * If the massage shall have no sender, the string 'Vector__XXX' has to be given
     * here.
     * </p>
     * */
    private CanDbcNode transmitter;

    /**
     * 消息信号集（the message's signal）
     * */
    private List<CanDbcSignal> signalList;

    /**
     * 额外的发送器节点名 （extra transmitter）
     * <br>
     * 使用“BO_TX_BU_”定义的额外发送器节点
     * <br>
     * Additional transmitter nodes defined using 'BO_TX_BU_'.
     * */
    private List<CanDbcNode> extraTransmitters;


    /**
     * 自定义属性设定集
     * */
    private List<CanDbcAttributeDefinition> attributeDefinitions;

    /*=========================================================================*/
    /**
     * 添加一个信号（add signal）
     * */
    public void addSignal(CanDbcSignal signal){
        if(signalList == null){
            signalList = new ArrayList<CanDbcSignal>();
        }
        if (signal == null){
            throw new CanDbcInvalidParameterException("signal can not null.");
        }
        signalList.add(signal);
    }

    /**
     * 添加信号集（add signals）
     * */
    public void addSignals(List<CanDbcSignal> signals){
        if(signalList == null){
            signalList = new ArrayList<CanDbcSignal>();
        }
        if (signals == null || signals.isEmpty()){
            throw new CanDbcInvalidParameterException("signals can not null.");
        }else {
            for (CanDbcSignal signal : signals) {
                if (signal == null){
                    throw new CanDbcInvalidParameterException("signal can not null.");
                }
            }
        }
        signalList.addAll(signals);
    }

    /**
     * 通过信号名删除信号（delete signal by name）
     * */
    public void delSignalByName(String signalName){
        if(signalList == null || StrUtils.isBlank(signalName)){
            return;
        }
        for (CanDbcSignal signal : signalList) {
            if (signal.getSignalName().equals(signalName)){
                signalList.remove(signal);
                return;
            }
        }
    }
    /*=========================================================================*/

    public BigInteger getMessageId() {
        return messageId;
    }

    public void setMessageId(BigInteger messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public Integer getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(Integer messageSize) {
        this.messageSize = messageSize;
    }

    public CanDbcNode getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(CanDbcNode transmitter) {
        this.transmitter = transmitter;
    }

    public List<CanDbcSignal> getSignalList() {
        if (this.signalList == null){
            this.signalList = new ArrayList<>();
        }
        return signalList;
    }

    public void setSignalList(List<CanDbcSignal> signalList) {
        if (signalList == null || signalList.isEmpty()){
            throw new CanDbcInvalidParameterException("signals can not null.");
        }else {
            for (CanDbcSignal signal : signalList) {
                if (signal == null){
                    throw new CanDbcInvalidParameterException("signal can not null.");
                }
            }
        }
        this.signalList = signalList;
    }

    public List<CanDbcNode> getExtraTransmitters() {
        return extraTransmitters;
    }

    public void setExtraTransmitters(List<CanDbcNode> extraTransmitters) {
        this.extraTransmitters = extraTransmitters;
    }

    public void addExtraTransmitter(CanDbcNode canDbcTransmitter) {
        if (this.extraTransmitters == null){
            this.extraTransmitters = new ArrayList<>();
        }
        extraTransmitters.add(canDbcTransmitter);
    }

    /**
     * 添加一个自定义属性的定义
     *
     * @param attributeDefinition 定义信息
     */
    @Override
    public void addAttributeDefinition(CanDbcAttributeDefinition attributeDefinition) {
        if (this.attributeDefinitions == null){
            this.attributeDefinitions = new ArrayList<CanDbcAttributeDefinition>();
        }
        this.attributeDefinitions.add(attributeDefinition);
    }

    /**
     * 更新一个属性的定义
     *
     * @param attributeDefinition 定义信息
     */
    @Override
    public void updateAttributeDefinition(CanDbcAttributeDefinition attributeDefinition) {
        if (this.attributeDefinitions != null){
            // 使用 Iterator 进行遍历和删除
            Iterator<CanDbcAttributeDefinition> iterator = this.attributeDefinitions.iterator();
            while (iterator.hasNext()) {
                CanDbcAttributeDefinition element = iterator.next();
                if (attributeDefinition.getAttributeName().equals(element.getAttributeName())) {
                    iterator.remove();
                    this.attributeDefinitions.add(attributeDefinition);
                    break;
                }
            }
        }
    }

    /**
     * 查询一个自定义属性的定义
     *
     * @param canDbcAttributeName 属性名称
     * @return 查询到的属性定义
     */
    @Override
    public CanDbcAttributeDefinition loadAttributeDefinitionByName(String canDbcAttributeName) {
        if (this.attributeDefinitions != null){
            for (CanDbcAttributeDefinition element : this.attributeDefinitions) {
                if (canDbcAttributeName.equals(element.getAttributeName())) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * 删除一个自定义属性的定义
     *
     * @param canDbcAttributeName 属性名称
     */
    @Override
    public void delAttributeDefinitionByName(String canDbcAttributeName) {
        if (this.attributeDefinitions != null){
            // 使用 Iterator 进行遍历和删除
            Iterator<CanDbcAttributeDefinition> iterator = this.attributeDefinitions.iterator();
            while (iterator.hasNext()) {
                CanDbcAttributeDefinition element = iterator.next();
                if (canDbcAttributeName.equals(element.getAttributeName())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * 重置自定义属性的定义集
     *
     * @param attributeDefinitions 属性集
     */
    @Override
    public void setAttributeDefinitions(List<CanDbcAttributeDefinition> attributeDefinitions) {
        this.attributeDefinitions = attributeDefinitions;
    }

    /**
     * 获取自定义属性的定义集
     *
     * @return 属性集
     */
    @Override
    public List<CanDbcAttributeDefinition> getAttributeDefinitions() {
        return this.attributeDefinitions;
    }

    /**
     * 清除自定义属性的定义集
     */
    @Override
    public void removeAttributeDefinitions() {
        this.attributeDefinitions = new ArrayList<>();
    }
}
