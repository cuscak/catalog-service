package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

import com.polarbookshop.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookIsCreated() {
		var expectedBook = new Book("1234567890", "Title", "Author", 9.90);
		webTestClient
			.post()
			.uri("/books")
			.bodyValue(expectedBook)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Book.class).value( actualBook -> {
				assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				assertThat(actualBook.title()).isEqualTo(expectedBook.title());
				assertThat(actualBook.author()).isEqualTo(expectedBook.author());
				assertThat(actualBook.price()).isEqualTo(expectedBook.price());
			});
}
}
