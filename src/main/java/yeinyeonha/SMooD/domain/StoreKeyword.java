package yeinyeonha.SMooD.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_keyword_id")
    private Long id;
    private String review;
    private Long count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId")
    private Store store;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="keywordId")
    private Keyword keyword;
}
