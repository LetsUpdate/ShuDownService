package hu.service.shutdown.Controller;

import hu.service.shutdown.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/phone")
@RestController()
public class phoneController {

    WebhookService webhookService;

    @Autowired
    public void setWebhookService(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @RequestMapping("/{par}")
    public String Reboot(@PathVariable("par") String param) {
        if (param == null)
            return "nem talállható";
        System.out.println("Késsz:" + param);
        return webhookService.pcManager(param);

    }
}