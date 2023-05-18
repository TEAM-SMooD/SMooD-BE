package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Post;

@Getter
public class PostResponseDto {
    private final Long postId;
    private final Long category;
    private final String store;
    private final String title;
    private final String contents;
    private final String nickname;
    private final String date;
    private final String time;

    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.category = post.getCategory();
        this.store = post.getStore();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.nickname = post.getUser().getNickname();
        this.date = post.getModifiedDay();
        this.time = post.getModifiedTime();
    }
}
