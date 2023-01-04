package com.bitstudy.app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEx01_2_ArticleComment_엔티티로_변경 is a Querydsl query type for Ex01_2_ArticleComment_엔티티로_변경
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEx01_2_ArticleComment_엔티티로_변경 extends EntityPathBase<Ex01_2_ArticleComment_엔티티로_변경> {

    private static final long serialVersionUID = -1757954530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEx01_2_ArticleComment_엔티티로_변경 ex01_2_ArticleComment_엔티티로_변경 = new QEx01_2_ArticleComment_엔티티로_변경("ex01_2_ArticleComment_엔티티로_변경");

    public final QArticle article;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath createBy = createString("createBy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QEx01_2_ArticleComment_엔티티로_변경(String variable) {
        this(Ex01_2_ArticleComment_엔티티로_변경.class, forVariable(variable), INITS);
    }

    public QEx01_2_ArticleComment_엔티티로_변경(Path<? extends Ex01_2_ArticleComment_엔티티로_변경> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEx01_2_ArticleComment_엔티티로_변경(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEx01_2_ArticleComment_엔티티로_변경(PathMetadata metadata, PathInits inits) {
        this(Ex01_2_ArticleComment_엔티티로_변경.class, metadata, inits);
    }

    public QEx01_2_ArticleComment_엔티티로_변경(Class<? extends Ex01_2_ArticleComment_엔티티로_변경> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new QArticle(forProperty("article")) : null;
    }

}

