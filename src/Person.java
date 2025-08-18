public class Person {
    private final String Name;
    private String Email;
    private final long   id;

    static long nextId = 0;

    public Person(String n, String e) {
        this.Name = n;
        this.Email = e;
        this.id = nextId;
        nextId ++;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String e) {
        this.Email = e;
    }

    public long getId() {
        return id;
    }

}
