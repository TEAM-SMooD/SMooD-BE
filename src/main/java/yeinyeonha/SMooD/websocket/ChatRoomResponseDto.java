package yeinyeonha.SMooD.websocket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomResponseDto {
    @Schema(description = "1", type = "Long")
    private Long id;
    @Schema(description = "채팅방아이디", type = "String")
    private String roomId;
    @Schema(description = "소셜아이디", type = "String")
    private Long userId;
    @Schema(description = "카페 관련 자유 톡방", type = "String")
    private String roomName;
    @Schema(description = "카페", type = "String")
    private String category;

    @Builder
    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.roomId = chatRoom.getRoomId();
        this.userId = chatRoom.getUserId();
        this.roomName = chatRoom.getRoomName();
        this.category = chatRoom.getCategory();
    }
}
