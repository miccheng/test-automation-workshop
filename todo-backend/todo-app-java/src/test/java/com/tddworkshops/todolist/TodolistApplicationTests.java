package com.tddworkshops.todolist;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TodolistApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	void test_create_todo() {
		webTestClient.get()
				.uri("/todos")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.length()")
				.isEqualTo("0");

		webTestClient.post()
				.uri("/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"task\": \"New Task\"}")
				.exchange()
				.expectStatus()
				.isOk();

		webTestClient.get()
				.uri("/todos")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.length()")
				.isEqualTo("1")
				.jsonPath("$.[0].task")
				.isEqualTo("New Task");

				webTestClient.put()
				.uri("/todos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"task\": \"Edited Task\", \"completed\": true}")
				.exchange()
				.expectStatus()
				.isOk();

		// Validate the updated value
		webTestClient.get()
				.uri("/todos/1")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("task")
				.isEqualTo("Edited Task")
				.jsonPath("completed")
				.isEqualTo("true");

				webTestClient.delete()
				.uri("/todos/1")
				.exchange()
				.expectStatus()
				.isNoContent();

		// Validate the deleted value
		webTestClient.get()
				.uri("/todos/1")
				.exchange()
				.expectStatus()
				.isNotFound();
	}
}
