package selab.threetier.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.json.*;

public abstract class JSONPresentation extends Presentation {

    @Override
    public String exec(String method, InputStream body) throws IOException {
        JSONObject json = getData(method, body);
        return json.toString();
    }

    public abstract JSONObject getData(String method, InputStream body) throws IOException;

    @Override
    public Hashtable<String, String> getResponseHeaders() {
        Hashtable<String, String> ret = new Hashtable<>();
        ret.put("Content-Type", "application/json");
        return ret;
    }
}
