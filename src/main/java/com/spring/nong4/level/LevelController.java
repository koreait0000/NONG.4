package com.spring.nong4.level;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/level")
public class LevelController {

    @GetMapping("/junior")
    public String junior() {
        return "level/junior";
    }
    @GetMapping("/intermediate")
    public String intermediate() {
        return "level/intermediate";
    }
    @GetMapping("/advanced")
    public String advanced() {
        return "level/advanced";
    }
    @GetMapping("/master")
    public String master() {
        return "level/master";
    }
}
