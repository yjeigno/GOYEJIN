package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/** TDD를 위해서 임시로 만들어 놓은 저장소 (이거로 DB에 접근할 거)
 *
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
