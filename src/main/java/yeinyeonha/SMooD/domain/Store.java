package yeinyeonha.SMooD.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeId")
    private Long id;
    private String name;
    private String url;
    private String location;
    private Double location_x;
    private Double location_y;
    private Double positive;
    private Double revisit;
    private Double rising;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="regionId")
    private Region region;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;
}
