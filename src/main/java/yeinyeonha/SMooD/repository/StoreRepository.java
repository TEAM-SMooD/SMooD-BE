package yeinyeonha.SMooD.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.Store;

import java.util.List;

@Primary
public interface StoreRepository extends JpaRepository<Store, Long>, CustomStoreRepository {
    List<Store> findStoresByRegionAndCategory(Region region, Category category);
}
