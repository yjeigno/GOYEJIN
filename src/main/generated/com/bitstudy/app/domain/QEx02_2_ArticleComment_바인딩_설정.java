package com.bitstudy.app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEx02_2_ArticleComment_바인딩_설정 is a Querydsl query type for Ex02_2_ArticleComment_바인딩_설정
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEx02_2_ArticleComment_바인딩_설정 extends EntityPathBase<Ex02_2_ArticleComment_바인딩_설정> {

    private static final long serialVersionUID = 873392994L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEx02_2_ArticleComment_바인딩_설정 ex02_2_ArticleComment_바인딩_설정 = new QEx02_2_ArticleComment_바인딩_설정("ex02_2_ArticleComment_바인딩_설정");

    public final QArticle article;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath createBy = createString("createBy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QEx02_2_ArticleComment_바인딩_설정(String variable) {
        this(Ex02_2_ArticleComment_바인딩_설정.class, forVariable(variable), INITS);
    }

    public QEx02_2_ArticleComment_바인딩_설정(Path<? extends Ex02_2_ArticleComment_바인딩_설정> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEx02_2_ArticleComment_바인딩_설정(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEx02_2_ArticleComment_바인딩_설정(PathMetadata metadata, PathInits inits) {
        this(Ex02_2_ArticleComment_바인딩_설정.class, metadata, inits);
    }

    public QEx02_2_ArticleComment_바인딩_설정(Class<? extends Ex02_2_ArticleComment_바인딩_설정> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new QArticle(forProperty("article")) : null;
    }

}

