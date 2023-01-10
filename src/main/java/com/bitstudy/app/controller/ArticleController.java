package com.bitstudy.app.controller;

import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.dto.response.ArticleCommentResponse;
import com.bitstudy.app.dto.response.ArticleResponse;
import com.bitstudy.app.dto.response.ArticleWithCommentsResponse;
import com.bitstudy.app.repository.ArticleRepository;
import com.bitstudy.app.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/** 뷰 엔드포인트 관련 컨드롤러
 *
 * 엑셀 api 에 보면 정의해 놓은 view 부분에 url 들이 있다. 그거 보면서 하면 됨.
    /articles	                GET	게시판 페이지
    /articles/{article-id}	    GET	게시글 페이지
    /articles/search	        GET	게시판 검색 전용 페이지
    /articles/search/hashtag	GET	게시판 해시태그 검색 전용 페이지
 */
@RequiredArgsConstructor // 필수 필드에 대한 생성자 자동 생성
@Controller
@RequestMapping("/articles") // 모든 경로들은 /articles 로 시작하니까 클래스 레벨이 1자로 @RequestMapping("/articles") 걸어 놨음
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) {
        // map.addAttribute("articles", List.of());
        map.addAttribute("articles", articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from));

        return "articles/index";
    }

    /* 게시글 상세 페이지 */
    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentsResponse article =
                ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        map.addAttribute("article", article); // 지금 당장은 받아오지 않기 때문에 null 이라고 넣어지만, 테스트 할 때에는 뭐라도 문자열을 넣어 줘서 모델에 담기도록 한다.
        map.addAttribute("articleComments", article.articleCommentsResponse());
        return "articles/detail";
    }

}
