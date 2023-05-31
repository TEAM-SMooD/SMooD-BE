package yeinyeonha.SMooD.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_category_Id")
    private Long id;
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url1;//인구보고서
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url2;//주변시설 현황보고서
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url3;//매출 보고서
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url4;//상권정보 보고서
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url5; //핵심키워드 워드클라우드
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url6; //워드클라우드 옆 순위
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url7; //핵심키워드 변화
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url8; //긍정 리뷰 그래프
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url9; //단골 그래프
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url10; //뜨고 있는 그래프
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="regionId")
    private Region region;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;
}
