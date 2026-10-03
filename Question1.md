# Question 1 - Website Authentication Implementation

Implement three functions to enable users to register, log in, and log out of a website. Refer to the table for the messages to return.

## Requirements

- Users will sign up with their email, password, and location details.
- Users can register from any location.
- There is a list of permitted locations.
- Users log in using their email address and password.
- Users can only log in from an allowed location.
- If the email and password are correct, the login attempt will fail under any of the following conditions:
  - The user is already logged in
  - The user is logged in from a different location
  - The user attempts to log in from a disallowed location
  - The user is blocked
- A user is blocked after three consecutive failed login attempts.
- The attempt count resets to zero upon successful login.

## Messages to Return

| Status | Message |
|---|---|
| Register success | `<email> registered successfully!` |
| Register fail: not registered | `<email> is not registered!` |
| Register fail: already registered | `<email> is already registered!` |
| Login success | `<email> logged in successfully!` |
| Login fail: not registered | `<email> is not registered!` |
| Login fail: already logged in | `<email> is already logged in!` |
| Login fail: logged in from another location | `<email> is already logged in from another location!` |
| Login fail: blocked | `<email> is blocked!` |
| Login fail: location not allowed | `<email> is not allowed to login from this location!` |
| Login fail: password incorrect | `<email> password is incorrect!` |
| Logout success | `<email> logged out successfully!` |
| Logout fail: not logged in | `<email> is not logged in!` |

## Example Commands

Given the allowed locations = `["Location1", "Location2"]` and the following commands:

| Commands | Output |
|---|---|
| Register `User1@email.com` with location `Location1` | `User1@email.com registered successfully!` |
| Login `User1@email.com` from `Location1` | `User1@email.com logged in successfully!` |
| Login `User1@email.com` from `Location2` | `User1@email.com is already logged in from another location!` |
| Login `User2@email.com` without registration | `User2@email.com is not registered!` |

## Function Description

### User Class

Create the `User` class and implement the `IUser` interface.

This class should have:
- a constructor which takes `id`, `email`, `password`, and `location`
- properties: `Id`, `Email`, `Password`, `Location`, and `IncorrectAttempt`
- these properties are initialized with the values passed by the constructor
- `IncorrectAttempt` is initialized to `0`

### ApplicationAuthState Class

Create the `ApplicationAuthState` class and implement the `IApplicationAuthState` interface.

This class should have:
- a constructor which
  - takes a list of allowed locations
  - initializes the `AllowedLocations` property with the provided list
  - initializes the `RegisteredUsers` and `UsersLoggedIn` properties to new instances of `List<IUser>`

#### Public Register Method

The method takes a parameter `user` of type `IUser` and returns a `string`.

It verifies:
- that the user is not yet registered
- if the user is not yet registered:
  - add the user to `RegisteredUsers`
  - return the success message
- if the user is already registered:
  - return the appropriate message

#### Public Login Method

The method takes a parameter `user` of type `IUser` and returns a `string`.

It verifies:
- that the provided email is registered
- that the user does not have too many failed login attempts
- that the password is correct
- that the user is not logged in already

If all tests pass:
- add the user to the `UsersLoggedIn` list
- reset the `IncorrectAttempt` property to `0`
- return the success message

Otherwise:
- increment the `IncorrectAttempt` property
- return the appropriate message

#### Public Logout Method

The method takes a parameter `user` of type `IUser` and returns a `string`.

It verifies:
- that the user is logged in

If the user is logged in:
- remove the user from the `UsersLoggedIn` list
- return the success message

If the user is not logged in:
- return the appropriate message

## Input Format for Custom Testing

The first line contains an integer `n`, the number of allowed locations.
Each of the next `n` lines contains the allowed location name.
The next line contains an integer `m`, the number of users.
Each of the next `m` lines contains the `(Id, Email, Password, Location)` of the user information.
The next line contains an integer `k`, the number of operations.
Each of the next `k` lines contains the function name and user index separated by `:`.

### Sample Input

```text
2
location1
location2
3
7, user7@email.com, 90559, location7
10, user10@email.com, 41853, location10
2, user2@email.com, 80573, location2
5
Register: 1
Register: 1
Login: 0
Login: 1
Register: 2
```

### Sample Output

```text
user10@email.com registered successfully!
user10@email.com is already registered!
user7@email.com is not registered!
user10@email.com is not allowed to login from this location!
user2@email.com registered successfully!
```

### Explanation

AllowedLocations = `['location1', 'location2']`.
3 users are added, then 5 operations are requested.

## Additional Sample Case

### Sample Input

```text
Location4
16, user16@email.com, 14165, Location16
8, user8@email.com, 89680, Location8
17, user17@email.com, 26883, Location17
16, user16@email.com, 36862, location16
10
Register: 2
Register: 2
Login: 1
Register: 2
Login: 0
Login: 0
Login: 0
Register: 2
Register: 1
Register: 2
```

### Sample Output

```text
user17@email.com registered successfully!
user17@email.com is already registered!
user8@email.com is not registered!
user17@email.com is already registered!
user16@email.com is not registered!
user16@email.com is not registered!
user16@email.com is not registered!
user17@email.com is already registered!
user8@email.com registered successfully!
user17@email.com is already registered!
```

## Notes

- Registration is independent of location.
- A user can be registered from any location.
- Login is restricted to allowed locations and valid credentials.
- Repeated failed logins can block the account after three consecutive failures.
- A successful login resets failed attempts.
- A user cannot log in again while already logged in from the same location or another location.
