package com.temteindustries.weddingsite.Controller;

import com.temteindustries.weddingsite.Email.sendEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controller {

    @GetMapping("/home")
    public String index() {
        return "index.html";
    }

    @PostMapping("/update")
    public String updateGuestList(
            @RequestBody String stmnt) {
		//System.out.println("Value Recieved:\n\n"+stmnt);
        sendEmail sendemail = new sendEmail();
        boolean msgSent = sendemail.sendMail(stmnt);

        if (msgSent){
            System.out.println("Email Sent");
            return"redirect:/home";
        }
        else{
            System.out.println("Error sending email");
            return"redirect:/home";
        } 
		//return"redirect:/home";
    }
}
