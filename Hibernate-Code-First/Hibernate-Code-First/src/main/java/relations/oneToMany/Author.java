package relations.oneToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "author" , targetEntity = Post.class,
    fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Post> posts;

    public Author() {
        this.posts = new ArrayList<>();
    }

    public Author(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
