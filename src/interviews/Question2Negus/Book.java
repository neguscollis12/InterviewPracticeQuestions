package interviews.Question2Negus;

public class Book implements IBook{

    private final int id;
    private final String title;
    private final int totalNumberOfCopies;

    private int availableCopies;

    public Book(int id, String title, int totalNumberOfCopies) {
        this.id = id;
        this.title = title;
        this.totalNumberOfCopies = totalNumberOfCopies;
        this.availableCopies = totalNumberOfCopies;
    }
    @Override
    public int getId() {return id;}
    @Override
    public String getTitle() {return title;}
    @Override
    public int getTotalNumberOfCopies() {return totalNumberOfCopies;}
    @Override
    public int getAvailableCopies() {return availableCopies;}
    @Override
    public void setAvailableCopies(int availableCopies) {this.availableCopies = availableCopies;}

}
