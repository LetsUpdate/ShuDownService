package hu.service.shutdown.Controller;

import hu.service.shutdown.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/computer/{param}")
    public void Reboot(@PathVariable("param") String param) {
        if (param == null) {
            return;
        }
        param = param.toLowerCase().substring(1);

        webhookService.pcManager(param);
        System.out.println("Késsz:" + param);
    }

}
