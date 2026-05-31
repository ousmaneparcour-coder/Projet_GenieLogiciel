package MonLogiciel;

public class Privilege {
    private String type;
    private String description;

    public Privilege(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public void appliquer() {
        System.out.println("Privilège [" + type + "] appliqué : " + description);
    }

    public String getType() { return type; }
    public String getDescription() { return description; }
}