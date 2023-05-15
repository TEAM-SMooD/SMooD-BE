package yeinyeonha.SMooD.websocket;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    private String roomName;
    private String category;
    private Long userId;

    @Builder
    public ChatRoom(String roomName, String category, Long userId) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
        this.category = category;
        this.userId = userId;
    }
}