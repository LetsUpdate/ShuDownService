package hu.service.shutdown.service;

import hu.service.shutdown.addons.ApplicationOperation;
import hu.service.shutdown.addons.Operation;
import hu.service.shutdown.addons.Operations;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    //region Strings
    private final String[] POWER_OFF_STRINGS = {"power off", "off", "shutdown", "turn off"};
    private final String[] REBOOT_STRINGS = {"reboot", "restart"};
    private final String[] HIBERNATE_STRINGS = {"hibernate", "freez"};
    private final String[] LOGOUT = {"logout", "sing out"};
    private final String[] SLEEP_STRINGS = {"sleep"};
    private final String[] MONITOR_OFF = {"monitor", "off monitor", "monitor off"};
    private final String[] APPLICATION_START_STRINGS = {"start", "run",};
    private Operations operations = new Operations();

    private WebhookService(){
        operations.addNewOperation(new Operation("shutdown -s -t 10", POWER_OFF_STRINGS));
        operations.addNewOperation(new Operation("shutdown -r -t 10", REBOOT_STRINGS));
        operations.addNewOperation(new Operation("shutdown -h", HIBERNATE_STRINGS));
        operations.addNewOperation(new Operation("shutdown -l", LOGOUT));
        operations.addNewOperation(new Operation("extras sleep", SLEEP_STRINGS));
        operations.addNewOperation(new Operation("extras monitorOff", MONITOR_OFF));
        operations.addNewOperation(new ApplicationOperation("games", APPLICATION_START_STRINGS));

    }

    private boolean isConstains(String[] array, String equals) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equals(equals)) return true;
        return false;
    }

    //endregion
    public String pcManager(String operatino) {
        operations.runOperationByTriggerString(operatino);
        return "Késsz: " + operatino;
    }
}