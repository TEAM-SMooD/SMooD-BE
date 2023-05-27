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
        List<String> result = new ArrayList<>();
        result.add(store.getStoreKeywordList().get(0).getKeyword().getName());
        result.add(store.getStoreKeywordList().get(1).getKeyword().getName());
        result.add(store.getStoreKeywordList().get(2).getKeyword().getName());
        this.id = store.getId();
        this.name = store.getName();
        this.photo = store.getPhoto();
        this.keywords = result;
    }

}
