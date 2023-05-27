package yeinyeonha.SMooD.repository;

import yeinyeonha.SMooD.domain.Store;

import java.util.List;

public interface CustomStoreRepository {
    List<Store> findStoresByCategory(String category, String keyword1, String keyword2, String keyword3);
    List<Store> findStoresSortingPositive(String Region, String category, String keyword1, String keyword2, String keyword3);
    List<Store> findStoresSortingRevisit(String Region, String category, String keyword1, String keyword2, String keyword3);
    List<Store> findStoresSortingRising(String Region, String category, String keyword1, String keyword2, String keyword3);
    List<String> findTop3Keyword(Long storeId);
    List<Store> findRepresentation(String dong, String category, String word);
    List<Store> findStoreBySorting(String sort, String dong, String middle);
}
