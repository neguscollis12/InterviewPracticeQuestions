# Question 6 — Chat Room Membership System

Implement join, leave, and message-sending operations for chat rooms with a max-capacity rule.

## Requirements

- Each room has a maximum capacity.
- A user can join a room only if it's not full and they're not already a member of that room.
- A user can be a member of multiple rooms at once.
- A user can send a message to a room only if they are currently a member of it.
- Leaving a room only succeeds if the user is currently a member.

## Messages to Return

| Status | Message |
|---|---|
| Join success | `<email> joined <roomName> successfully!` |
| Join fail: already member | `<email> is already a member of <roomName>!` |
| Join fail: room full | `<roomName> is full!` |
| Leave success | `<email> left <roomName> successfully!` |
| Leave fail: not member | `<email> is not a member of <roomName>!` |
| Send success | `<email> sent a message in <roomName>!` |
| Send fail: not member | `<email> cannot send messages in <roomName>!` |

## Function Description

### Room Class — implements IRoom

- constructor: `(name, capacity)`
- properties: `Name`, `Capacity`, `Members` (list of users)

### ChatSystem Class — implements IChatSystem

- constructor takes a list of `IRoom`
- `Join(IUser user, IRoom room) → string`
- `Leave(IUser user, IRoom room) → string`
- `SendMessage(IUser user, IRoom room, String message) → string`

## Input Format for Custom Testing

```text
n                                  // number of rooms
n lines of: name, capacity
m                                  // number of users
m lines of: id, email
k                                  // number of operations
k lines of: FunctionName:userIndex:roomIndex(:message)
```

### Sample Input

```text
1
general, 1
2
1, alice@email.com
2, bob@email.com
4
Join:0:0
Join:1:0
SendMessage:0:0:hi
SendMessage:1:0:hello
```

### Sample Output

```text
alice@email.com joined general successfully!
general is full!
alice@email.com sent a message in general!
bob@email.com cannot send messages in general!
```

## Notes

- Membership is tracked per user and per room; a user can join multiple rooms simultaneously.
- A room is considered full when the number of members reaches its capacity.
- Send-message checks should be based only on current room membership, not on past joins or pending invites.
- Leaving a room should only remove the user from that specific room and not affect membership in other rooms.
