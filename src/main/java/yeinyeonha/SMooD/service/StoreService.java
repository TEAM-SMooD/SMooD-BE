package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yeinyeonha.SMooD.domain.Category;
import yeinyeonha.SMooD.domain.Region;
import yeinyeonha.SMooD.domain.Store;
import yeinyeonha.SMooD.dto.StoreResponseDto;
import yeinyeonha.SMooD.repository.CategoryRepository;
import yeinyeonha.SMooD.repository.RegionRepository;
import yeinyeonha.SMooD.repository.StoreRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    //카테고리별 상위 가게 정보 조회
    public List<StoreResponseDto> findStoreByCategory(String sorting, String dong, String middle) {
        Region region = regionRepository.findRegionByDong(dong);
        Category category = categoryRepository.findCategoryByMiddle(middle);
        if (Objects.equals(sorting, "긍정 리뷰")) {
            Sort sort = Sort.by("positive").descending();
            Pageable pageable = PageRequest.of(0, 10, sort);
            Page<Store> result = storeRepository.findAllByRegionAndCategory(region, category, pageable);
            return result.stream().map(StoreResponseDto::new).collect(Collectors.toList());
        } else if (Objects.equals(sorting, "단골")) {
            Sort sort = Sort.by("positive").descending();
            Pageable pageable = PageRequest.of(0, 10, sort);
            Page<Store> result = storeRepository.findAllByRegionAndCategory(region, category, pageable);
            return result.stream().map(StoreResponseDto::new).collect(Collectors.toList());
        } else {
            Sort sort = Sort.by("positive").descending();
            Pageable pageable = PageRequest.of(0, 10, sort);
            Page<Store> result = storeRepository.findAllByRegionAndCategory(region, category, pageable);
            return result.stream().map(StoreResponseDto::new).collect(Collectors.toList());
        }
    }
}
