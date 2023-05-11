package yeinyeonha.SMooD.websocket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yeinyeonha.SMooD.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String roomId;
    private String senderLoginId;
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
