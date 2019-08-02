package hu.service.shutdown.addons;

import java.util.Arrays;
import java.util.List;

public class Operation extends Operations implements IDK {
    private final String command;
    private List<String> triggerWords;

    public Operation(String command, String[] triggerWords) {
        this.triggerWords = Arrays.asList(triggerWords);
        this.command = command;
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
    public void execute(Runtime runtime) {
        try {
            runtime.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Nem sikerült lefuttatni a parancsot: " + command);
        }
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
