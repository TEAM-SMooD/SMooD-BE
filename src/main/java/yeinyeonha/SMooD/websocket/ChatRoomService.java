package yeinyeonha.SMooD.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yeinyeonha.SMooD.exception.CustomException;

import java.util.*;
import java.util.stream.Collectors;

import static yeinyeonha.SMooD.exception.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    //채팅방 불러오기
    @Transactional(readOnly = true)
    public List<ChatRoomResponseDto> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> list = chatRoomRepository.findAll((Sort.by(Sort.Direction.DESC, "id")));
        return list.stream().map(ChatRoomResponseDto::new).collect(Collectors.toList());
    }

    //채팅방 하나 불러오기
    @Transactional(readOnly = true)
    public ChatRoomResponseDto findById(Long id) {
        Optional<ChatRoom> findChatRoom = chatRoomRepository.findById(id);
        if (findChatRoom.isEmpty()) {
            throw new CustomException(CHATROOM_NOT_FOUND);
        }
        ChatRoom chatRoom = chatRoomRepository.findById(id).get();
        return new ChatRoomResponseDto(chatRoom);
    }
    @Transactional
    //채팅방 생성
    public ChatRoomResponseDto createRoom(ChatRequestDto chatRequestDto) {
        ChatRoom chatRoom = chatRoomRepository.save(ChatRequestDto.toEntity(chatRequestDto));
        return new ChatRoomResponseDto(chatRoom);
    }
    @Transactional
    //채팅방 삭제
    public void deleteRoom(String chatRoomId) {
        Optional<ChatRoom> findChatRoom = chatRoomRepository.findByRoomId(chatRoomId);
        if (findChatRoom.isEmpty()) {
            throw new CustomException(CHATROOM_NOT_FOUND);
        }
        chatRoomRepository.deleteChatRoomByRoomId(chatRoomId);
    }
}
