package yeinyeonha.SMooD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;
    private Long category;
    private String store;
    @Column(columnDefinition = "TEXT", length = 65535)
    private String title;
    @Column(columnDefinition = "TEXT", length = 65535)
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userseq")
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(Long category, String store, String title, String contents, User user) {
        this.category = category;
        this.store = store;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void updateCategory(Long category) {
        this.category = category;
    }
    public void updateStore(String store) {
        this.store = store;
    }
    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContents(String contents) {
        this.contents = contents;
    }
}
