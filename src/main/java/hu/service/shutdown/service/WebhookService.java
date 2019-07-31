package hu.service.shutdown.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebhookService {

    //region Strings
    private final String[] POWER_OFF_STRINGS = {"power off", "off", "shutdown", "turn off"};
    private final String[] REBOOT_STRINGS = {"reboot", "restart"};
    private final String[] HIBERNATE_STRINGS = {"hibernate", "freez"};
    private final String[] LOGOUT = {"logout", "sing out"};
    private final String[] SLEEP_STRINGS = {"sleep"};
    private final String[] MONITOR_OFF = {"monitor", "off monitor", "monitor off"};



    private Operations operations;

    private WebhookService(){
        operations = new Operations();
        operations.addOperation(new Operations.Operation("shutdown -s -t 10",POWER_OFF_STRINGS));
        operations.addOperation(new Operations.Operation("shutdown -r -t 10",REBOOT_STRINGS));
        operations.addOperation(new Operations.Operation("shutdown -h",HIBERNATE_STRINGS));
        operations.addOperation(new Operations.Operation("shutdown -l",LOGOUT));
        operations.addOperation(new Operations.Operation("extras sleep",SLEEP_STRINGS));
        operations.addOperation(new Operations.Operation("extras monitorOff",MONITOR_OFF));

    }


    private boolean isConstains(String[] array, String equals) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equals(equals)) return true;
        return false;
    }

    //endregion
    public String pcManager(String operatino) {
        final String string = operatino;


        operations.runOperationByTriggerString(string);
        return "Késsz: " + operatino;

    }

}
