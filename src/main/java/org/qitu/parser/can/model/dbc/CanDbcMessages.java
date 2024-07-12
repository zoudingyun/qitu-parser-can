package org.qitu.parser.can.model.dbc;


import java.util.ArrayList;
import java.util.List;

/**
 * 消息（Message）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:08
 */
public class CanDbcMessages{

    /**
     * 消息集
     * */
    private List<CanDbcMessage> messageList;

    public List<CanDbcMessage> getMessageList() {
        if (this.messageList == null) {
            this.messageList = new ArrayList<CanDbcMessage>();
        }
        return messageList;
    }

    public void setMessageList(List<CanDbcMessage> messageList) {
        this.messageList = messageList;
    }
}
