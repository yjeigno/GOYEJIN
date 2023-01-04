package com.bitstudy.app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEx01_1_Article_엔티티로_변경 is a Querydsl query type for Ex01_1_Article_엔티티로_변경
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEx01_1_Article_엔티티로_변경 extends EntityPathBase<Ex01_1_Article_엔티티로_변경> {

    private static final long serialVersionUID = -1800788214L;

    public static final QEx01_1_Article_엔티티로_변경 ex01_1_Article_엔티티로_변경 = new QEx01_1_Article_엔티티로_변경("ex01_1_Article_엔티티로_변경");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath createBy = createString("createBy");

    public final StringPath hashtag = createString("hashtag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public final StringPath title = createString("title");

    public QEx01_1_Article_엔티티로_변경(String variable) {
        super(Ex01_1_Article_엔티티로_변경.class, forVariable(variable));
    }

    public QEx01_1_Article_엔티티로_변경(Path<? extends Ex01_1_Article_엔티티로_변경> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEx01_1_Article_엔티티로_변경(PathMetadata metadata) {
        super(Ex01_1_Article_엔티티로_변경.class, metadata);
    }

}

