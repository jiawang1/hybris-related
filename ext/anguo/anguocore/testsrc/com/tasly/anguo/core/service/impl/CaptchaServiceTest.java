package com.tasly.anguo.core.service.impl;

import javax.annotation.Resource;

import com.tasly.anguo.core.service.ICaptchaService;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

public class CaptchaServiceTest extends ServicelayerTransactionalTest {
    @Resource
    private ICaptchaService captchaService;
    
    public void sendCaptcha(String mobileNumber) {
        
    }
    public void verifyCaptcha(String mobileNumber, String captcha) {
        
    }
    public void addCaptcha(String mobileNumber, String captcha){
        
    }
    public void removeCaptcha(String mobileNumber){
        
    }
}
