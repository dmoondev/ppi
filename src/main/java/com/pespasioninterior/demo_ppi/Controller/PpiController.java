package com.pespasioninterior.demo_ppi.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PpiController {
    
    @PostMapping(value = "demo")
    public String welcome(){
        return "Welcome from secure endpoint";
    }
}
