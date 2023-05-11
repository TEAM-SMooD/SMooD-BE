package yeinyeonha.SMooD.websocket;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static yeinyeonha.SMooD.websocket.QChatMessage.chatMessage;

@RequiredArgsConstructor
public class ChattingRepositoryImpl implements ChattingRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ChatMessage> getChattingMessages(String roomId, Long id, Integer size) {
        return jpaQueryFactory.selectFrom(chatMessage)
                .where(chatMessage.roomId.eq(roomId).and(chatMessage.id.lt(id)))
                .orderBy(chatMessage.id.desc())
                .limit(size)
                .fetch();
    }
}
