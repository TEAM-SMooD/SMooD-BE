package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauthId(String id);
}