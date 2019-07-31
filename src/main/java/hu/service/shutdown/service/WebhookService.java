package hu.service.shutdown.service;

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
        if (isConstains(POWER_OFF_STRINGS, operatino)) operatino = POWER_OFF_STRINGS[0];
        else if (isConstains(REBOOT_STRINGS, operatino)) operatino = REBOOT_STRINGS[0];
        else if (isConstains(HIBERNATE_STRINGS, operatino)) operatino = HIBERNATE_STRINGS[0];
        else if (isConstains(LOGOUT, operatino)) operatino = LOGOUT[0];
        else if (isConstains(SLEEP_STRINGS, operatino)) operatino = SLEEP_STRINGS[0];
        else if (isConstains(MONITOR_OFF, operatino)) operatino = MONITOR_OFF[0];
        else if (operatino.equals("test")) return "A teszt sikeres";
        else {
            System.out.println("nem ismertem fel: " + operatino);
            return "Nem ismertem fel: " + operatino;
        }
        final String string = operatino;


        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    switch (string) {
                        case "power off":
                            runtime.exec("shutdown -s -t 10");
                            System.out.println("Leálltam");
                            break;
                        case "sleep":
                            System.out.println("el aludtam");
                            runtime.exec("extras sleep");
                            break;
                        case "reboot":
                            runtime.exec("shutdown -r -t 10");
                            System.out.println("ujraindultam");
                            break;
                        case "hibernate":
                            runtime.exec("shutdown -h");
                            System.out.println("hibernálódtam");
                            break;
                        case "logout":
                            runtime.exec("shutdown -l");
                            System.out.println("Kijelentkeztem");
                            break;
                        case "monitor":
                            System.out.println("monitor kikapcsolva");
                            runtime.exec("extras monitorOff");
                            break;
                        default:
                            System.out.println("nem ismertem fel: " + string);
                    }
                } catch (Exception e) {
                    System.err.println("WebhookService Thread hiba:\n" + e.toString());
                }
            }
        };
        thread.start();
        return "Késsz: " + operatino;

    }

}
