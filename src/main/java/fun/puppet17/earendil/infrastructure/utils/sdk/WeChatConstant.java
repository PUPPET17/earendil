package fun.puppet17.earendil.infrastructure.utils.sdk;

/**
 * @author PUPPET17
 * @version 1.0
 * @date 2023/10/16
 */
public class WeChatConstant {

    /**
     * 请求消息类型
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO ="shortvideo";
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_SCAN = "scan";
    public static final String EVENT_TYPE_LOCATION = "location";
    public static final String EVENT_TYPE_CLICK = "click";
    public final String EVENT_TYPE_VIEW ="VIEW";
    public final String EVENT_TYPE_TEMPLATESENDJOBFINISH="TEMPLATESENDJOBFINISH";
    /**
     * 响应消息类型
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_IMAGE ="image";
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    public static final  String RESP_MESSAGE_TYPE_NEWS = "news";
    public static final String RESP_MESSAGE_TYPE_VIDEO ="video";
    /**
     * 基本信息
     */
    public static final String FROM_USER_NAME = "FromUserName";
    public static final String TO_USER_NAME = "ToUserName";
    public static final String MSG_ID = "MsgId";
    public static final String MSG_TYPE = "MsgType";
    public static final String CONTENT = "Content";
    public static final String CREATE_TIME = "CreateTime";
    public static final String EVENT = "Event";

}
