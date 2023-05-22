package yeinyeonha.SMooD.repository;

import yeinyeonha.SMooD.domain.Comment;

import java.util.List;

public interface CustomCommentRepository {
    List<Comment> findCommentByPostId(Long postId);
}
