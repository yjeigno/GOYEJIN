package com.bitstudy.app.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
    슬라이드 테스트: 기능별(레이어별)로 잘라서 특정 부분(기능)만 테스트하는 것.

 - 통합 테스트 애너테이션
    @SpringBootTest - 스프링이 관리하는 모든 빈을 등록시켜서 테스트 하기 때문에 무겁다
                      * 테스트를 가볍게 하기 위해서 @WebMvcTest 를 사용해서 web 레이어 관련 빈들만 등록한 상태로 테스트를 할 수도 있다.
                        단, web 레이어 관련된 빈들만 등록되므로 Service 는 등록되지 않는다. 그래서 Mock 관련 애너테이션을 이용해서 가짜로 만들어 줘야 한다.

 - 슬라이스 테스트 애너테이션
    1) @WebMvcTest - 슬라이스 테스트에서 대표적인 애너테이션
                     Controller를 테스트 할 수 있도록 관련 설정을 제공해 준다.
                     @WebMvcTest를 선언하면 web 관련된 Bean만 주입이 되고, MockMvc 를 알아볼 수 있게 된다.

                     * MockMvc 는 웹 어플리케이션을 어플리케이션 서버에 배포하지 않고, 가짜로 테스트용 MVC 환경울 만들어서 요청 및 전송, 응답기능을 제공해 주는 유틸리티 클래스.
                      간단히 말하면, 내가 컨트롤러 테스트 하고 싶을 때 실제 서버에 올리지 않고, 테스트용으로 시뮬레이션 해서 MVC가 되도록 해 주는 클래스.
                     그냥 컨트롤러 슬라이스 테스트 한다고 하면 @WebMvcTest 랑 MockMvc 쓰면 됨.

    2) @DataJpaTest - JPA 레포지토리 테스트 할 때 사용
                     @Entity 가 있는 엔티티 클래스들을 스캔해서 테스트를 위한 JPA 레포지토리들을 설정
                     * @Component 나 @ConfigurationProperties Bean 들은 무시

    3) @RestClientTest - (클라이언트 입장에서의) API 연동 테스트
                         테스트 코드 내에서 Mock 서버를 띄울 수 있다. (response, request 에 대한 사전 정의가 가능)

 * */

@WebMvcTest
public class Ex07_3_1_DataRestTest_실패하는_테스트 {

    /** MockMvc 테스트 방법
     * 1) MockMvc 생성 (빈 준비)
     * 2) MockMvc 에게 요청에 대한 정보를 입력
     * 3) 요청에 대한 응답값을 expect를 이용해서 테스트 한다.
     * 4) expect 다 통과하면 테스트 통과
     * */

    private final MockMvc mvc; /* 1) MockMvc 생성 (빈 준비) */

    public Ex07_3_1_DataRestTest_실패하는_테스트(@Autowired MockMvc mvc) { /* 2) MockMvc 에게 요청에 대한 정보를 입력 (주입) */
        this.mvc = mvc;
    }

    // [api] - 게시글 리스트 전체 조회
    @DisplayName("[api] - 게시글 리스트 전체 조회")
    @Test
    void articles() throws Exception {

        /* 일단 이 테스트는 실패해야 정상임. 이유는 해당 api를 찾을 수 없기 때문
           콘솔창에 MockHttpServletRequest 부분에 URI="/api/articles 있을 거다. 복사해서 브라우저에 http://localhost:8080/api/articles
           넣어 보면 제대로 나온다.

           그럼 왜 여기선 안 되냐면 @MockMvcTest 는 슬라이스 테스트이기 때문에 controller 외의 빈들을 로드하지 않았기 때문이다.
           슬라이스 테스트라서 다른 애들을 못 가져오고 여기 것만 돌렸기 때문에~

        */
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk()) // 현재 200이 들어왔는지 확인 (서버에 갔다 왔냐는 걸 묻는 중)
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))); // 이 부분은 외워라! 그게 편함

    }


}
