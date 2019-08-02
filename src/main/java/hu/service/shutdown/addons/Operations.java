package hu.service.shutdown.addons;

import java.util.ArrayList;
import java.util.List;

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

