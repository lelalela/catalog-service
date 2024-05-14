package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
	webEnvironment = WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {

		var expectedBook = new Book("1231231231", "title", "author", 9.90); //북 객체 생성


		webTestClient
			.post()
			.uri("/books")
			.bodyValue(expectedBook)
			.exchange() //요청 명령어
			.expectStatus().isCreated() //요청이 201 상태를 갖는지 확인
			.expectBody(Book.class).value(actualBook -> { //요청을 전달하고 돌아온 콜백
				assertThat(actualBook).isNotNull(); //null인지 체크
				assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn()); // 리턴 객체가 생성한 객체랑 같은지 테스트
			});
	}


}
