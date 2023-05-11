package yeinyeonha.SMooD.websocket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<ChatMessage, Long>, ChattingRepositoryCustom {
//    List<ChatMessage> findTopByPjIdAAndIdLessThanOOrderByIdDesc(String pjId, Long id);
}