package hu.service.shutdown.addons;

import java.util.List;

interface IDK {
    boolean isConstaint(String word);

    void addTRiggerWord(String triggerWord);

    List<String> getTriggerWords();

    void setTriggerWords(List<String> triggerWords);

    void execute(Runtime runtime);
}
