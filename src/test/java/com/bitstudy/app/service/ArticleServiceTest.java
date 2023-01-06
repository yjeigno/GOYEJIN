package com.bitstudy.app.service;

import com.bitstudy.app.repository.ArticleRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

// import static org.junit.jupiter.api.Assertions.*;
// import org.assertj.

/** 서비스 비지니스 로직은 슬라이스 테스트 기능 사용 안 하고 만들어 볼 거임
    스프링부트 어플리케이션 컨텍스트가 뜨는데 걸리는 시간을 없애려고 한다.
    디펜던시가 추가돼야 하는 부분에는 Mocking 을 하는 방식으로 한다.
    많이 사용하는 라이브러리는 mockito 라는 게 있다. (스프링 테스트 패키지에 내장되어 있음)

 @ExtendWith(MockitoExtension.class) 쓰면 됨.
 */

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    /* Mock을 주입하는 곳에 @InjectMocks 을 달아 줘야 한다. 그 외의 것들한테는 @Mock 달아 줌 */
    @InjectMocks private ArticleService sut; // sut - system under test, 테스트 짤 때 사용하는 이름 중 하나. 이건 테스트 대상이라는 뜻.

    private ArticleRepository articleRepository; // 의존하는 걸 가져와야 함 (테스트 중간에 mocking 할 때 필요)
}