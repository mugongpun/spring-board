package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello SBB";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
