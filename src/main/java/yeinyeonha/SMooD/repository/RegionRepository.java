package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findRegionByDong(String dong);
}
