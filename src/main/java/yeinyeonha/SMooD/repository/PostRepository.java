package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByCategory(Long category);
}
