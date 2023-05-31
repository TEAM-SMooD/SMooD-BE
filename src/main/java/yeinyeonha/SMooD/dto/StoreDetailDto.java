package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreDetailDto {
    private final String name;
    private final List<String> url;
    private final List<KeywordReviewDto> list;
    private final List<String> topKeyword;
    private final Double positive;
    private final Double location_x;
    private final Double location_y;

    public StoreDetailDto(Store store) {
        List<String> list1 = new ArrayList<>();
        list1.add(store.getUrl1());
        list1.add(store.getUrl2());
        List<KeywordReviewDto> list2 = new ArrayList<>();
        list2.add(new KeywordReviewDto(store.getStoreKeywordList().get(0)));
        list2.add(new KeywordReviewDto(store.getStoreKeywordList().get(1)));
        list2.add(new KeywordReviewDto(store.getStoreKeywordList().get(2)));
        list2.add(new KeywordReviewDto(store.getStoreKeywordList().get(3)));
        list2.add(new KeywordReviewDto(store.getStoreKeywordList().get(4)));
        List<String> list3 = new ArrayList<>();
        list3.add(store.getStoreKeywordList().get(0).getKeyword().getName());
        list3.add(store.getStoreKeywordList().get(1).getKeyword().getName());
        list3.add(store.getStoreKeywordList().get(2).getKeyword().getName());
        this.name = store.getName();
        this.url = list1;
        this.list = list2;
        this.topKeyword = list3;
        this.positive = store.getPositive();
        this.location_x = store.getLocation_x();
        this.location_y = store.getLocation_y();
    }
}
