package st.qurks.ms.poc.model.mongodb;

import com.mongodb.client.result.DeleteResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomDeleteResult {

    List<String> successfullyDeletedIds = new ArrayList<>();
    List<String> failedToDeleteIds = new ArrayList<>();

    public CustomDeleteResult appendFromDeleteResult(List<String> ids, List<DeleteResult> deleteResults) {
        Iterator<DeleteResult> deleteResultsIter = deleteResults.iterator();
        Iterator<String> idsIter = ids.iterator();
        while (idsIter.hasNext() && deleteResultsIter.hasNext()) {
            String nextId = idsIter.next();
            DeleteResult nextDelete = deleteResultsIter.next();
            if (nextDelete.getDeletedCount() > 0) {
                successfullyDeletedIds.add(nextId);
            } else {
                failedToDeleteIds.add(nextId);
            }
        }
        return this;
    }

    public List<String> getSuccessfullyDeletedIds() {
        return successfullyDeletedIds;
    }

    public void setSuccessfullyDeletedIds(List<String> successfullyDeletedIds) {
        this.successfullyDeletedIds = successfullyDeletedIds;
    }

    public List<String> getFailedToDeleteIds() {
        return failedToDeleteIds;
    }

    public void setFailedToDeleteIds(List<String> failedToDeleteIds) {
        this.failedToDeleteIds = failedToDeleteIds;
    }
}
