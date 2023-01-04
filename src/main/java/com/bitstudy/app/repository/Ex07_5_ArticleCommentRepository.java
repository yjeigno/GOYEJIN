package com.bitstudy.app.repository;

import com.bitstudy.app.domain.ArticleComment;
import com.bitstudy.app.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/** QueryDsl 의 QuerydslPredicateExecutor 와 QuerydslBinderCustomizer 를 이용해서 검색 기능 만들기
 *
 * */

@RepositoryRestResource
public interface Ex07_5_ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>
        , QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createAt, root.createBy);

        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createAt).first(DateTimeExpression::eq);
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase);
    }

    /* 1) 빌드 (ctrl + F9)
    *  2) Hal 가서 체크하기
    *       ex) http://localhost:8080/api/articleComments?content=Maecenas */
}
