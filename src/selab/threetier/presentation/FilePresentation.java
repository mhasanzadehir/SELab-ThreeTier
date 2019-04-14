package selab.threetier.presentation;

import java.io.InputStream;
import java.util.Hashtable;
import java.io.*;

public class FilePresentation extends Presentation {

    private String filename;
    private String contentType;

    public FilePresentation(String filename, String contentType) {
        this.filename = filename;
        this.contentType = contentType;
    }

    @Override
    public String exec(String method, InputStream body) throws IOException {
        File file = new File(filename);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String content = "";
        String st;
        while ((st = br.readLine()) != null)
            content = content.concat(st);

        return content;
    }

    @Override
    public Hashtable<String, String> getResponseHeaders() {
        Hashtable<String, String> ret = new Hashtable<>();
        ret.put("Content-Type", contentType);
        return ret;
    }
}
