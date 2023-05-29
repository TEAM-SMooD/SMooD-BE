package yeinyeonha.SMooD.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //회원 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User-001", "유저를 찾을 수 없습니다."),
    USER_INFORMATION_NOT_FOUND(HttpStatus.NOT_FOUND, "User-002", "유저 정보를 가져올 수 없습니다."),
    USER_CONFLICT(HttpStatus.CONFLICT, "User-003", "이메일이 중복됩니다."),

    //게시글 관련 에러
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post-001", "게시글을 찾을 수 없습니다."),
    //댓글 관련 에러
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment-001", "댓글을 찾을 수 없습니다."),
    //키워드 관련 에러
    KEYWORD_NOT_FOUND(HttpStatus.NOT_FOUND, "Keyword-001", "키워드에 맞는 가게 정보가 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "Store-001", "가게 정보가 없습니다."),
    CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "Chat-001", "채팅방 정보가 없습니다."),
    ;

    private final HttpStatus httpStatus;    // HttpStatus
    private final String code;                // ACCOUNT-001
    private final String message;            // 설명

}
