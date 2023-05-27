package yeinyeonha.SMooD.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import yeinyeonha.SMooD.domain.Store;

import java.util.List;

import static yeinyeonha.SMooD.domain.QStoreKeyword.storeKeyword;
import static yeinyeonha.SMooD.domain.QStore.store;
@RequiredArgsConstructor
@Primary
@Slf4j
public class StoreRepositoryImpl implements CustomStoreRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Store> findStoresByCategory(String category, String keyword1, String keyword2, String keyword3) {
        if (keyword2 == null) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.eq(keyword1))
                    .distinct()
                    .orderBy(OrderByNull.DEFAULT)
                    .fetch();
        } else if (keyword3 == null) {
            return jpaQueryFactory.selectFrom(store)
                    .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                    .leftJoin(storeKeyword.keyword).fetchJoin()
                    .where(store.category.middle.eq(category),
                            storeKeyword.keyword.name.in(keyword1, keyword2))
                    .distinct()
                    .orderBy(OrderByNull.DEFAULT)
                    .fetch();
        }
        return jpaQueryFactory.selectFrom(store)
                .leftJoin(store.storeKeywordList, storeKeyword).fetchJoin()
                .leftJoin(storeKeyword.keyword).fetchJoin()
                .where(store.category.middle.eq(category),
                        storeKeyword.keyword.name.in(keyword1, keyword2, keyword3))
                .distinct()
                .orderBy(OrderByNull.DEFAULT)
                .fetch();
    }
}
