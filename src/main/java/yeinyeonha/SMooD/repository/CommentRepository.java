package yeinyeonha.SMooD.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Comment;
@Primary
public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}
