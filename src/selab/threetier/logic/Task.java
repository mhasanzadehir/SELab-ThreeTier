package selab.threetier.logic;

import selab.threetier.storage.EntityStorage;
import selab.threetier.storage.Storage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task extends Entity implements Comparable {
    private String title;
    private Date start;
    private Date end;

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        title = value;
    }

    public void setStart(Date value) {
        start = value;
    }

    public String getStartDate() {
        return new SimpleDateFormat("YYYY-MM-DD").format(start);
    }

    public String getStartTime() {
        return new SimpleDateFormat("HH:mm:ss").format(start);
    }

    public void setEnd(Date value) {
        end = value;
    }

    public String getEndTime() {
        return new SimpleDateFormat("HH:mm:ss").format(end);
    }

    public void save() throws IOException {
        if (start.after(end)) {
            throw new IOException("Start time can not set after end time");
        }
        EntityStorage<Task> tasks = Storage.getInstance().getTasks();
        boolean existOverlap = tasks.getAll()
                .stream()
                .anyMatch(task -> ((task.start.after(start) || task.start.equals(start)) && task.start.before(end))
                        || ((task.end.before(end) || task.end.equals(end)) && task.end.after(start)));
        if (existOverlap) {
            throw new IOException("Overlap task");
        }
        tasks.addOrUpdate(this);
    }

    public static ArrayList<Task> getAll() {
        return Storage.getInstance().getTasks().getAll();
    }

    public static ArrayList<Task> getAllSorted() {
        ArrayList<Task> tasks = getAll();
        Collections.sort(tasks);
        return tasks;
    }

    @Override
    public int compareTo(Object o) {
        Task second = ((Task) o);
        if (start.equals(second.start)){
            return 0;
        } else if (start.before(second.start)){
            return -1;
        } else {
            return 1;
        }
    }
}
