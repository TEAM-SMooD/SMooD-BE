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
import yeinyeonha.SMooD.dto.CommentRequestDto;
import yeinyeonha.SMooD.dto.CommentResponseDto;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.service.CommentService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Api(tags = {"댓글 관련 API"})
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    //댓글 생성
    @Operation(summary = "댓글 작성하기", description = "댓글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 작성 성공", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping("/comment/{userId}/{postId}")
    @ResponseBody
    public ResponseDto createComment(@PathVariable Long userId, @PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(requestDto, userId, postId);
        return ResponseDto.success("result", commentResponseDto);
    }
    //대댓글 생성
    @Operation(summary = "대댓글 작성하기", description = "대댓글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "대댓글 작성 성공", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping("/comment/reply/{userId}/{postId}/{commentId}")
    @ResponseBody
    public ResponseDto createReplyComment(@PathVariable Long userId, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.createReplyComment(requestDto, userId, postId, commentId);
        return ResponseDto.success("result", commentResponseDto);
    }
    //댓글 수정
    @Operation(summary = "댓글 수정하기", description = "댓글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 수정 성공", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/comment/{commentId}")
    @ResponseBody
    public ResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(requestDto, commentId);
        return ResponseDto.success("result", commentResponseDto);
    }
    //댓글 삭제
    @Operation(summary = "댓글 삭제하기", description = "댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping("/comment/{commentId}")
    @ResponseBody
    public ResponseDto deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseDto.success("result", "success");
    }
    //특정 게시글 댓글 조회
    @Operation(summary = "특정 게시글 댓글 조회하기", description = "특정 게시글에 달린 댓글을 모두 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/comments/{postId}")
    @ResponseBody
    public ResponseDto findCommentsByPostId(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.findCommentsByPostId(postId);
        return ResponseDto.success("result", comments);
    }
}
