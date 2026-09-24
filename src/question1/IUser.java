package question1;

interface IUser {
    int getId();
    String getEmail();
    String getPassword();
    String getLocation();
    int getIncorrectAttempts();
    void setIncorrectAttempts(int incorrectAttempts);

}
