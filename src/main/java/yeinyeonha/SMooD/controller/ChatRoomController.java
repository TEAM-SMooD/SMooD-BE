package yeinyeonha.SMooD.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.websocket.ChatRoom;
import yeinyeonha.SMooD.websocket.ChatRoomDto;
import yeinyeonha.SMooD.websocket.ChatRoomService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Api(tags = {"Chatting 관련 API"})
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    @Operation(summary = "모든 채팅방 조회", description = "채팅방 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/rooms")
    @ResponseBody
    public ResponseDto room() {
        List<ChatRoom> chatRooms = chatRoomService.findAllRoom();
        return ResponseDto.success("chatRooms", chatRooms);
    }
    @Operation(summary = "채팅방 생성", description = "채팅방을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 생성 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ResponseDto createRoom(@RequestBody ChatRoomDto chatRoomDto) {
        return ResponseDto.success("chatRoom", chatRoomService.createRoom(chatRoomDto));
    }
    @Operation(summary = "특정 채팅방 조회", description = "특정 채팅방을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ResponseDto roomInfo(@PathVariable String roomId) {
        return ResponseDto.success("chatRoom", chatRoomService.findById(roomId));
    }
}