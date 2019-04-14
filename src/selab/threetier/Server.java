package selab.threetier;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import selab.threetier.presentation.*;

public class Server {
    private HttpServer server;

    public Server(int port) throws Exception {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.setExecutor(null);
    }

    public void start() {
        this.server.start();
    }

    public void addView(String path, Presentation presentation) {
        this.server.createContext(path, presentation);
    }
}
