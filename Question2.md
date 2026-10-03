# Question 2 — Library Book Borrowing System

Implement a system that lets users borrow and return books from a library, and allows the library to reserve books that are checked out.

## Requirements

- Each book has a unique ID, title, and total copies available.
- Users borrow books by ID; a user can borrow a book only if a copy is available.
- A user cannot borrow the same book twice while already holding a copy.
- A user cannot borrow more than 3 books at a time.
- If a book has zero copies available, a user may reserve it instead. A reservation is only allowed if the user hasn't already reserved that book.
- When a book is returned, if there's a reservation queue for it, the reservation is not automatically converted to a loan — the returning simply frees a copy.
- Returning a book only succeeds if the user currently holds that book.

## Messages to Return

|Status	| Message |
|---|---|
| Borrow success	|`<email> borrowed <title> successfully! `|
| Borrow fail: book not found	| `<title> does not exist! `| 
| Borrow fail: no copies available	| `<title> has no copies available!` |
| Borrow fail: already borrowed	| `<email> has already borrowed <title>! `|
| Borrow fail: limit reached	| `<email> has reached the borrowing limit!` |
| Return success |	`<email> returned <title> successfully! `|
| Return fail: not borrowed	 | `<email> has not borrowed <title>! `|
| Reserve success	| `<email> reserved <title> successfully!` | 
| Reserve fail: copies available	|` <title> has copies available, please borrow instead!` |
| Reserve fail: already reserved	| `<email> has already reserved <title>!` | 

## Function Description

### Book Class — implements IBook

- constructor: (id, title, totalCopies)
- properties: Id, Title, TotalCopies, AvailableCopies (initialized to TotalCopies)

User Class — implements IUser

- constructor: (id, email)
- properties: Id, Email, BorrowedBooks (list), ReservedBooks (list)

LibrarySystem Class — implements ILibrarySystem

- constructor takes a list of IBook
- Borrow(IUser user, IBook book) → string
- Return(IUser user, IBook book) → string
- Reserve(IUser user, IBook book) → string

Input Format for Custom Testing
```text
n                                  // number of books
n lines of: id, title, totalCopies
m                                  // number of users
m lines of: id, email
k                                  // number of operations
k lines of: FunctionName:userIndex:bookIndex
```
Sample Input
```text
2
1, Java Basics, 1
2, Design Patterns, 0
2
1, alice@email.com
2, bob@email.com
4
Borrow:0:0
Borrow:1:0
Reserve:1:1
Reserve:1:1
```
Sample Output
```text
alice@email.com borrowed Java Basics successfully!
Java Basics has no copies available!
bob@email.com reserved Design Patterns successfully!
bob@email.com has already reserved Design Patterns!
```