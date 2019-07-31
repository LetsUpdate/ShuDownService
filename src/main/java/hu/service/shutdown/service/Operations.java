package hu.service.shutdown.service;

import java.util.Arrays;
import java.util.List;

public class Operations {
    private List<Operation> operations;
    public void addOperation(Operation operation){
        operations.add(operation);
    }
    public void runOperationByTriggerString(String string){
        for (Operation op:
             operations) {
            if(op.getTriggerWords().equals(string)) {
                op.execute();
                return;
            }
        }

    }
    public static class Operation {
        Runtime runtime = Runtime.getRuntime();
        private List<String> triggerWords;
        private final String command;

        Operation(String command){
            this.command =command;
        }

        public Operation(String command,String[] triggerWords) {
            this.triggerWords = Arrays.asList(triggerWords);
            this.command = command;
        }

        public void execute(){
            try {
                runtime.exec(command);
            }catch (Exception e){
                e.printStackTrace();
                System.err.println("Nem sikerült lefuttatni a parancsot: "+command);
            }
        }

        public List<String> getTriggerWords() {
            return triggerWords;
        }

        public void setTriggerWords(List<String> triggerWords) {
            this.triggerWords = triggerWords;
        }
        public void addTRiggerWord(String triggerWord){
            if(triggerWords.contains(triggerWord)) return;
            triggerWords.add(triggerWord);
        }
        public void runIfConstaintWord(String word){
            if(triggerWords==null) return;
            if(triggerWords.contains(word))
                execute();
        }
    }

}
