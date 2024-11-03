package fun.puppet17.earendil.domain.receive.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信接收的文本消息实体
 *
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public class MessageTextEntity {

    /**
     * 消息的唯一标识符
     */
    @XStreamAlias("MsgId")
    private String msgId;
    /**
     * 发送消息的用户或公众账号的原始ID
     */
    @XStreamAlias("ToUserName")
    private String toUserName;
    /**
     * 发送消息的用户的OpenID
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间戳。
     */
    @XStreamAlias("CreateTime")
    private String createTime;
    /**
     * 消息类型，例如文本、图片、事件等。
     */
    @XStreamAlias("MsgType")
    private String msgType;
    /**
     * 消息内容。
     */
    @XStreamAlias("Content")
    private String content;
    /**
     * 事件消息的事件类型（如果消息是事件消息）。
     */
    @XStreamAlias("Event")
    private String event;
    /**
     * 与消息关联的事件键（如果适用）。
     */
    @XStreamAlias("EventKey")
    private String eventKey;

    public MessageTextEntity() {
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
