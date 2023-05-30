package yeinyeonha.SMooD.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import yeinyeonha.SMooD.domain.Store;

import java.util.List;
import java.util.Objects;

import static yeinyeonha.SMooD.domain.QStoreKeyword.storeKeyword;
import static yeinyeonha.SMooD.domain.QStore.store;
import static com.querydsl.core.group.GroupBy.groupBy;
@RequiredArgsConstructor
@Primary
@Slf4j
public class StoreRepositoryImpl implements CustomStoreRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Store> findRepresentation(String dong, String category, String word) {
        return jpaQueryFactory.selectFrom(store)
                .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                .leftJoin(storeKeyword.keyword).fetchJoin()
                .where(store.region.dong.eq(dong), store.category.middle.eq(category),
                        storeKeyword.keyword.name.eq(word))
                .distinct()
                .orderBy(storeKeyword.count.desc(),
                        store.reviewCount.desc())
                .limit(20)
                .fetch();
    }
    @Override
    public List<Store> findStoreBySorting(String sort, String dong, String middle) {
        if(sort.equals("긍정 리뷰")) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.region.dong.eq(dong), store.category.middle.eq(middle))
                    .orderBy(store.positive.desc(),
                            store.reviewCount.desc(),
                            storeKeyword.count.desc())
                    .limit(20)
                    .fetch();
        } else if (sort.equals("단골")) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.region.dong.eq(dong), store.category.middle.eq(middle))
                    .orderBy(store.revisit.desc(),
                            store.reviewCount.desc(),
                            storeKeyword.count.desc())
                    .limit(20)
                    .fetch();
        } else {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.region.dong.eq(dong), store.category.middle.eq(middle))
                    .orderBy(store.rising.desc(),
                            store.reviewCount.desc(),
                            storeKeyword.count.desc())
                    .limit(20)
                    .fetch();
        }
    }
    @Override
    public List<Store> findStoresByCategory(String category, String keyword1, String keyword2, String keyword3) {
        if (keyword2 == null) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.eq(keyword1))
                    .distinct()
                    .orderBy(store.reviewCount.desc())
                    .fetch();
        } else if (keyword3 == null) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2))
                    .distinct()
                    .orderBy(store.reviewCount.desc())
                    .fetch();
        }
        return jpaQueryFactory.selectFrom(store)
                .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                .leftJoin(storeKeyword.keyword).fetchJoin()
                .where(store.category.middle.eq(category),
                        storeKeyword.keyword.name.in(keyword1, keyword2, keyword3))
                .distinct()
                .orderBy(store.reviewCount.desc())
                .fetch();
    }
    @Override
    public List<Store> findStoresSortingPositive(String region, String category, String keyword1, String keyword2, String keyword3) {
        if (keyword2 == null) {
            if(Objects.equals(region, "전체")) {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.eq(keyword1))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.positive.desc())
                        .limit(20)
                        .fetch();
            } else {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.eq(keyword1),
                                store.region.dong.eq(region))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.positive.desc())
                        .limit(20)
                        .fetch();
            }

        } else if (keyword3 == null) {
            if(Objects.equals(region, "전체")) {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.in(keyword1, keyword2))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.positive.desc())
                        .limit(20)
                        .fetch();
            } else {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.in(keyword1, keyword2),
                                store.region.dong.eq(region))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.positive.desc())
                        .limit(20)
                        .fetch();
            }
        }
        if(Objects.equals(region, "전체")) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2, keyword3))
                    .distinct()
                    .orderBy(store.reviewCount.desc(), store.positive.desc())
                    .limit(20)
                    .fetch();
        } else {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2, keyword3),
                            store.region.dong.eq(region))
                    .distinct()
                    .orderBy(store.reviewCount.desc(), store.positive.desc())
                    .limit(20)
                    .fetch();
        }
    }
    @Override
    public List<Store> findStoresSortingRevisit(String region, String category, String keyword1, String keyword2, String keyword3) {
        if (keyword2 == null) {
            if(Objects.equals(region, "전체")) {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.eq(keyword1))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.revisit.desc())
                        .limit(20)
                        .fetch();
            } else {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.eq(keyword1),
                                store.region.dong.eq(region))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.revisit.desc())
                        .limit(20)
                        .fetch();
            }

        } else if (keyword3 == null) {
            if(Objects.equals(region, "전체")) {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.in(keyword1, keyword2))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.revisit.desc())
                        .limit(20)
                        .fetch();
            } else {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.in(keyword1, keyword2),
                                store.region.dong.eq(region))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.revisit.desc())
                        .limit(20)
                        .fetch();
            }
        }
        if(Objects.equals(region, "전체")) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2, keyword3))
                    .distinct()
                    .orderBy(store.reviewCount.desc(), store.revisit.desc())
                    .limit(20)
                    .fetch();
        } else {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2, keyword3),
                            store.region.dong.eq(region))
                    .distinct()
                    .orderBy(store.reviewCount.desc(), store.revisit.desc())
                    .limit(20)
                    .fetch();
        }
    }
    @Override
    public List<Store> findStoresSortingRising(String region, String category, String keyword1, String keyword2, String keyword3) {
        if (keyword2 == null) {
            if (Objects.equals(region, "전체")) {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.eq(keyword1))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.rising.desc())
                        .limit(20)
                        .fetch();
            } else {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.eq(keyword1),
                                store.region.dong.eq(region))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.rising.desc())
                        .limit(20)
                        .fetch();
            }

        } else if (keyword3 == null) {
            if (Objects.equals(region, "전체")) {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.in(keyword1, keyword2))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.rising.desc())
                        .limit(20)
                        .fetch();
            } else {
                return jpaQueryFactory.selectFrom(store)
                        .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                        .leftJoin(storeKeyword.keyword).fetchJoin()
                        .where(store.category.middle.eq(category),
                                storeKeyword.keyword.name.in(keyword1, keyword2),
                                store.region.dong.eq(region))
                        .distinct()
                        .orderBy(store.reviewCount.desc(), store.rising.desc())
                        .limit(20)
                        .fetch();
            }
        }
        if (Objects.equals(region, "전체")) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2, keyword3))
                    .distinct()
                    .orderBy(store.reviewCount.desc(), store.rising.desc())
                    .limit(20)
                    .fetch();
        } else {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2, keyword3),
                            store.region.dong.eq(region))
                    .distinct()
                    .orderBy(store.reviewCount.desc(), store.rising.desc())
                    .limit(20)
                    .fetch();
        }
    }
    @Override
    public List<String> findTop3Keyword(Long storeId) {
        return jpaQueryFactory.selectFrom(store)
                .leftJoin(store.storeKeywordList, storeKeyword)
                .leftJoin(storeKeyword.keyword)
                .where(store.id.eq(storeId))
                .orderBy(storeKeyword.count.desc())
                .limit(3)
                .transform(
                        groupBy(storeKeyword.id).list(
                                Projections.constructor(String.class,
                                        storeKeyword.keyword.name)
                        )
                );
    }
}

