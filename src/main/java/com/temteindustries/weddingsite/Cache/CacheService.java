package com.temteindustries.weddingsite.Cache;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class CacheService {
    private static String sheetsUrl;

    @Autowired
    public void setUp(
            @Value("${cache.url.update}") String sheetsUrl) {
        this.sheetsUrl = sheetsUrl;
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

}
