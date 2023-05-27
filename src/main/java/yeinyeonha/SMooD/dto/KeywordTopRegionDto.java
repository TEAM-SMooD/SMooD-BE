package yeinyeonha.SMooD.dto;

import lombok.Getter;

@Getter
public class KeywordTopRegionDto {
    private final String name;
    private final Double percentage;
    private final Long storeSum;
    private final Long keywordSum;

    public KeywordTopRegionDto(String name, Double percentage, Long storeSum, Long keywordSum) {
        this.name = name;
        this.percentage = percentage;
        this.storeSum = storeSum;
        this.keywordSum = keywordSum;
    }
}
