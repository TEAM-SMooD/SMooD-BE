package yeinyeonha.SMooD.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import yeinyeonha.SMooD.domain.Keyword;

import java.util.List;

import static yeinyeonha.SMooD.domain.QStore.store;
import static yeinyeonha.SMooD.domain.QKeyword.keyword;
import static yeinyeonha.SMooD.domain.QStoreKeyword.storeKeyword;
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements CustomKeywordRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Keyword> findRepresentKeyword(String dong, String category) {
        return jpaQueryFactory.selectFrom(keyword)
                .leftJoin(keyword.storeKeywordList, storeKeyword).fetchJoin()
                .leftJoin(storeKeyword.store).fetchJoin()
                .where(storeKeyword.store.region.dong.eq(dong),
                        storeKeyword.store.category.middle.eq(category),
                        storeKeyword.rep.eq(Boolean.TRUE))
                .distinct()
                .orderBy(keyword.id.asc())
                .fetch();
    }
}
