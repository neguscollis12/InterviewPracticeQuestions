package interviews.Question1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Please enter the number of allowed locations:");
            int locationCount = Integer.parseInt(in.readLine().trim());
            System.out.println("Please enter the location:");
            List<String> allowedLocations = new ArrayList<>(locationCount);
            for (int i = 0; i < locationCount; i++) {
                allowedLocations.add(in.readLine().trim());
            }

            System.out.println("Please enter the number of allowed users");
            int userCount = Integer.parseInt(in.readLine().trim());
            List<IUser> users = new ArrayList<>(userCount);
            System.out.println("Please enter the users information: " +
                    "ie. 13,user13@email.com,password13,location13");
            for (int i = 0; i < userCount; i++) {
                String[] parts = in.readLine().trim().split(",");
                users.add(new User(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim()));
            }

            ApplicationAuthState authState = new ApplicationAuthState(allowedLocations);
            StringBuilder out = new StringBuilder();

            System.out.println("Please enter the of operations:");
            int operationCount = Integer.parseInt(in.readLine().trim());
            System.out.println("Please enter the operation and the index:" +
                    "ie. Register:1 " +
                    "Login:0 " +
                    "Logout:1");
            for (int i = 0; i < operationCount; i++) {
                String[] parts = in.readLine().trim().split(":");
                String command = parts[0].trim();
                IUser user = users.get(Integer.parseInt(parts[1].trim()));

                String result = switch (command) {
                    case "Register" -> authState.register(user);
                    case "Login" -> authState.login(user);
                    case "Logout" -> authState.logout(user);
                    default -> throw new IllegalArgumentException("Unknown command: " + command);
                };

                out.append(result).append(System.lineSeparator());
            }

            System.out.print(out);
        }
    }

}

