package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Store;

@Getter
public class StoreResponseDto {
    private final Long id;
    private final String name;
    private final String url;
    private final Double positive;
    private final Double revisit;
    private final Double rising;
    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.url = store.getUrl();
        this.positive = store.getPositive();
        this.revisit = store.getRevisit();
        this.rising = store.getRising();
    }
}
