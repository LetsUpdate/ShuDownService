package hu.service.shutdown.Controller;

import hu.service.shutdown.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    WebhookService webhookService;

    @Autowired
    public void setWebhookService(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @RequestMapping("/computer")
    public void Reboot(@RequestBody String param) {
        if (param == null)
            return;
        param = param.toLowerCase();
        webhookService.pcManager(param);
        System.out.println("Késsz:" + param);
    }
}
