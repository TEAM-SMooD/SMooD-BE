package yeinyeonha.SMooD.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yeinyeonha.SMooD.exception.CustomException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static yeinyeonha.SMooD.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final ChattingRepository chattingRepository;
    private final ChatRoomRepository chatRoomRepository;
    @Transactional
    public void saveChatting(MessageDto messageDto) {
        chattingRepository.save(new ChatMessage(messageDto));
    }

    public List<MessageDto> getChatMessages(String roomId, Long id, Integer size) {
        Optional<ChatRoom> findChatroom = chatRoomRepository.findByRoomId(roomId);
        if (findChatroom.isEmpty()) {
            throw new CustomException(CHATROOM_NOT_FOUND);
        }
        List<ChatMessage> chatMessages = chattingRepository.getChattingMessages(roomId, id, size);
        return chatMessages.stream().map(MessageDto::new).collect(Collectors.toList());
    }
}
