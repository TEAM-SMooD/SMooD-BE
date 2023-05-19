package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostId(Long postId);
}
