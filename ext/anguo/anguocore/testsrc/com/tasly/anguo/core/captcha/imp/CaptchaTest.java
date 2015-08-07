package com.tasly.anguo.core.captcha.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
public class CaptchaTest {
    
    public static void main(String[] args)
    {
        String postData = "sname=DL-xubin&spwd=xubin1234&scorpid=&sprdid=1012888&sdst=18617192927&smsg=您的验证码是：75343。请不要把验证码泄露给其他人。【微网通联】";
        String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
        System.out.println(SMS(postData, postUrl));
    }


        public static String SMS(String postData, String postUrl) {
            try {
                //发送POST请求
                URL url = new URL(postUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setUseCaches(false);
                conn.setDoOutput(true);

                conn.setRequestProperty("Content-Length", "" + postData.length());
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                out.write(postData);
                out.flush();
                out.close();

                //获取响应状态
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    System.out.println("connect failed!");
                    return "";
                }
                //获取响应内容体
                String line, result = "";
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                while ((line = in.readLine()) != null) {
                    result += line + "\n";
                }
                in.close();
                return result;
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
            return "";
        }

}
