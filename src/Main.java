import selab.threetier.Server;
import selab.threetier.presentation.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8000);

        server.addView("/", new FilePresentation("static/index.html", "text/html"));
        server.addView("/tasks/list", new TasksListPresentation());
        server.addView("/tasks/add", new AddTaskPresentation());
        server.addView("/tasks/remove", new RemoveTaskPresentation());

        server.start();
    }
}
