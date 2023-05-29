package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yeinyeonha.SMooD.domain.Comment;
import yeinyeonha.SMooD.domain.Post;
import yeinyeonha.SMooD.domain.User;
import yeinyeonha.SMooD.dto.CommentRequestDto;
import yeinyeonha.SMooD.dto.CommentResponseDto;
import yeinyeonha.SMooD.exception.CustomException;
import yeinyeonha.SMooD.repository.CommentRepository;
import yeinyeonha.SMooD.repository.CustomCommentRepository;
import yeinyeonha.SMooD.repository.PostRepository;
import yeinyeonha.SMooD.repository.UserRepository;

import java.util.*;

import static yeinyeonha.SMooD.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CustomCommentRepository customCommentRepository;
    //최상위 댓글 달기
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Long userId, Long postId) {
        Optional<User> findUser = userRepository.findById(userId);
        Optional<Post> findPost = postRepository.findById(postId);
        if (findUser.isEmpty()) { //회원 정보가 없을 때
            throw new CustomException(USER_NOT_FOUND);
        } else if (findPost.isEmpty()) { //게시글 정보가 없을 때
            throw new CustomException(POST_NOT_FOUND);
        }
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        Comment comment = commentRepository.save(CommentRequestDto.toEntiy(commentRequestDto, user, post));
        return new CommentResponseDto(comment);
    }
    //대댓글 달기
    @Transactional
    public CommentResponseDto createReplyComment(CommentRequestDto commentRequestDto, Long userId, Long postId, Long parentId) {
        Optional<User> findUser = userRepository.findById(userId);
        Optional<Post> findPost = postRepository.findById(postId);
        Optional<Comment> findparent = commentRepository.findById(parentId);
        if (findUser.isEmpty()) { //회원 정보가 없을 때
            throw new CustomException(USER_NOT_FOUND);
        } else if (findPost.isEmpty()) { //게시글 정보가 없을 때
            throw new CustomException(POST_NOT_FOUND);
        } else if (findparent.isEmpty()) {
            throw new CustomException(COMMENT_NOT_FOUND);
        }
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        Comment parent = commentRepository.findById(parentId).get();
        Comment comment = commentRepository.save(CommentRequestDto.toEntiy(commentRequestDto, user, post));
        comment.updateParent(parent);
        return new CommentResponseDto(comment);
    }
    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);
        if (findComment.isEmpty()) {
            throw new CustomException(COMMENT_NOT_FOUND);
        }
        Comment comment = commentRepository.findById(commentId).get();
        comment.updatecontents(commentRequestDto.getContents());
        return new CommentResponseDto(comment);
    }
    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);
        if (findComment.isEmpty()) {
            throw new CustomException(COMMENT_NOT_FOUND);
        }
        Comment comment = commentRepository.findById(commentId).get();
        if (comment.getChildren().size() != 0) {
            comment.updateIsDeleted(true);
            comment.updatecontents("삭제된 댓글입니다.");
        } else {
            commentRepository.delete(getDeletableAncestorComment(comment));
        }
    }
    //삭제 가능한 조상 댓글을 구함
    @Transactional
    public Comment getDeletableAncestorComment(Comment comment) {
        Comment parent = comment.getParent();
        if(parent != null && parent.getChildren().size() == 1 && parent.getIsDeleted()) {
            return getDeletableAncestorComment(parent);
        }
        return comment;
    }
    @Transactional(readOnly = true)
    //특정 게시글에 달린 모든 댓글 조회
    public List<CommentResponseDto> findCommentsByPostId(Long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isEmpty()) {
            throw new CustomException(POST_NOT_FOUND);
        }
        List<Comment> commentList = customCommentRepository.findCommentByPostId(postId);
        List<CommentResponseDto> result = new ArrayList<>();
        Map<Long, CommentResponseDto> map = new HashMap<>();
        commentList.stream().forEach(c -> {
            CommentResponseDto cdto = new CommentResponseDto(c);
            map.put(cdto.getId(), cdto);
            if (c.getParent() != null) map.get(c.getParent().getId()).getChildren().add(cdto);
            else result.add(cdto);
        });
        return result;
    }
}
