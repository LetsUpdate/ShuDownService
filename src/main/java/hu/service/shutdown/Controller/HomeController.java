package hu.service.shutdown.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/**")
    public String defa() {
        return "ez az oldal nem létezik;";
    }
}
