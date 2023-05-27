package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yeinyeonha.SMooD.domain.*;
import yeinyeonha.SMooD.dto.KeywordTopRegionDto;
import yeinyeonha.SMooD.repository.*;
import static yeinyeonha.SMooD.domain.QStore.store;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final CustomStoreRepository storeRepositoryCustom;
    //지역과 카테고리에 따른 상권분석 태블로 조회
//    public StoreAnalysisDto findAnalysis(String dong, String middle) {
//        Region region = regionRepository.findRegionByDong(dong);
//        Category category = categoryRepository.findCategoryByMiddle(middle);
//        Store store = storeRepository.findByRegionAndCategory(region, category);
//        return new StoreAnalysisDto(store);
//    }
    //키워드에 따른 대표 가게 정보 조회
//    public List<RepresentativeDto> findStoreByKeyword(String dong, String middle, String word) {
//        Region region = regionRepository.findRegionByDong(dong);
//        Category category = categoryRepository.findCategoryByMiddle(middle);
//        Sort sorting = Sort.by("count").descending();
//        Page<Store> result = storeRepository.findAllByRegionAndCategoryAndRep(region, category, true, sorting);
//        return result.stream().map(RepresentativeDto::new).collect(Collectors.toList());
//    }
    //카테고리별 상위 가게 정보 조회
//    public List<StoreInformationDto> findStoreByCategory(String sort, String dong, String middle) {
//        Region region = regionRepository.findRegionByDong(dong);
//        Category category = categoryRepository.findCategoryByMiddle(middle);
//        if (Objects.equals(sort, "긍정 리뷰")) {
//            Sort sorting = Sort.by("positive", "count").descending();
//            Pageable pageable = PageRequest.of(0,15, sorting);
//            Page<Store> result = storeRepository.findAllByRegionAndCategory(region, category, pageable);
//            return result.stream().map(StoreInformationDto::new).collect(Collectors.toList());
//        } else if (Objects.equals(sort, "단골")) {
//            Sort sorting = Sort.by("revisit", "count").descending();
//            Pageable pageable = PageRequest.of(0,15, sorting);
//            Page<Store> result = storeRepository.findAllByRegionAndCategory(region, category, pageable);
//            return result.stream().map(StoreInformationDto::new).collect(Collectors.toList());
//        } else {
//            Sort sorting = Sort.by("rising", "count").descending();
//            Pageable pageable = PageRequest.of(0,15, sorting);
//            Page<Store> result = storeRepository.findAllByRegionAndCategory(region, category, pageable);
//            return result.stream().map(StoreInformationDto::new).collect(Collectors.toList());
//        }
//    }
    //가게 상세 조회
//    public StoreDetailDto findDetailById(Long storeId) {
//        Store result = storeRepository.findById(storeId).get();
//        return new StoreDetailDto(result);
//    }
    //카테고리와 키워드를 입력하면 해당 키워드가 가장 많이 포함된 지역 정보 TOP3 조회
    public List<KeywordTopRegionDto> findByKeywordAndCategory(String category, String keyword1, String keyword2, String keyword3) {
        List<Store> storeList = storeRepositoryCustom.findStoresByCategory(category, keyword1, keyword2, keyword3);
        int[] countList = {0, 0, 0, 0, 0, 0, 0, 0};
        List<String> name = new ArrayList<>(Arrays.asList("", "성수1가 제1동", "성수1가 제2동", "성수2가 제1동", "성수2가 제3동", "가회동", "삼청동", "신촌동"));
        HashMap<String, Double> percentage = new HashMap<>();
        int[] storeSum = {0, 0, 0, 0, 0, 0, 0, 0};
        for(Store s : storeList) {
            countList[s.getRegion().getId().intValue()] += 1;
        }
        for(int i = 1; i < 8; i++) {
            int sum = storeRepository.findStoresByRegionAndCategory(regionRepository.findRegionByDong(name.get(i)), categoryRepository.findCategoryByMiddle(category)).size();
            if (countList[i] != 0) {
                percentage.put(name.get(i), Double.valueOf(String.format("%.2f", (countList[i]/(double)sum)*100)));
            } else {
                percentage.put(name.get(i) , 0.0);
            }
            storeSum[i] = sum;
        }
        List<String> keySet = new ArrayList<>(percentage.keySet());
        keySet.sort((o1, o2) -> percentage.get(o2).compareTo(percentage.get(o1)));
        //TOP3 구하기
        List<KeywordTopRegionDto> result = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            result.add(new KeywordTopRegionDto(keySet.get(i), percentage.get(keySet.get(i)), (long) storeSum[name.indexOf(keySet.get(i))], (long) countList[name.indexOf(keySet.get(i))]));
        }
        return result;
    }
//    //특정 지역에서 업종과 키워드가 포함된 가게 정렬해서 보여주기
//    public List<StoreInformationDto> findStoreByKeywordAndCategory() {
//
//    }
}
