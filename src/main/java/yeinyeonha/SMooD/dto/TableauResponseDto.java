package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.RegionCategory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TableauResponseDto {
    private final String dong;
    private final String middle;
    private final String url;

    public TableauResponseDto(RegionCategory regionCategory) {
        this.dong = regionCategory.getRegion().getDong();
        this.middle = regionCategory.getCategory().getMiddle();
        this.url = regionCategory.getUrl();
    }
}
