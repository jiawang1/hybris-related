package com.tasly.anguo.core.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.tasly.anguo.core.service.ICaptchaService;
import com.tasly.anguo.core.service.items.CaptchaEntity;
import com.tasly.anguo.core.service.items.CaptchaSendStatus;
import com.tasly.anguo.core.service.items.CaptchaVerifyStatus;

@Component("captchaService")
public class DefaultCaptchaService implements ICaptchaService {
    
    private Map<String, CaptchaEntity> map =  new ConcurrentHashMap<String, CaptchaEntity>();

    protected String generateCaptcha() {
        Random rd = new Random(Calendar.getInstance().getTimeInMillis());
        int val = rd.nextInt(10000);
        return String.format("%04d", val);
    }
    
    @Override
    public CaptchaSendStatus sendCaptcha(String mobileNumber) {
        String captcha = generateCaptcha();
        //TODO remove below captcha once we buy the captcha service.
        captcha = "75343";
        String message = "您的验证码是：" + captcha + "。请不要把验证码泄露给其他人。【微网通联】";
        String postData = "sname=DL-xubin&spwd=xubin1234&scorpid=&sprdid=1012888&sdst=" + mobileNumber + "&smsg=" + message;
        String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
        addCaptcha(mobileNumber, captcha);
        return SMS(postData, postUrl);
    }

    @Override
    public CaptchaVerifyStatus verifyCaptcha(String mobileNumber, String captcha) {
        if (!map.containsKey(mobileNumber))
            return CaptchaVerifyStatus.INCORRECT;
        
        CaptchaEntity entity = map.get(mobileNumber);
        if (!entity.getCaptcha().equalsIgnoreCase(captcha)) {
            return CaptchaVerifyStatus.INCORRECT;
        } else {
         long addTime = entity.getGeneratedTime();
         if (Calendar.getInstance().getTimeInMillis() - addTime > 1 * 60 * 1000) {
             map.remove(mobileNumber);
             return CaptchaVerifyStatus.OVERDUE;
         } else {
             map.remove(mobileNumber);
             return CaptchaVerifyStatus.CORRECT;
         }          
       }
    }

    protected void addCaptcha(String mobileNumber, String captcha) {
        CaptchaEntity entity = new CaptchaEntity(captcha, Calendar.getInstance().getTimeInMillis());
        map.put(mobileNumber, entity);
        
    }
    
    public static void main(String[] args) {
        String result = "<CSubmitState xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://tempuri.org/\">";
        result += "<State>0</State><MsgID>1507301457092809805</MsgID><MsgState>提交成功</MsgState><Reserve>0</Reserve></CSubmitState>";
        try {  
            JAXBContext context = JAXBContext.newInstance(CaptchaSendStatus.class);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            CaptchaSendStatus captchaVerifyStatus = (CaptchaSendStatus)unmarshaller.unmarshal(new StringReader(result));  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }   
    }

    private CaptchaSendStatus SMS(String postData, String postUrl) {
        try {
            // 发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            // 获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return null;
            }
            // 获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            try {  
                JAXBContext context = JAXBContext.newInstance(CaptchaSendStatus.class);  
                Unmarshaller unmarshaller = context.createUnmarshaller();  
                CaptchaSendStatus captchaVerifyStatus = (CaptchaSendStatus)unmarshaller.unmarshal(new StringReader(result));  
                return captchaVerifyStatus; 
            } catch (JAXBException e) {  
                e.printStackTrace();  
            }  
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
