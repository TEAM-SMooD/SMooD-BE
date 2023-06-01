package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

@Getter
public class StoreDto {
    private final String name;
    private final String photo;
    private final String dong;
    private final String category;

    public StoreDto(Store store) {
        this.name = store.getName();
        this.photo = store.getPhoto();
        this.dong = store.getRegion().getDong();
        this.category = store.getCategory().getMiddle();
    }
}
