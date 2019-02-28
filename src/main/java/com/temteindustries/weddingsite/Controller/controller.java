package com.temteindustries.weddingsite.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controller {

    @GetMapping("/view")
    @ResponseBody
    public String viewGuestList(){
        return "Hello";
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateGuestList(
            @RequestBody String stmnt) {
        return ResponseEntity.ok(stmnt + "\nThis is a test") ;
    }
}
