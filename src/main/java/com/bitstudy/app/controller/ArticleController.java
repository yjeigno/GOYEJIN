package com.bitstudy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/** 뷰 엔드포인트 관련 컨드롤러
 *
 * 엑셀 api 에 보면 정의해 놓은 view 부분에 url 들이 있다. 그거 보면서 하면 됨.
    /articles	                GET	게시판 페이지
    /articles/{article-id}	    GET	게시글 페이지
    /articles/search	        GET	게시판 검색 전용 페이지
    /articles/search/hashtag	GET	게시판 해시태그 검색 전용 페이지
 */

@Controller
@RequestMapping("/articles") // 모든 경로들은 /articles 로 시작하니까 클래스 레벨이 1자로 @RequestMapping("/articles") 걸어 놨음
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map) {
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article", null); // 지금 당장은 받아오지 않기 때문에 null 이라고 넣어지만, 테스트 할 때에는 뭐라도 문자열을 넣어 줘서 모델에 담기도록 한다.
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }

}
