package hu.service.shutdown.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface IDK {
    boolean isConstaint(String word);

    void addTRiggerWord(String triggerWord);

    String getCommand();

    List<String> getTriggerWords();

    void setTriggerWords(List<String> triggerWords);
}

public class Operations {
    Runtime runtime = Runtime.getRuntime();
    private List<IDK> operations = new ArrayList<>();

    public void addNewOperation(IDK operation) {
        operations.add(operation);
    }

    public void runOperationByTriggerString(String string){
        for (IDK op :
             operations) {
            if(op.isConstaint(string)) {
                execute(op);
                return;
            }
        }
    }

    private void execute(IDK op) {
        try {
            runtime.exec(op.getCommand());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Nem sikerült lefuttatni a parancsot: " + op.getCommand());
        }
    }


}

class Operation extends Operations implements IDK {
    private final String command;
    private List<String> triggerWords;

    Operation(String command, String[] triggerWords) {
        this.triggerWords = Arrays.asList(triggerWords);
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public List<String> getTriggerWords() {
        return triggerWords;
    }

    @Override
    public void setTriggerWords(List<String> triggerWords) {
        this.triggerWords = triggerWords;
    }

    @Override
    public void addTRiggerWord(String triggerWord) {
        if (triggerWords.contains(triggerWord)) return;
        triggerWords.add(triggerWord);
    }

    @Override
    public boolean isConstaint(String word) {
        return triggerWords.contains(word);
    }
}

class ApplicationOperation extends Operations implements IDK {
    private List<String> triggerWords;
    private String command;

    @Override
    public boolean isConstaint(String word) {
        return false;
    }

    @Override
    public void addTRiggerWord(String triggerWord) {

    }

    @Override
    public String getCommand() {
        return null;
    }

    @Override
    public List<String> getTriggerWords() {
        return null;
    }

    @Override
    public void setTriggerWords(List<String> triggerWords) {

    }
}
