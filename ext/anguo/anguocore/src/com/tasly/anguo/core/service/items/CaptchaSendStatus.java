package com.tasly.anguo.core.service.items;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://tempuri.org/" ,name="CSubmitState")
public class CaptchaSendStatus {
    private int state;
    private String msgId;
    private String msgState;
    private String reserve;
    
    @XmlElement(name="State")
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    @XmlElement(name="MsgID")
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    @XmlElement(name="MsgState")
    public String getMsgState() {
        return msgState;
    }
    public void setMsgState(String msgState) {
        this.msgState = msgState;
    }
    @XmlElement(name="Reserve")
    public String getReserve() {
        return reserve;
    }
    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
