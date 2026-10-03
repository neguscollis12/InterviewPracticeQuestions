# Question 5 — Movie Ticket Booking System

Implement seat booking and cancellation for a single movie screening.

## Requirements

- The screening has a fixed set of seats identified by seat number (for example, 1 through N).
- A user can book any number of specific seats in one request, but the booking only succeeds if all requested seats are currently free; if any is taken, the whole booking fails (no partial booking).
- A user cannot book a seat they already hold.
- Cancelling releases only seats the requesting user currently holds; cancelling a seat not held by that user fails for that seat (and the whole cancel request fails if any seat isn't theirs).

## Messages to Return

| Status | Message |
|---|---|
| Book success | `<email> booked seats <seatList> successfully!` |
| Book fail: seat taken | `Seat <seatNumber> is already taken!` |
| Book fail: invalid seat | `Seat <seatNumber> does not exist!` |
| Cancel success | `<email> cancelled seats <seatList> successfully!` |
| Cancel fail: not owner | `<email> does not hold seat <seatNumber>!` |

## Function Description

### Screening Class — implements IScreening

- constructor: `(totalSeats)`
- property: `SeatOwners` — a map/dictionary of seat number → owning user email (or empty/null if free)

### BookingSystem Class — implements IBookingSystem

- constructor takes an `IScreening`
- `Book(IUser user, List<Integer> seatNumbers) → string`
- `Cancel(IUser user, List<Integer> seatNumbers) → string`

## Input Format for Custom Testing

```text
totalSeats
m                                  // number of users
m lines of: id, email
k                                  // number of operations
k lines of: FunctionName:userIndex:seat1,seat2,...
```

### Sample Input

```text
5
2
1, alice@email.com
2, bob@email.com
3
Book:0:1,2
Book:1:2,3
Cancel:0:1,2
```

### Sample Output

```text
alice@email.com booked seats 1,2 successfully!
Seat 2 is already taken!
alice@email.com cancelled seats 1,2 successfully!
```

## Notes

- Any booking request should validate all seat numbers before making changes.
- If any requested seat is occupied or invalid, the operation should fail without partially reserving the earlier seats.
- A cancellation request must validate ownership of each seat before releasing them; any ownership mismatch should fail the whole operation.
- Seat ownership should be tied to the user email for each seat in the screening.
