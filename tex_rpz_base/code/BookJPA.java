public class Book {
    @Id
    @Column(name = "BOOKID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    private Category category;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "LIKES")
    private int likes = 0;

    public Book(String title, String author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
}
