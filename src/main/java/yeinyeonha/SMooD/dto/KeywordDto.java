package yeinyeonha.SMooD.dto;

import lombok.Getter;
import yeinyeonha.SMooD.domain.Keyword;

@Getter
public class KeywordDto {
    private final String keyword;
    public KeywordDto(Keyword keyword) {
        this.keyword = keyword.getName();
    }
}
