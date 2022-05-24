package madrat.Tasks;

import java.util.Dictionary;
import java.util.HashMap;

public class Block {

    private final String name;
    private String schoolKey = "school";
    private String libraryKey = "lib";
    private String officeKey = "office";
    private HashMap<String, Boolean> block;

    public Block(boolean school, boolean lib, boolean office, String name) {
        this.block = new HashMap<String, Boolean> () {};
        this.block.put(schoolKey, school);
        this.block.put(libraryKey, lib);
        this.block.put(officeKey, office);
        this.name = name;

        create();
    }

    public boolean school() {
        return block.get(schoolKey);
    }

    public boolean library() {
        return block.get(libraryKey);
    }

    public boolean office() {
        return block.get(officeKey);
    }

    public String name() {
        return name;
    }

    private HashMap<String, Boolean> create() {
        return block;
    }
}
