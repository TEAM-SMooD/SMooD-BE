package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final Long postId;
    private final Long parentId;
    private final String contents;
    private final String nickname;
    private final String date;
    private final String time;
    private final Boolean isDeleted;
    private final List<CommentResponseDto> children = new ArrayList<>();

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.nickname = comment.getUser().getNickname();
        this.postId = comment.getPost().getId();
        this.parentId = comment.getParent() == null ? null : comment.getParent().getId();
        this.date = comment.getModifiedDay();
        this.time = comment.getModifiedTime();
        this.isDeleted = comment.getIsDeleted();
    }
}
