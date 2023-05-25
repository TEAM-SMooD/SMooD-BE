package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.RegionCategory;
import yeinyeonha.SMooD.dto.TableauRequestDto;
import yeinyeonha.SMooD.dto.TableauResponseDto;
import yeinyeonha.SMooD.repository.CategoryRepository;
import yeinyeonha.SMooD.repository.RegionCategoryRepository;
import yeinyeonha.SMooD.repository.RegionRepository;

@Service
@RequiredArgsConstructor
public class RegionCategoryService {
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final RegionCategoryRepository regionCategoryRepository;
    //지역과 카테고리별 태블로 url 가져오기
    public TableauResponseDto findTableauByRegionAndCategory(TableauRequestDto tableauRequestDto) {
        Region region = regionRepository.findRegionByDong(tableauRequestDto.getDong());
        Category category = categoryRepository.findCategoryByMiddle(tableauRequestDto.getMiddle());
        RegionCategory regionCategory = regionCategoryRepository.findRegionCategoryByRegionAndCategory(region, category);
        return new TableauResponseDto(regionCategory);
    }

}