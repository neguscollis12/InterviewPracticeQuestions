package question1;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAuthState implements IApplicationAuthState{
    private static final int MAX_ATTEMPTS = 3;

    private final List<String> allowedLocations;
    private final List<IUser> registeredUsers;
    private final List<IUser> usersLoggedIn;


    public ApplicationAuthState(List<String> allowedLocations){
        this.allowedLocations = allowedLocations;
        this.registeredUsers = new ArrayList<>();
        this.usersLoggedIn = new ArrayList<>();
    }

    @Override
    public String register(IUser user){
        if(findRegisteredUser(user.getEmail()) != null){
            return user.getEmail() + " is already registered!";
        }

        registeredUsers.add(user);
        return user.getEmail() + " registered successfully!";
    }

    @Override
    public String login(IUser user){
        String email = user.getEmail();

        IUser account = findRegisteredUser(email);
        if(account == null){
            return email + " is not registered!";
        }

        if(account.getIncorrectAttempts() >= MAX_ATTEMPTS){
            return email + " is blocked!";
        }

        if(!account.getPassword().equals(user.getPassword())){
            return failLogin(account, email + " password is incorrect!");
        }

        if(!allowedLocations.contains(user.getLocation())){
            return failLogin(account, email + " is not allowed to login from this location!");
        }

        IUser session = findLoggedInUsers(email);

        if(session != null){
            return session.getLocation().equals(user.getLocation())
                    ? failLogin(account, email + " is already logged in!")
                    : failLogin(account, email + " is already logged in from another location!");
        }

        usersLoggedIn.add(user);
        account.setIncorrectAttempts(0);
        return email + " logged in successfully!";


    }

    @Override
    public String logout(IUser user){
        IUser session = findLoggedInUsers(user.getEmail());
        if(session == null){
            return user.getEmail() + " is not logged in!";
        }

        usersLoggedIn.remove(user);
        return user.getEmail() + " logged out successfully!";
    }

    private String failLogin(IUser user, String message){
        user.setIncorrectAttempts(user.getIncorrectAttempts() + 1);
        return message;
    }

    private IUser findRegisteredUser(String email){
        return registeredUsers.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    private IUser findLoggedInUsers(String email){
        return usersLoggedIn.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }


}
