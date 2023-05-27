package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.RegionCategory;
import yeinyeonha.SMooD.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreAnalysisDto {
    private final List<String> url1;
    private final String url2;
    private final List<String> url3;
    public StoreAnalysisDto(RegionCategory regionCategory) {
        List<String> list1 = new ArrayList<>();
        list1.add(regionCategory.getUrl5());
        list1.add(regionCategory.getUrl6());
        List<String> list2 = new ArrayList<>();
        list2.add(regionCategory.getUrl8());
        list2.add(regionCategory.getUrl9());
        list2.add(regionCategory.getUrl10());
        this.url1 = list1;
        this.url2 = regionCategory.getUrl7();
        this.url3 = list2;
    }
}
