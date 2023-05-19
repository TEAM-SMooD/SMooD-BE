package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Comment;
import yeinyeonha.SMooD.domain.Post;

@Getter
public class CommentResponseDto {
    private final Long commentId;
    private final String contents;
    private final Long postId;
    private final String nickname;
    private final String date;
    private final String time;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.contents = comment.getContents();
        this.nickname = comment.getUser().getNickname();
        this.postId = comment.getPost().getId();
        this.date = comment.getModifiedDay();
        this.time = comment.getModifiedTime();
    }
}
