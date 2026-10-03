package interviews.Question2Negus;

import java.util.ArrayList;
import java.util.List;

public class LibrarySystem implements ILibrarySystem{

    private static final int BORROW_LIMIT = 3;

    private final List<IBook> books;

    public LibrarySystem(List<IBook> books) {
        this.books = new ArrayList<>(books);
    }

    private IBook findBook(int id) {
        for (IBook b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    private boolean hasBorrowed(IUser user, int bookId) {
        for (IBook b : user.getBorrowedBooks()) {
            if (b.getId() == bookId) {
                return true;
            }
        }
        return false;
    }

    private boolean hasReserved(IUser user, int bookId) {
        for (IBook b : user.getReservedBooks()) {
            if (b.getId() == bookId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String borrow(IUser user, IBook book) {
        IBook systemBook = findBook(book.getId());
        if (systemBook == null) {
            return book.getTitle() + " does not exist!";
        }

        if (hasBorrowed(user, systemBook.getId())) {
            return user.getEmail() + " has already borrowed " + systemBook.getTitle() + "!";
        }

        if (user.getBorrowedBooks().size() >= BORROW_LIMIT) {
            return user.getEmail() + " has reached the borrowing limit!";
        }

        if (systemBook.getAvailableCopies() <= 0) {
            return systemBook.getTitle() + " has no copies available!";
        }

        systemBook.setAvailableCopies(systemBook.getAvailableCopies() - 1);
        user.getBorrowedBooks().add(systemBook);
        return user.getEmail() + " borrowed " + systemBook.getTitle() + " successfully!";
    }

    @Override
    public String returnBook(IUser user, IBook book) {
        IBook systemBook = findBook(book.getId());
        String title = systemBook != null ? systemBook.getTitle() : book.getTitle();

        if (!hasBorrowed(user, book.getId())) {
            return user.getEmail() + " has not borrowed " + title + "!";
        }

        user.getBorrowedBooks().removeIf(b -> b.getId() == book.getId());
        if (systemBook != null) {
            systemBook.setAvailableCopies(systemBook.getAvailableCopies() + 1);
        }
        return user.getEmail() + " returned " + title + " successfully!";
    }

    @Override
    public String reserve(IUser user, IBook book) {
        IBook systemBook = findBook(book.getId());
        if (systemBook == null) {
            return book.getTitle() + " does not exist!";
        }

        if (systemBook.getAvailableCopies() > 0) {
            return systemBook.getTitle() + " has copies available, please borrow instead!";
        }

        if (hasReserved(user, systemBook.getId())) {
            return user.getEmail() + " has already reserved " + systemBook.getTitle() + "!";
        }

        user.getReservedBooks().add(systemBook);
        return user.getEmail() + " reserved " + systemBook.getTitle() + " successfully!";
    }

}
