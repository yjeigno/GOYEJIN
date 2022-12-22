package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity /* 테이블과의 매핑 한다는 뜻.
            JPA 가 관리한다.
            PK가 알아볼 수 있께 필드들 중 하나에 @Id 어노테이션 달아 줘야 한다. */
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class Ex02_2_ArticleComment_바인딩_설정 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article;
//    @Setter @Column(nullable = false) private Long article;
    /** 연관관계 매핑
     * 연관관계 없이 만들면 private Long articleId; 이런식으로(관계형 데이터베이스 스타일) 하면 된다.
     * 그런데 우리는 Article 과 ArticleComment 가 관계를 맺고 있는 걸 객체 지향적으로 표현하려고 이렇게 쓸 것.
     * 그러기 위해서 필요한 건 단방향이라는 뜻의 @ManyToOne 에너테이션을 써 주고,
     * 이건 필수값이다 라는 뜻으로 (optional = false)
     * '댓글은 여러개:게시글 1개' 이기 때문에 단방향 방식을 썼다.
     * 댓글은 여러개:게시글 1개 == N : 1 == ManyToOne
     */


    @Setter
    @Column(nullable = false, length = 500)
    private String content; // 본문


    // 메타데이터
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성일시

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createBy; // 생성자

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy; // 수정자
}
