package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.RegionCategory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TableauResponseDto {
    private final String dong;
    private final String middle;
    private final List<String> url;

    public TableauResponseDto(RegionCategory regionCategory) {
        this.dong = regionCategory.getRegion().getDong();
        this.middle = regionCategory.getCategory().getMiddle();
        List<String> list = new ArrayList<>();
        list.add(regionCategory.getUrl1());
        list.add(regionCategory.getUrl2());
        list.add(regionCategory.getUrl3());
        list.add(regionCategory.getUrl4());
        this.url = list;
    }
}
