package yeinyeonha.SMooD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yeinyeonha.SMooD.oauth.ProviderType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "userseq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
    @Column(name = "userId", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;
    @Column(name = "username", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;
    @Column(name = "nickname", length = 100)
    @NotNull
    @Size(max = 100)
    private String nickname;
    @JsonIgnore
    @Column(name = "password", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;
    @Column(name = "email", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;
    @Column(name = "emailverified", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;
    @Column(name = "profileimage", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;
    @Column(name = "providertype", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;
    @Column(name = "roletype", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;
    @Column(name = "createat")
    @NotNull
    private LocalDateTime createdAt;
    @Column(name = "modifiedat")
    @NotNull
    private LocalDateTime modifiedAt;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 100) String nickname,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.userId = userId;
        this.username = username;
        this.nickname = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
