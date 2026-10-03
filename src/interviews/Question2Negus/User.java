package interviews.Question2Negus;

import java.util.ArrayList;
import java.util.List;

public class User implements IUser {
    private final int id;
    private final String email;

    List<IBook> borrowedBooks;
    List<IBook> reservedBooks;

    public User(int id, String email) {
        this.id = id;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }
    @Override
    public int getId() {return id;}
    @Override
    public String getEmail() {return email;}
    @Override
    public List<IBook> getBorrowedBooks() {return borrowedBooks;}
    @Override
    public List<IBook> getReservedBooks() {return reservedBooks;}
}
