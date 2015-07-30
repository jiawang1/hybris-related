package com.tasly.anguo.core.service;

import com.tasly.anguo.core.service.items.CaptchaSendStatus;
import com.tasly.anguo.core.service.items.CaptchaVerifyStatus;

public interface ICaptchaService {
    public CaptchaSendStatus sendCaptcha(String mobileNumber);
    public CaptchaVerifyStatus verifyCaptcha(String mobileNumber, String captcha);

}
