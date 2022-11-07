package relations.oneToMany;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public Post() {
    }

    public Post(String text) {
        this();
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
