package yeinyeonha.SMooD.repository;

import yeinyeonha.SMooD.domain.Keyword;

import java.util.List;

public interface CustomKeywordRepository {
    List<Keyword> findRepresentKeyword(String dong, String category);
}
