package selab.threetier.presentation;

import java.io.InputStream;
import java.util.Hashtable;

public abstract class HtmlPresentation extends Presentation {

    private String title;

    public HtmlPresentation(String title) {
        this.title = title;
    }

    @Override
    public String exec(String method, InputStream body) {
        return getHead().concat(getContents(method, body)).concat(getFoot());
    }

    public abstract String getContents(String method, InputStream body);

    @Override
    public Hashtable<String, String> getResponseHeaders() {
        Hashtable<String, String> ret = new Hashtable<>();
        ret.put("Content-Type", "text/html");
        return ret;
    }

    private String getHead() {
        return "<!doctype html>" +
                "<html>" +
                "<head>" +
                "<title>" + this.title + "</title>" +
                "</head>" +
                "<body>";
    }

    private String getFoot() {
        return "</body>" +
                "</html>";
    }

}
