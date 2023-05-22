package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}
