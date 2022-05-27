package madrat;

import java.util.UUID;

public class Land {

    private final String name;
    private final int size;
    private final String id;

    public Land(String name, int size) {
        this.name = name;
        this.size = size;
        this.id = UUID.randomUUID().toString();
    }

    public String name() {
        return name;
    }
}
