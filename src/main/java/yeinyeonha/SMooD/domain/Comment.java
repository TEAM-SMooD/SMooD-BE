package yeinyeonha.SMooD.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;
    @Column(columnDefinition = "TEXT", length = 65535)
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userseq")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postId")
    private Post post;
    @ColumnDefault("FALSE")
    @Column(nullable = false)
    private Boolean isDeleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();
    @Builder
    public Comment(String contents, User user, Post post) {
        this.contents = contents;
        this.user = user;
        this.post = post;
        this.isDeleted = false;
    }

    public void updatecontents(String contents) {
        this.contents = contents;
    }
    public void updateIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public void updateParent(Comment parent) {
        this.parent = parent;
    }
}
