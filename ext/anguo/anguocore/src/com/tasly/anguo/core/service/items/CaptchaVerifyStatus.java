package com.tasly.anguo.core.service.items;

public enum CaptchaVerifyStatus {
    CORRECT(0), OVERDUE(1), INCORRECT(2);
    private int value;
    private CaptchaVerifyStatus(int value) {
        this.value = value;
    }
    public CaptchaVerifyStatus fromValue(int value) {
        for (CaptchaVerifyStatus status : CaptchaVerifyStatus.values()) {
            if (status.value == value) 
                return status;
        }
        return null;
    }
    public int getValue() {
        return value;
    }
}
