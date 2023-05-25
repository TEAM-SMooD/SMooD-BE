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
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="regionId")
    private Region region;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category category;
}
