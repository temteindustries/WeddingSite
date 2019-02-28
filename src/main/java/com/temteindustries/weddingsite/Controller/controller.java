package com.temteindustries.weddingsite.Controller;

import com.temteindustries.weddingsite.Email.sendEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controller {

    @GetMapping("/update")
    public String index() {
        return "index.html";
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateGuestList(
            @RequestBody String stmnt) {

        sendEmail sendemail = new sendEmail();
        boolean msgSent = sendemail.sendMail(stmnt);

        if (msgSent){
            return ResponseEntity.ok("Email Sent") ;
        }
        else{
            return ResponseEntity.ok("Error sending email") ;
        }
    }
}
