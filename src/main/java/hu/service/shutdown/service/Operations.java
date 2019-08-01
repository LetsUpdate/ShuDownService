package hu.service.shutdown.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface IDK {
    boolean isConstaint(String word);

    void addTRiggerWord(String triggerWord);

    List<String> getTriggerWords();

    void setTriggerWords(List<String> triggerWords);

    void execute(Runtime runtime);
}

public class Operations {
    private List<IDK> operations = new ArrayList<>();

    public void addNewOperation(IDK operation) {
        operations.add(operation);
    }

    public void runOperationByTriggerString(String string){
        for (IDK op :
             operations) {
            if(op.isConstaint(string)) {
                if (op instanceof Operation)
                    op.execute(Runtime.getRuntime());
                return;
            }
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

class ApplicationOperation extends Operations implements IDK {
    private List<String> triggerWords;
    private final String FOLDER_LOCATION;
    private List<String> applications;

    public ApplicationOperation(String folderLoaction, String[] triggerWords) {
        FOLDER_LOCATION = folderLoaction;
        updateAppList();
        this.triggerWords = Arrays.asList(triggerWords);
    }

    private List<String> getFileList() {
        return getFileList(FOLDER_LOCATION);
    }

    private List<String> getFileList(String folder) {
        File file = new File(folder);
        List<String> list = new ArrayList<String>();
        file.mkdir();
        for (File f : file.listFiles()
        ) {
            list.add(f.getName());
        }

        System.out.println(list);
        return list;
    }

    public void updateAppList() {
        applications = getFileList();
    }

    @Override
    public boolean isConstaint(String word) {
        for (String a :
                triggerWords) {
            if (word.contains(a)) {
                word = word.substring(a.length() + 1).toLowerCase();

                for (String b : applications) {
                    if (b.toLowerCase().contains(word)) {
                        try {
                            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + FOLDER_LOCATION + "//" + b);
                            return true;
                        } catch (IOException e) {
                            System.err.println("Execute hiba");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void addTRiggerWord(String triggerWord) {
        triggerWords.add(triggerWord);
    }

    @Override
    public List<String> getTriggerWords() {
        return null;
    }

    @Override
    public void setTriggerWords(List<String> triggerWords) {
        this.triggerWords = triggerWords;
    }

    @Override
    public void execute(Runtime runtime) {
        System.err.println("Ez így nem megy (AppliacationOperation)");
    }
}
