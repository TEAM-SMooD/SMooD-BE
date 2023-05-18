package yeinyeonha.SMooD.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userseq")
    private User user;

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
