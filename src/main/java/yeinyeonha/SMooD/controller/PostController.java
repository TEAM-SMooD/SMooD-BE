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
import yeinyeonha.SMooD.dto.PostRequestDto;
import yeinyeonha.SMooD.dto.PostResponseDto;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Api(tags = {"게시글 관련 API"})
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    //게시글 생성
    @Operation(summary = "게시글 작성하기", description = "게시글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping("/post/{userId}")
    @ResponseBody
    public ResponseDto createPost(@PathVariable Long userId, @RequestBody PostRequestDto requestDto) {
        PostResponseDto postResponseDto = postService.createPost(requestDto, userId);
        return ResponseDto.success("result", postResponseDto);

    }
    //게시글 수정
    @Operation(summary = "게시글 수정하기", description = "게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공", content = @Content(schema = @Schema(implementation = PostResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/post/{postId}")
    @ResponseBody
    public ResponseDto updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        PostResponseDto postResponseDto = postService.updatePost(requestDto, postId);
        return ResponseDto.success("result", postResponseDto);
    }
    //게시글 삭제
    @Operation(summary = "게시글 삭제하기", description = "게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping("/post/{postId}")
    @ResponseBody
    public ResponseDto deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseDto.success("result", "success");
    }
    //모든 게시글 조회
    @Operation(summary = "게시글 모두 조회하기", description = "모든 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 게시글 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/posts")
    @ResponseBody
    public ResponseDto findAllPost() {
        List<PostResponseDto> posts = postService.findAllPosts();
        return ResponseDto.success("result", posts);
    }
    //게시글 단건 조회
    @Operation(summary = "특정 게시글 조회하기", description = "특정 아이디의 게시글을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 게시글 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/post/{postId}")
    @ResponseBody
    public ResponseDto findPostByPostId(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.findPostBypostId(postId);
        return ResponseDto.success("result", postResponseDto);
    }
}
