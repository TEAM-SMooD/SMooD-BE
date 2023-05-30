package yeinyeonha.SMooD.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Keyword;

@Primary
public interface KeywordRepository extends JpaRepository<Keyword, Long>, CustomKeywordRepository {
}
