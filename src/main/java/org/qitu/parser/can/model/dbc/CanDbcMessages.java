package org.qitu.parser.can.model.dbc;

import org.qitu.parser.can.model.dbc.enums.CanDbcPartType;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息（Message）
 *
 * @author zoudingyun
 * @since 2024/7/3 11:08
 */
public class CanDbcMessages extends CanDbcPart{

    /**
     * 消息集
     * */
    private List<CanDbcMessage> messageList;

    public CanDbcMessages() {
        this.setKeyword(CanDbcPartType.MESSAGE.name);
    }

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
