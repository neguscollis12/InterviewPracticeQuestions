package interviews.Question2Negus;

public interface IBook {
    int getId();
    String getTitle();
    int getTotalNumberOfCopies();
    int getAvailableCopies();
    void setAvailableCopies(int availableCopies);
}
