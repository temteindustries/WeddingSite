package com.temteindustries.weddingsite.Controller;

import com.temteindustries.weddingsite.Cache.CacheService;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class WeddingController implements ErrorController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    private static final String PATH = "/error";
    @RequestMapping(value = PATH)
    public String error() {
        return "error.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @PostMapping("/update")
    public String updateGuestList(@RequestBody String stmnt) {
        boolean cached = false;
        GsonJsonParser parser = new GsonJsonParser();
        String json = cleanRequest(stmnt);
        if(json.isEmpty()){
            return"redirect:/error";
        }
        List<Object> guestList = parser.parseList(json);
        try {
            cached = CacheService.addToCache(guestList);
        }catch (Exception e){
            System.out.println("Error caching: \n" + e.getMessage());
            return"redirect:/error";
        }
        if (cached){
            System.out.println("RSVP cached");
            return"redirect:/";
        }
        else{
            System.out.println("Error caching");
            return"redirect:/error";
        }
    }

    public String cleanRequest(@RequestBody String stmnt) {
        String json = null;
        try {
            json = stmnt.substring(5); // remove "json=" from stmnt
            //json = json.substring(0, json.length() - 2);
        }catch (Exception e){
            System.out.println("Error cleaning request: \n" + e.getMessage());
            return json;
        }finally {
            return json;
        }
    }

}

