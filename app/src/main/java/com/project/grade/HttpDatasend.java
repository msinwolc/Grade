package com.project.grade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpDatasend {
    public static String sendGet(String url) throws IOException{
        String result = "";
        BufferedReader in =null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            connection.setRequestProperty("Accept-Encoding","gzip, deflate");
            connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
            connection.setRequestProperty("Connection"," keep-alive");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            connection.setRequestProperty("Referer",Value.URL);
            connection.connect();

            in = new BufferedReader(new InputStreamReader(
               connection.getInputStream(),"UTF-8"));
            String line;
            //Value.URL=connection.getURL().toString();

            while ((line = in.readLine())!=null){
                result += line;
            }
        } catch (Exception e){
            System.out.println("error"+e);
            return null;
        }
        finally {
            try {
                if(in!=null){
                    in.close();
                }
            } catch (Exception e2){
                return null;
            }
        }
        return result;
    }

    public static String sendPost(String url,String param){
        PrintWriter out = null;
        BufferedReader in =null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            conn.setRequestProperty("Accept-Encoding","gzip, deflate");
            conn.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
            conn.setRequestProperty("Connection"," keep-alive");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            conn.setRequestProperty("Referer",Value.URL);
            conn.connect();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.println(param);
            out.flush();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"GBK")
            );
            String line=in.readLine();
            while (line!=null){
                result += line;
            }
        } catch (Exception e){
            System.out.println("发送POST请求失败");
            System.out.println(param);
            System.out.println(url);
            return null;
        }
        finally {
            try {
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            } catch (IOException e2){
                e2.printStackTrace();
            }
        }
        return result;
    }
}

