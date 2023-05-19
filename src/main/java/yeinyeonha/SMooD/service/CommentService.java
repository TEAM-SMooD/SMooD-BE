package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeinyeonha.SMooD.domain.Comment;
import yeinyeonha.SMooD.domain.Post;
import yeinyeonha.SMooD.domain.User;
import yeinyeonha.SMooD.dto.CommentRequestDto;
import yeinyeonha.SMooD.dto.CommentResponseDto;
import yeinyeonha.SMooD.repository.CommentRepository;
import yeinyeonha.SMooD.repository.PostRepository;
import yeinyeonha.SMooD.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //댓글 달기
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Long userId, Long postId) {
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        Comment comment = commentRepository.save(CommentRequestDto.toEntiy(commentRequestDto, user, post));
        return new CommentResponseDto(comment);
    }
    //댓글 수정
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.update(commentRequestDto.getContents());
        return new CommentResponseDto(comment);
    }
    //댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
    //특정 게시글의 모든 댓글 조회
    public List<CommentResponseDto> findCommentsByPostId(Long postId) {
        List<Comment> commentList = commentRepository.findCommentsByPostId(postId);
        return commentList.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
