package yeinyeonha.SMooD.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String senderLoginId;
    private String nickname;
    private String message;
    private String roomId;
    private LocalDateTime dateTime;

    public MessageDto(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.senderLoginId = chatMessage.getSenderLoginId();
        this.nickname = chatMessage.getNickname();
        this.roomId = chatMessage.getRoomId();
        this.message = chatMessage.getMessage();
        this.dateTime = chatMessage.getCreatedTime();
    }
}
