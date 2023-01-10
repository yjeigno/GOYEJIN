package com.bitstudy.app.controller;

import com.bitstudy.app.config.SecurityConfig;
import com.bitstudy.app.domain.UserAccount;
import com.bitstudy.app.dto.ArticleCommentDto;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.dto.UserAccountDto;
import com.bitstudy.app.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** 하기 전에 알아둘 것: 이 테스트 코드를 작성하고 돌리면 결과적으로는 404 에러난다.
 * 이유는 아직 ArticleController 에 작성한 내용이 없고, dao 같은 것들도 없기 때문이다.
 *
 * 우선 작성하고 실제 코드(ArticleController)와 연결되는지 확인.
 *
 * 슬라이스 테스트 방식으로 테스트 할 거임.
 * */

/* autoConfiguration 을 가져올 필요가 없기 때문에 슬라이스 테스트 사용 가능 */
// @WebMvcTest // 이렇게만 쓰면 모든 컨트롤러들 다 읽어들인다. 지금은 컨트롤러 디렉토리에 파일이 하나밖에 없어서 상관없지만 많아지면 모든 컨트롤러들을 bead 으로 읽어오기 때문에 아래처럼 필요한 클래스만 넣어주면 됨.
@Import(SecurityConfig.class) /* 안 넣으면 무슨 url 을 받던 login 페이지로 이동시킴 */
@WebMvcTest(ArticleController.class)
@DisplayName("view 컨트롤러 - 게시글")
class ArticleControllerTest {

    private final MockMvc mvc;
    @MockBean private ArticleService articleService;
    /* @MockBean: 테스트에 필요한 객체를 bean 으로 등록 시켜서 기존 객체 대신 사용할 수 있게 만들어 줌

    ArticleController 에 있는 private final ArticleService articleService; 부분의 articleService 를 배제하기 위해서 @MckBean 사용함. 이유는 MockMvc 가 입출력 관련된 것들만 보게 하기 위해서 진퉁 서비스 로직을 끊어 주기 위해 사용??
    * */

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    /* 테스트는 엑셀 api 에 있는 순서대로 만들 거
        1) 게시판 (리스트) 페이지
        2) 게시글 (상세) 페이지
        3) 게시판 검색 전용
        4) 해시태그 검색 전용 페이지

    * 엑셀 api 에 보면 정의해 놓은 view 부분에 url 들이 있다. 그거 보면서 하면 됨.
    /articles	                GET	게시판 페이지
    /articles/{article-id}	    GET	게시글 페이지
    /articles/search	        GET	게시판 검색 전용 페이지
    /articles/search/hashtag	GET	게시판 해시태그 검색 전용 페이지
 */
    /* 1) 게시판 (리스트) 페이지 */
    @Test
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    public void articlesAll() throws Exception {
        /* eq는 equal 이라는 뜻 */
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());

        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                // 뷰를 만들고 있으니까 html 로 코드를 짤 거임. /articles 로 받아 온 데이터의 타입이 html 타입으로 되어있는지 확인
                // contentType 의 경우 exact math 라서 미디어타입이 딱 text/html 로 나오는 것만 허용하기 때문에
                // contentTypeCompatibleWith 를 이용해서 호환되는 타입까지 맞다고 쳐주는 거
                .andExpect(view().name("articles/index"))
                // 가져온 뷰 파일명이 index 인지 확인
                .andExpect(model().attributeExists("articles"));
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    }
    /* 2) 게시글 상세 페이지 */
    @Test
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상호출")
    public void articlesOne() throws Exception {

        Long articleId = 1L;
        /* eq는 equal 이라는 뜻 */
        given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("articleComments"));

        then(articleService).should().getArticle(articleId);

    }
    /* 3) 게시글 검색 전용 페이지 */
    @Disabled("구현중")
    @Test
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상호출")
    public void articlesSearch() throws Exception {
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));
    }

    /* 4) 해시태그 검색 전용 페이지 */
    @Disabled("구현중")
    @Test
    @DisplayName("[view][GET] 게시글 해시태그 전용 페이지 - 정상호출")
    public void articlesSearchHashtag() throws Exception {
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag"));
    }

    ///////////////////////////////////////////////////

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
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
               "memo memomemo",
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }

}