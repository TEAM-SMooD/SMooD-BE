package yeinyeonha.SMooD.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yeinyeonha.SMooD.domain.Post;
import yeinyeonha.SMooD.domain.User;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private Long category;
    private String store;
    private String title;
    private String contents;

    @Builder
    public PostRequestDto(Long category, String store, String title, String contents) {
        this.category = category;
        this.store = store;
        this.title = title;
        this.contents = contents;
    }

    public static Post toEntiy(PostRequestDto postRequestDto, User user) {
        return Post.builder()
                .category(postRequestDto.getCategory())
                .store(postRequestDto.getStore())
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContents())
                .user(user)
                .build();
    }
}
