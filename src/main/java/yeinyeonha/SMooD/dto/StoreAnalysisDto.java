package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreAnalysisDto {
    private final List<String> url1;
    private final String url2;
    private final List<String> url3;
    public StoreAnalysisDto(Store store) {
        List<String> list1 = new ArrayList<>();
        list1.add(store.getUrl1());
        list1.add(store.getUrl2());
        List<String> list2 = new ArrayList<>();
        list2.add(store.getUrl4());
        list2.add(store.getUrl5());
        list2.add(store.getUrl6());
        this.url1 = list1;
        this.url2 = store.getUrl7();
        this.url3 = list2;
    }
}
