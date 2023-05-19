package yeinyeonha.SMooD.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yeinyeonha.SMooD.domain.Comment;
import yeinyeonha.SMooD.domain.Post;
import yeinyeonha.SMooD.domain.User;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String contents;
    @Builder
    public CommentRequestDto(String contents) {
        this.contents = contents;
    }

    public static Comment toEntiy(CommentRequestDto commentRequestDto, User user, Post post) {
        return Comment.builder()
                .contents(commentRequestDto.getContents())
                .user(user)
                .post(post)
                .build();
    }
}
