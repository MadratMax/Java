package madrat.Empire;

import java.util.UUID;

public class Civil {

    private String name;
    private final String id;
    private int health;
    private ResourceType workOnResourceType;
    private Spirit spirit;

    public Civil() {
        this.id = UUID.randomUUID().toString();
        this.spirit = Spirit.MEDIUM;
        this.workOnResourceType = ResourceType.UNSET;
    }

    public String name() {
        return id;
    }

    public void setToWork(ResourceType resourceType) {
        workOnResourceType = resourceType;
    }

    public ResourceType getWorkOnResourceType() {
        return workOnResourceType;
    }
}
