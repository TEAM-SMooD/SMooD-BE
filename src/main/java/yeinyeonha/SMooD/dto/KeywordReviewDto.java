package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.StoreKeyword;
@Getter
public class KeywordReviewDto {
    private final String keyword;
    private final String review;
    public KeywordReviewDto(StoreKeyword storeKeyword) {
        this.keyword = storeKeyword.getKeyword().getName();
        this.review = storeKeyword.getReview();
    }
}
