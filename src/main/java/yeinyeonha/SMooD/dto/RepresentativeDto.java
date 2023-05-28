package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RepresentativeDto {
    private final Long storeId;
    private final String name;
    private final String photo;
    private final String repKeyword;
    private final List<String> keywords;

    public RepresentativeDto(Store store, List<String> keywords) {
        this.storeId = store.getId();
        this.name = store.getName();
        this.photo = store.getPhoto();
        this.repKeyword = store.getStoreKeywordList().get(0).getKeyword().getName();
        this.keywords = keywords;
    }
}
