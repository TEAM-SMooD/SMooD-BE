package yeinyeonha.SMooD.repository;

import yeinyeonha.SMooD.domain.Store;

import java.util.List;

public interface CustomStoreRepository {
    List<Store> findStoresByCategory(String category, String keyword1, String keyword2, String keyword3);
}
