package org.qitu.parser.can.model.dbc;

import cn.hutool.core.util.StrUtil;
import org.qitu.parser.can.exceptions.CanDbcInvalidParameterException;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息（Message）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:08
 */
public class CanDbcMessage {

    /**
     * 消息id （message_id）
     * */
    private Integer messageId;

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
    private String transmitter;

    /**
     * 消息信号集（the message's signal）
     * */
    private List<CanDbcSignal> signalList;

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
        if(signalList == null || StrUtil.isBlankIfStr(signalName)){
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

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
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

    public String getTransmitter() {
        return transmitter;
    }

    public void setTransmitter(String transmitter) {
        this.transmitter = transmitter;
    }

    public List<CanDbcSignal> getSignalList() {
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
}
