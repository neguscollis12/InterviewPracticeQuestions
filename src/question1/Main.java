package question1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Please enter in the number of allowed locations:");
            int locationCount = Integer.parseInt(input.readLine().trim());
            System.out.println("Please enter the location names: ");
            List<String> allowedLocations = new ArrayList<>(locationCount);
            for (int i = 0; i < locationCount; i++){
                allowedLocations.add(input.readLine().trim());
            }

            System.out.println("Please enter the number of Users");
            int userCount = Integer.parseInt(input.readLine().trim());
            List<IUser> users = new ArrayList<>(userCount);

            System.out.println("Please enter in the users information: " +
                    "example: 7, user7@email.com, 90559, location7");
            for(int i = 0; i < userCount; i++){
                String[] user = input.readLine().trim().split(",");
                users.add(new User(
                        Integer.parseInt(user[0].trim()),
                        user[1].trim(),
                        user[2].trim(),
                        user[3].trim()
                ));
            }

            ApplicationAuthState authState = new ApplicationAuthState(allowedLocations);
            StringBuilder output = new StringBuilder();

            System.out.println("Please enter in the number of operations:");
            int operationCount = Integer.parseInt(input.readLine().trim());

            System.out.println("Please enter the operation and the index: " +
                    "example: Register:1 " +
                    "Login:0 " +
                    "Logout:2");

            for(int i = 0; i < operationCount; i++){
                String [] operationInput = input.readLine().trim().split(":");
                String command = operationInput[0].trim();
                IUser user = users.get(Integer.parseInt(operationInput[1].trim()));

                String results = switch (command){
                    case "Register" -> authState.register(user);
                    case "Login" -> authState.login(user);
                    case "Logout" -> authState.logout(user);
                    default -> throw new IllegalArgumentException("Unknown command " + command);
                };

                output.append(results).append(System.lineSeparator());
            }
            System.out.println(output);
        }
    }
}
