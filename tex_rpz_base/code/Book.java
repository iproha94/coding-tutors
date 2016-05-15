public class Book {
    private int bookId;
    private Category category;
    private String title;
    private String author;
    private int likes = 0;

    public Book(String title, String author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
}
