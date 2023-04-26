package yeinyeonha.SMooD.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yeinyeonha.SMooD.domain.Role;

@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private Role role;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponseDto(Long id, String name, String email, String imageUrl, Role role, String tokenType, String accessToken, String refreshToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}