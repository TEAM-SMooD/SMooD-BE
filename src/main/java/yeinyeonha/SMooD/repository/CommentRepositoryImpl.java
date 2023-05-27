package yeinyeonha.SMooD.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import yeinyeonha.SMooD.domain.Comment;

import java.util.List;
import static yeinyeonha.SMooD.domain.QComment.*;
@Primary
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CustomCommentRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        return jpaQueryFactory.selectFrom(comment)
                .leftJoin(comment.parent)
                .fetchJoin()
                .where(comment.post.id.eq(postId))
                .orderBy(comment.parent.id.asc().nullsFirst(), comment.createdDay.asc())
                .fetch();
    }
}
