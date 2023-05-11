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
public class ChatRoomDto {
    @Schema(description = "1", type = "Long", required = true)
    private Long id;
    @Schema(description = "소셜아이디", type = "String")
    private Long userId;
    @Schema(description = "1", type = "String")
    private String roomId;
    @Schema(description = "카페 관련 자유 톡방", type = "String")
    private String roomName;
    @Schema(description = "카페", type = "String")
    private String category;

    public ChatRoomDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.userId = chatRoom.getUserId();
        this.roomId = chatRoom.getRoomId();
        this.roomName = chatRoom.getRoomName();
        this.category = chatRoom.getCategory();
    }

}
