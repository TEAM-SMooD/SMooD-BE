package yeinyeonha.SMooD.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.Store;

import java.util.List;

@Primary
public interface StoreRepository extends JpaRepository<Store, Long>, CustomStoreRepository {
//    Page<Store> findAllByRegionAndCategory(Region region, Category category, Pageable pageable);
//    Page<Store> findAllByRegionAndCategoryAndRep(Region region, Category category, Boolean rep, Sort sort);
//    Store findByRegionAndCategory(Region region, Category category);
    List<Store> findStoresByRegionAndCategory(Region region, Category category);
}
