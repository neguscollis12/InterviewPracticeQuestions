package interviews.Question2Negus;

import java.util.List;

public interface IUser {
    int getId();
    String getEmail();
    List<IBook> getBorrowedBooks();
    List<IBook> getReservedBooks();
}
