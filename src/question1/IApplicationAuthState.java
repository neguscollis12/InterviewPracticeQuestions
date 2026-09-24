package question1;

interface IApplicationAuthState {
    String register(IUser user);
    String login(IUser user);
    String logout(IUser user);
}
