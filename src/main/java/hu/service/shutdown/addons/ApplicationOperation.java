package hu.service.shutdown.addons;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationOperation extends Operations implements IDK {
    private final String FOLDER_LOCATION;
    private List<String> triggerWords;
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
                            b = FOLDER_LOCATION + "//" + b;
                            WindowsShortcut wsc = new WindowsShortcut(new File(b));
                            Runtime.getRuntime().exec(wsc.getRealFilename());
                            return true;
                        } catch (IOException | ParseException e) {
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
