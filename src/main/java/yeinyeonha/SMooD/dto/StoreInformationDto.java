package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StoreInformationDto {
    private final Long id;
    private final String name;
    private final String photo;
    private final List<String> keywords;

    public StoreInformationDto(Store store) {
        List<String> keywords = new ArrayList<>();
        keywords.add(store.getStoreKeywordList().get(0).getKeyword().getName());
        keywords.add(store.getStoreKeywordList().get(1).getKeyword().getName());
        keywords.add(store.getStoreKeywordList().get(2).getKeyword().getName());
        this.id = store.getId();
        this.name = store.getName();
        this.photo = store.getPhoto();
        this.keywords = keywords;
    }
}
