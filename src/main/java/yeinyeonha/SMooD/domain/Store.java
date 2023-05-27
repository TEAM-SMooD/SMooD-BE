package yeinyeonha.SMooD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private Long id;
    private String name;
    private String photo;
    private String url1; //핵심키워드 워드클라우드
    private String url2; //워드클라우드 옆 순위
    private String url3; //핵심키워드 변화
    private String url4; //긍정 리뷰 그래프
    private String url5; //단골 그래프
    private String url6; //뜨고 있는 그래프
    private String url7; //가게 상세정보 도표
    private String url8; //가게 상세정보 워드클라우드
    private String location; //주소
    private Double location_x; //위도
    private Double location_y; //경도
    private Double positive; //긍정 리뷰
    private Double revisit; //단골
    private Double rising; //뜨고 있는
    private Boolean rep; // 대표 가게 기준
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="regionId")
    private Region region;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreKeyword> storeKeywordList = new ArrayList<>();
}
