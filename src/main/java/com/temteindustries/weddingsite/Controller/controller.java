package com.temteindustries.weddingsite.Controller;

import com.temteindustries.weddingsite.Email.sendEmail;
import com.temteindustries.weddingsite.model.GuestObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class controller {

    @GetMapping("/home")
    public String index() {
        return "index.html";
    }

    @PostMapping("/update")
    public String updateGuestList(
            @RequestBody String stmnt) {
        sendEmail sendemail = new sendEmail();
        String json = stmnt.substring(5); // remove "json=" from stmnt
        json = json.substring(0, json.length()-2);
        GsonJsonParser parser = new GsonJsonParser();
        List<Object> guestList = parser.parseList(json);
        boolean msgSent = sendemail.sendMail(guestList);

        if (msgSent){
            System.out.println("Email Sent");
            return"redirect:/home";
        }
        else{
            System.out.println("Error sending email");
            return"redirect:/home";
        }
    }
}
