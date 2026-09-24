package question1;

public class User implements IUser{

    private final int id;
    private final String email;
    private final String password;
    private final String location;
    private int incorrectAttempts;

    public User(int id, String email, String password, String location){
        this.id = id;
        this.email = email;
        this.password = password;
        this.location = location;
        incorrectAttempts = 0;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getEmail() { return email; }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getLocation() { return location; }

    @Override
    public int getIncorrectAttempts() { return incorrectAttempts; }

    @Override
    public void setIncorrectAttempts(int incorrectAttempts) {this.incorrectAttempts = incorrectAttempts;}

    @Override
    public String toString() {
        return "User[id=%d, email='%s', location='%s', incorrectAttempts=%d]"
                .formatted(id,email,location,incorrectAttempts);
    }

}
