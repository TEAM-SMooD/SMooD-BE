package yeinyeonha.SMooD.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.websocket.MessageDto;
import yeinyeonha.SMooD.websocket.MessageService;

import java.util.List;
@RequiredArgsConstructor
@Controller
@Api(tags = {"Chatting 관련 API"})
public class ChatController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final MessageService messageService;

    @MessageMapping("/chatting/project")
    public void broadcastProject(MessageDto messageDto) {
        messageService.saveChatting(messageDto);
        simpMessageSendingOperations.convertAndSend(
                "/sub/chatting/" + messageDto.getRoomId(), messageDto);
    }
    @Operation(summary = "특정 채팅방 메시지들 조회", description = "채팅방의 메시지를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메시지 불러오기 성공", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/api/chatting")
    @ResponseBody
    public ResponseDto<List<MessageDto>> chatLoad(@RequestParam("roomId") String roomId,
                                                  @RequestParam(value = "id", defaultValue = Long.MAX_VALUE + "") Long id,
                                                  @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<MessageDto> messages = messageService.getChatMessages(roomId, id, size);
        return ResponseDto.success("result", messages);
    }
}

