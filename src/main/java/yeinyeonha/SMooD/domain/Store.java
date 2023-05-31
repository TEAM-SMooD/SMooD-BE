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
    @Column(columnDefinition = "TEXT", length = 2048)
    private String photo;
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url1; //가게 상세정보 도표
    @Column(columnDefinition = "TEXT", length = 2048)
    private String url2; //가게 상세정보 워드클라우드
    private String location; //주소
    private Double location_x; //위도
    private Double location_y; //경도
    private Double positive; //긍정 리뷰
    private Double revisit; //단골
    private Double rising; //뜨고 있는
    private Long reviewCount; //리뷰 개수
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
