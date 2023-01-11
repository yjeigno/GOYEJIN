package com.bitstudy.app.service;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.UserAccount;
import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleCommentDto;
import com.bitstudy.app.dto.ArticleDto;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.dto.UserAccountDto;
import com.bitstudy.app.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

// import static org.junit.jupiter.api.Assertions.*;
// import org.assertj.

/** 서비스 비지니스 로직은 슬라이스 테스트 기능 사용 안 하고 만들어 볼 거임
    스프링부트 어플리케이션 컨텍스트가 뜨는데 걸리는 시간을 없애려고 한다.
    디펜던시가 추가돼야 하는 부분에는 Mocking 을 하는 방식으로 한다.
    많이 사용하는 라이브러리는 mockito 라는 게 있다. (스프링 테스트 패키지에 내장되어 있음)

 @ExtendWith(MockitoExtension.class) 쓰면 됨.
 */

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    /* Mock 을 주입하는 곳에 @InjectMocks 을 달아 줘야 한다. 그 외의 것들한테는 @Mock 달아 줌 */
    @InjectMocks private ArticleService sut; // sut - system under test, 테스트 짤 때 사용하는 이름 중 하나. 이건 테스트 대상이라는 뜻.

    @Mock
    private ArticleRepository articleRepository; // 의존하는 걸 가져와야 함 (테스트 중간에 mocking 할 때 필요)

    /** 테스트 할 기능들
     * 1. 검색
     * 2. 각 게시글 선택하면 해당 상세 페이지로 이동
     * 3. 페이지네이션
     * */

    /* 1. 검색 */
    @DisplayName("검색어 없이 게시글 검색하면, 게시글 리스트 반환한다.")
    @Test
    void withoutKeywordReturnArticlesAll() {
        // Given - 페이지 기능을 넣기
        Pageable pageable = Pageable.ofSize(20); // 한 페이지에 몇 개 가져올 건지 결정
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When - 입력 없는지(null) 실제 테스트 돌리는 부분
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();;
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("검색어 이용해서 게시글 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void withKeywordReturnArticlesAll() {
        // Given - 페이지 기능을 넣기
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);
    }

    /* 2. 게시글 페이지로 이동 */
    @DisplayName("게시글 선택하면, 게시글(하나) 반환한다.")
    @Test
    void selectedKeywordReturnArticlesOne() {
        // Given
        Article article = createArticle();
        Long articleId = 1L;
        given(articleRepository.findById(1L)).willReturn(Optional.of(article));

        // When
        ArticleWithCommentsDto dto = sut.getArticle(articleId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        then(articleRepository).should().findById(articleId);
    }

    /* 3. 게시글 생성 */
    @DisplayName("게시글 정보 입력하면, 게시글(하나) 생성한다.")
    @Test
    void givenGetArticleInfoWhenCreateArticleOne() {
        // Given
        ArticleDto dto = createArticleDto();
        given(articleRepository.save(any(Article.class))).willReturn(createArticle());

        // When
        sut.saveArticle(dto);

        // Then
        then(articleRepository).should().save(any(Article.class));
    }

    /* 4. 게시글 수정 */
    @DisplayName("게시글 수정 정보 입력하면, 게시글(하나) 수정한다.")
    @Test
    void givenGModifiedArticleInfoWhenUpdateArticleOne() {
        // Given
        ArticleDto dto = createArticleDto("title", "content", "#java");
        Article article = createArticle();
        given(articleRepository.getReferenceById(dto.id())).willReturn(article);
        // dto.id() 는 getId() 이다.
        // dto 가 record 이기 때문에 별도로 getter 를 만들 필요가 없다.
        // 대신 이걸 불러다 쓸 때는 일반 필드처럼 가져다 쓰면 된다.

        // When
        sut.updateArticle(dto);

        // Then
        assertThat(article)
                .hasFieldOrPropertyWithValue("title", dto.title())
                .hasFieldOrPropertyWithValue("title", dto.content())
                .hasFieldOrPropertyWithValue("title", dto.hashtag());
        then(articleRepository).should().getReferenceById(dto.id());
    }

    /* 5. 게시글 삭제 */
    @DisplayName("게시글 ID 입력하면, 게시글(하나) 삭제한다")
    @Test
    void givenArticleIdInfoWhenDeleteArticleOne() {
        // Given
        Long articleId = 1L;
        willDoNothing().given(articleRepository).deleteById(articleId);

        // When
        sut.deleteArticle(articleId);

        // Then
        then(articleRepository).should().deleteById(articleId);
    }

    /* 새로 추가 */
    @DisplayName("게시글 수 조회하면, 게시글 수 반환")
    @Test
    void givenNothing_thenReturnArticleCount() {
        // Given
        long expected = 0L;
        given(articleRepository.count()).willReturn(expected);

        // When
        long actual = sut.getArticleCount();

        //Then
        assertThat(actual).isEqualTo(expected);
        then(articleRepository).should().count();

    }
    ////////////////////////////////////////////////////////////////////////////////
    private UserAccount creatUserAccount() {
        return UserAccount.of(
                "bitstudy",
                "password",
                "bitstudy@email.com",
                "bitstudy",
                null
        );
    }
    private Article createArticle() {
        return Article.of(
                creatUserAccount(),
                "title",
                "content",
                "#java"
        );
    }

    private ArticleDto createArticleDto() {
        return createArticleDto("title", "content", "#java");
    }

    private ArticleDto createArticleDto(String title, String content, String hashtag) {
        return ArticleDto.of(
                1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "bitstudy",
                "password",
                "bitstudy@email.com",
                "bitstudy",
                "memomemo",
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }
}