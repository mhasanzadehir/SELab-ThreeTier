package selab.threetier.storage;
import selab.threetier.logic.Task;

public class Storage {
    private static Storage instance = new Storage();
    private EntityStorage<Task> tasks = new EntityStorage<Task>();

    private Storage() {}

    public static Storage getInstance() { return instance; }
    public EntityStorage<Task> getTasks() { return tasks; }
}
