package selab.threetier.presentation;

import org.json.JSONArray;
import org.json.JSONObject;
import selab.threetier.logic.Task;
import java.io.InputStream;

public class TasksListPresentation extends JSONPresentation {
    @Override
    public JSONObject getData(String method, InputStream body) {
        JSONObject result = new JSONObject();
        result.put("tasks", new JSONArray(Task.getAll()));
        return result;
    }
}
