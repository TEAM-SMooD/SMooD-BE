package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

import java.util.List;
@Getter
public class StoreKeywordInformationDto {
    private final Long id;
    private final String name;
    private final String photo;
    private final List<String> keywords;

    public StoreKeywordInformationDto(Store store, List<String> keywords) {
        this.id = store.getId();
        this.name = store.getName();
        this.photo = store.getPhoto();
        this.keywords = keywords;
    }
}
