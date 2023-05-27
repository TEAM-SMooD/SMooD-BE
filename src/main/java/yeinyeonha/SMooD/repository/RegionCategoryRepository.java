package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.RegionCategory;

public interface RegionCategoryRepository extends JpaRepository<RegionCategory, Long> {
    RegionCategory findByRegionAndCategory(Region region, Category category);
}
