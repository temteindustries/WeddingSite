package com.temteindustries.weddingsite.Cache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class CacheService {
    private static String sheetsUrl;
    private static String csvUrl;

    @Autowired
    public void setUp(
            @Value("${cache.url.update}") String sheetsUrl,
            @Value("${cache.url.csv}") String csvUrl) {
        this.sheetsUrl = sheetsUrl;
        this.csvUrl = csvUrl;
    }


    public static Boolean addToCache(List<Object> guestList) {
        final HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(sheetsUrl);
        httppost.setHeader("Content-Type", "application/json");

        try{
            httppost.setEntity(new ByteArrayEntity(guestList.toString().getBytes("UTF-8")));
            httpclient.execute(httppost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getCSVCache(){

        final HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(csvUrl);
        httpget.setHeader("Content-Type","text/plain");
        HttpResponse response = null;
        HttpEntity entity = null;
        String CSVString = null;

        try{
            response = httpclient.execute(httpget);
            entity = response.getEntity();
            CSVString = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CSVString;
    }
}
