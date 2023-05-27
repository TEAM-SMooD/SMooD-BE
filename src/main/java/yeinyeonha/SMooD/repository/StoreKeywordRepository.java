package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import yeinyeonha.SMooD.domain.StoreKeyword;

import java.util.List;
public interface StoreKeywordRepository extends JpaRepository<StoreKeyword, Long>{

}
