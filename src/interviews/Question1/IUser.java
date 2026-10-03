package interviews.Question1;

interface IUser {
    int getId();
    String  getEmail();
    String getPassword();
    String getLocation();
    int getIncorrectAttempt();
    void setIncorrectAttempt(int incorrectAttempt);
}
