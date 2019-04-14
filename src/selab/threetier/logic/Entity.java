package selab.threetier.logic;

import selab.threetier.storage.Storage;

public abstract class Entity {
    private int id;

    public int getId() { return id; }
    public void setId(int value) { id = value; }
}
