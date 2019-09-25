package com.project.grade;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoginServer {
    String loginStr = "";
    String myUrl;
    String number;
    String password;
    String htmlInfo;

    public int Connect(String number,String password){
        this.number = number;
        this.password = password;
        String lt = "";
        try {
            loginStr = HttpDatasend.sendGet("http://authserver.hue.edu.cn/authserver/login?service=http%3A%2F%2F202.197.144.243%3A80%2Fcaslogin.jsp");
            myUrl = Value.URL;
//            int d = myUrl.indexOf(")");
//            myUrl = myUrl.substring(0,d+1);
        }catch (IOException e1){
            return -1;
        }
//        if(loginStr=="")
//            return -1;
//        if(loginStr==null)
//            return -3;
//        int error = loginStr.indexOf("服务器忙");
//        if(error!=-1)
//           return -2;
//        if (loginStr != "") {
//            int startIndex = loginStr.indexOf("name=\"lt\"");
//            int endIndex = loginStr.indexOf("\"",startIndex+23);
//            loginStr = loginStr.substring(startIndex+18,loginStr.length());
//            startIndex = loginStr.indexOf("\"");
//            endIndex = loginStr.indexOf("\"",startIndex+3);
//            loginStr = loginStr.substring(startIndex+1,endIndex);
//            lt = loginStr;
//        }
//        try {
//            lt = java.net.URLEncoder.encode(lt,"utf-8");
//        } catch (UnsupportedEncodingException e1){
//            return -1;
//        }

        String sendStr = "username="+this.number+"&password="+this.password+
                "&ltLT-361383-GettRJBQGesYbMa4IIwJKrIRlmWPas1569293496256-sgxS-cas"+
                "&dllt=userNamePasswordLogin" +
                "&execution=e1s1" +
                "&_eventId=submit" +
                "&rmShown=1";
        try {
            htmlInfo = HttpDatasend.sendPost(myUrl,sendStr);
        } catch (Exception e){
            return -1;
        }
        if(htmlInfo == null)
            return -3;
        if(htmlInfo!=""){
            if(htmlInfo.indexOf("密码错误")>0 || htmlInfo.indexOf("用户名错误")>0){
                return -1;
            }
            int startIndex = htmlInfo.indexOf("<td nowrap>");
            int endIndex = htmlInfo.indexOf("同学</font");
            if(startIndex<0 || endIndex<0){
                return -1;
            }
            htmlInfo = htmlInfo.substring(startIndex+16,endIndex);
            String[] splitStr = htmlInfo.split(" ");
            Value.studentName = splitStr[1];
            Value.studentNumber = splitStr[0];
            return 1;
        }
        return 1;
    }
}
