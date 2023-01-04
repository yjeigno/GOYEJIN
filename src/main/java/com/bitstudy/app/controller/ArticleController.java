package com.bitstudy.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/** 뷰 엔드포인트 관련 컨드롤러
 *
 * 엑셀 api 에 보면 정의해 놓은 view 부분에 url 들이 있다. 그거 보면서 하면 됨.
    /articles	                GET	게시판 페이지
    /articles/{article-id}	    GET	게시글 페이지
    /articles/search	        GET	게시판 검색 전용 페이지
    /articles/search/hashtag	GET	게시판 해시태그 검색 전용 페이지
 */

@RequestMapping("/articles") // 모든 경로들은 /articles 로 시작하니까 클래스 레벨이 1자로 @RequestMapping("/articles") 걸어 놨음
public class ArticleController {

    /* BDD 하러 가기 */

}
