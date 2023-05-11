package yeinyeonha.SMooD.websocket;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long id;
    private String roomId;
    private String roomName;
    private String category;
    private Long userId;

    public static ChatRoom create(ChatRoomDto chatRoomDto) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = chatRoomDto.getRoomName();
        room.category = chatRoomDto.getCategory();
        room.userId = chatRoomDto.getUserId();
        return room;
    }
}