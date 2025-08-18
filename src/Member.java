public class Member extends  Person{

    private Library library;

    public Member(String n, String e) {
        super(n, e);
    }

    public void joinLibrary (Library l) {
        library = l;
    }

    public void removeLibrary () {
        library = null;
    }

}
