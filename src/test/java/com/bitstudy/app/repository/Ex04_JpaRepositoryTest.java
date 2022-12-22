package com.bitstudy.app.repository;

import com.bitstudy.app.config.JpaConfig;
import com.bitstudy.app.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // 슬라이드 테스트
/** 슬라이드 테스트란 지난번 TDD 때 각 메서드를 다 남남으로 서로를 알아보지 못하게 만들었었다. 이것처럼 메서드를 각각 테스트한 결과를 서로 못 보게 잘라서 만드는 것.
 *
 */

@Import(JpaConfig.class)
/** 원래대로라면 JPA에서 모든 정보를 컨트롤 해야 되는데 JpaConfig 의 경우는 읽어오지 못한다. 이유는 시스템에서 만든 게 아니라 우리가 별도로 만든 파일이기 때문. 그래서 따로 import를 해 줘야 한다.
 * 안 하면 config 안에 명시해 놨던 JpaAuditing 기능이 동작하지 않는다.
* */
class Ex04_JpaRepositoryTest {

    private final Ex04_ArticleRepository_기본테스트용 articleRepository;
    private final Ex05_ArticleCommentRepository_기본테스트용 articleCommentRepository;

    /* 원래는 둘 다 @Autowired 가 붙어야 하는데, JUnit5 버전과 최신 버전의 스프링 부트를 이용하면 Test에서 생성자 주입 패턴을 사용할 수 있다. */

    /* 생성자 만들기 - 여기서는 다른 파일에서 매개변수로 보내 주는 걸 받는 거라서 위에랑 상관없이 @Autowired 붙어야 한다. */
    public Ex04_JpaRepositoryTest(@Autowired Ex04_ArticleRepository_기본테스트용 articleRepository,
                                  @Autowired Ex05_ArticleCommentRepository_기본테스트용 articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    /* 트랜잭션 시 시용하는 메서드
       사용법: repository명.메소드()
        1) findAll() - 모든 컬럼을 조회할 때 사용. 페이징(pageable) 가능.
                        당연히 select 작업을 하지만, 잠깐 사이에 해당 테이블에 어떤 변화가 있었는지 알 수 없기 때문에 select 전에
                        먼저 최신 데이터를 얻기 위해 update를 한다.
                        동작 순서 : update -> select
        2) findById() - 한 건에 대한 데이터 조회 시 사용
                        primary key로 레코드 한 건 조회.
        3) save() - 레코드 저장할 때 사용. (insert, update)
        4) count() - 레코드 개수 뽑을 때 사용
        5) delete() - 레코드 삭제
        ---------------------------------------------------------

        -테스트용 데이터 가져오기
    * */


    /* select 테스트 */
    @DisplayName("select 테스트")
    @Test
    void selectTest() {
        /** 셀렉팅을 할 거니까 articleRepository 를 기준으로 테스트 할 거임
        * */
        List<Article> articles = articleRepository.findAll();

        /** assertJ 를 이용해서 테스트 할 거임
         * List 에 담겨있는 articles NotNull 이고 사이즈가 ?? 개면 통과
         *
         * */
        assertThat(articles).isNotNull().hasSize(100);
    }

    @DisplayName("insert 테스트")
    @Test
    void insertTest() {
        long prevCount = articleRepository.count();
        Article article = Article.of("제목", "내용", "#해시태그");
        articleRepository.save(article);

        assertThat(articleRepository.count()).isEqualTo(prevCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#abcd";
        article.setHashtag(updateHashtag);

        Article savedArticles = articleRepository.saveAndFlush(article);
        assertThat(savedArticles).hasFieldOrPropertyWithValue("Hashtag", updateHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void delete() {
        Article article = articleRepository.findById(1L).orElseThrow();

        long prevArticleCount = articleRepository.count();
        long prevArticleCommentCount = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();

        articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deletedCommentSize);
    }

}
