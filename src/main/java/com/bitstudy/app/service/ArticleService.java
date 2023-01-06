package com.bitstudy.app.service;

import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleDto;
import com.bitstudy.app.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


@Setter
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository; // 아티클 관련 서비스이기 때문에 ArticleRepository 필요

    // 검색용
    @Transactional(readOnly = true) // 트랜젝션을 읽기 전용 모드로 설정. 실수로 커밋해도 flush 가 되지 않아서 엔티티가 등록, 수정, 삭제가 되지 않는다. (DB 안 건드린다)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }
}
