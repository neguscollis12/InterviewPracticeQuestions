package interviews.Question1;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAuthState implements IApplicationAuthState {

    private static final int MAX_ATTEMPTS = 3;

    private final List<String> allowedLocations;
    private final List<IUser> registeredUsers;
    private final List<IUser> userLoggedIn;

    public ApplicationAuthState(List<String> allowedLocations) {
        this.allowedLocations = new ArrayList<>(allowedLocations);
        this.registeredUsers = new ArrayList<>();
        this.userLoggedIn = new ArrayList<>();
    }

    public List<String> getAllowedLocations() {return allowedLocations;}

    public List<IUser> getRegisteredUsers() {return registeredUsers;}

    public List<IUser> getUserLoggedIn() {return userLoggedIn;}

    @Override
    public String register(IUser user){
        if (findRegisteredUser(user.getEmail()) != null){
            return user.getEmail() +" is already registered!";
        }


        registeredUsers.add(user);
        return user.getEmail() + " registered successfully!";
    }

    @Override
    public String login(IUser user){
        String email = user.getEmail();

        IUser account = findRegisteredUser(email);
        if (account == null){
            return email + " is not registered!";
        }

        if(account.getIncorrectAttempt() >= MAX_ATTEMPTS){
            return email + " is blocked!";
        }

        if(!account.getPassword().equals(user.getPassword())){
            return fail(account , email + " passwords is incorrect!" );
        }

        if(!allowedLocations.contains(user.getLocation())){
            return fail(account, email + " is not allowed to login from this location!");
        }

        IUser session = findLoggedInUser(email);

        if(session != null){
            return session.getLocation().equals(user.getLocation())
                    ? fail(account , email + " is already logged in!")
                    : fail(account, email + " is already logged in from another location!");
        }

        userLoggedIn.add(user);
        account.setIncorrectAttempt(0);
        return email + " logged in successfully!";
    }

    @Override
    public String logout(IUser user){
        IUser session = findLoggedInUser(user.getEmail());
        if(session == null){
            return user.getEmail() +" is not logged in!";
        }
        userLoggedIn.remove(user);
        return user.getEmail() + " logged out successfully!";
    }

    private IUser findLoggedInUser(String email){
        return registeredUsers.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    private String fail(IUser user, String message){
        user.setIncorrectAttempt(user.getIncorrectAttempt() + 1);
        return message;
    }

    private IUser findRegisteredUser(String email){
        return registeredUsers.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
