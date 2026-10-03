package interviews.Question2Negus;

public interface ILibrarySystem {

    String borrow(IUser user, IBook book);

    // Note: "return" is a reserved keyword in Java, so this is named returnBook.
    String returnBook(IUser user, IBook book);

    String reserve(IUser user, IBook book);
}
