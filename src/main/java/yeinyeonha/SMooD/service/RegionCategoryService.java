package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.RegionCategory;
import yeinyeonha.SMooD.dto.StoreAnalysisDto;
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
    public TableauResponseDto findTableauByRegionAndCategory(String dong, String middle) {
        Region region = regionRepository.findRegionByDong(dong);
        Category category = categoryRepository.findCategoryByMiddle(middle);
        RegionCategory regionCategory = regionCategoryRepository.findByRegionAndCategory(region, category);
        return new TableauResponseDto(regionCategory);
    }
    //지역과 카테고리에 따른 상권분석 태블로 조회
    public StoreAnalysisDto findAnalysis(String dong, String middle) {
        Region region = regionRepository.findRegionByDong(dong);
        Category category = categoryRepository.findCategoryByMiddle(middle);
        RegionCategory regionCategory = regionCategoryRepository.findByRegionAndCategory(region, category);
        return new StoreAnalysisDto(regionCategory);
    }
}