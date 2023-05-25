package yeinyeonha.SMooD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yeinyeonha.SMooD.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByMiddle(String middle);
}
