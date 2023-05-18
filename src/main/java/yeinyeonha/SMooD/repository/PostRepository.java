package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
