package yeinyeonha.SMooD.websocket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yeinyeonha.SMooD.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    private Long senderLoginId;
    private String nickname;
    @Column(columnDefinition = "LONGTEXT")
    private String message;

    public ChatMessage(MessageDto messageDto) {
        this.senderLoginId = messageDto.getSenderLoginId();
        this.roomId = messageDto.getRoomId();
        this.nickname = messageDto.getNickname();
        this.message = messageDto.getMessage();
    }
}
