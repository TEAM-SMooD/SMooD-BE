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
    @Column(columnDefinition = "TEXT", length = 65535)
    private String review;
    private Long count;
    private boolean rep;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId")
    private Store store;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="keywordId")
    private Keyword keyword;
}
