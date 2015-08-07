package com.tasly.anguo.core.service.items;

import org.joda.time.DateTime;

public class CaptchaEntity {
    private String captcha;
    private long generatedTime;
    public CaptchaEntity(String captcha, long generatedTime) {
        super();
        this.captcha = captcha;
        this.generatedTime = generatedTime;
    }
    public String getCaptcha() {
        return captcha;
    }
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    public long getGeneratedTime() {
        return generatedTime;
    }
    public void setGeneratedTime(long generatedTime) {
        this.generatedTime = generatedTime;
    }
}
