package interviews.Question1;

public class User implements IUser{

    private final int id;
    private final String email;
    private final String password;
    private final String location;
    private int incorrectAttempt;

    public User(int id, String email, String password, String location) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.location = location;
        this.incorrectAttempt = 0;
    }

    @Override
    public int getId() {return id;}

    @Override
    public String getEmail(){return email;}

    @Override
    public String getPassword(){return password;}

    @Override
    public String getLocation(){return location;}

    @Override
    public int getIncorrectAttempt(){return incorrectAttempt;}

    @Override
    public void setIncorrectAttempt(int incorrectAttempt){this.incorrectAttempt = incorrectAttempt;}

    @Override
    public String toString() {
        return "User[id=%d, email='%s', password='%s', incorrectAttempt=%d]"
                .formatted(id, email, location, incorrectAttempt);
    }
}
