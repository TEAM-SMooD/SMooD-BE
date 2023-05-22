package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yeinyeonha.SMooD.domain.User;
import yeinyeonha.SMooD.dto.UserNicknameRequestDto;
import yeinyeonha.SMooD.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
    @Transactional
    public User updateNickname(String userId, UserNicknameRequestDto requestDto) {
        User user = userRepository.findByUserId(userId);
        user.setNickname(requestDto.getNickname());
        return user;
    }
}
