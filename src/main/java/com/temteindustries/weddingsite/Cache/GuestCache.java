package com.temteindustries.weddingsite.Cache;

import com.temteindustries.weddingsite.model.GuestObject;
import java.util.HashMap;

public class GuestCache {

    private static HashMap<Integer, GuestObject> cache = new HashMap<>();
    // static method to create instance of GuestCache class
    public static HashMap<Integer, GuestObject> getInstance()
    {
        if (cache == null)
            cache = new HashMap<>();

        return cache;
    }
}
