package yeinyeonha.SMooD.websocket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequestDto {
    @Schema(description = "소셜아이디", type = "String")
    private Long userId;
    @Schema(description = "카페 관련 자유 톡방", type = "String")
    private String roomName;
    @Schema(description = "카페", type = "String")
    private String category;

    public ChatRequestDto(ChatRoom chatRoom) {
        this.userId = chatRoom.getUserId();
        this.roomName = chatRoom.getRoomName();
        this.category = chatRoom.getCategory();
    }

    public static ChatRoom toEntity(ChatRequestDto ChatRequestDto) {
        return ChatRoom.builder()
                .category(ChatRequestDto.getCategory())
                .roomName(ChatRequestDto.getRoomName())
                .userId(ChatRequestDto.getUserId())
                .build();
    }
}
