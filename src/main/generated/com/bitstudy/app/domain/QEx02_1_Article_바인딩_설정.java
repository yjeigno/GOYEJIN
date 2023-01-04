package com.bitstudy.app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEx02_1_Article_바인딩_설정 is a Querydsl query type for Ex02_1_Article_바인딩_설정
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEx02_1_Article_바인딩_설정 extends EntityPathBase<Ex02_1_Article_바인딩_설정> {

    private static final long serialVersionUID = 1675053588L;

    public static final QEx02_1_Article_바인딩_설정 ex02_1_Article_바인딩_설정 = new QEx02_1_Article_바인딩_설정("ex02_1_Article_바인딩_설정");

    public final SetPath<ArticleComment, QArticleComment> articleComments = this.<ArticleComment, QArticleComment>createSet("articleComments", ArticleComment.class, QArticleComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath createBy = createString("createBy");

    public final StringPath hashtag = createString("hashtag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public final StringPath title = createString("title");

    public QEx02_1_Article_바인딩_설정(String variable) {
        super(Ex02_1_Article_바인딩_설정.class, forVariable(variable));
    }

    public QEx02_1_Article_바인딩_설정(Path<? extends Ex02_1_Article_바인딩_설정> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEx02_1_Article_바인딩_설정(PathMetadata metadata) {
        super(Ex02_1_Article_바인딩_설정.class, metadata);
    }

}

