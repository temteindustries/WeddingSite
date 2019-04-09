package com.temteindustries.weddingsite.Controller;

import com.temteindustries.weddingsite.Cache.CacheService;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class controller {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/update")
    public String updateGuestList(@RequestBody String stmnt) {
        GsonJsonParser parser = new GsonJsonParser();
        String json = cleanRequest(stmnt);
        List<Object> guestList = parser.parseList(json);
        boolean cached = CacheService.addToCache(guestList);
        if (cached){
            System.out.println("RSVP cached");
            return"redirect:/";
        }
        else{
            System.out.println("Error caching");
            return"redirect:/";
        }
    }

    private String cleanRequest(@RequestBody String stmnt) {
        String json = stmnt.substring(5); // remove "json=" from stmnt
        json = json.substring(0, json.length()-2);
        return json;
    }

}

