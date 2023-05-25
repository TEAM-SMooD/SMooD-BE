package yeinyeonha.SMooD.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findAllByRegionAndCategory(Region region, Category category, Pageable pageable);
}
