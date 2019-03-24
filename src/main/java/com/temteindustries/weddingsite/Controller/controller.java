package com.temteindustries.weddingsite.Controller;

import com.temteindustries.weddingsite.CSV.CSVGenerator;
import com.temteindustries.weddingsite.Cache.CacheService;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping(value = "/guestList")
    public void getCSV (HttpServletResponse response) throws IOException {

        String dataDirectory = "target/classes/static/GuestList.csv";
        Path file = Paths.get(dataDirectory);
        if (Files.exists(file)) {
            Files.delete(file);
        }
        //Files.createFile(file);
        String CSVString = CacheService.getCSVCache();
        CSVGenerator.GenerateFile(CSVString);

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=GuestList.csv");
        try
        {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String cleanRequest(@RequestBody String stmnt) {
        String json = stmnt.substring(5); // remove "json=" from stmnt
        json = json.substring(0, json.length()-2);
        return json;
    }

}

