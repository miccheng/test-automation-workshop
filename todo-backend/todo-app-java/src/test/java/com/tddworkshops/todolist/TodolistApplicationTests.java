package com.tddworkshops.todolist;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Order;
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
	@Order(1)
	void context_loads() {
	}

	@Test
	@Order(2)
	void context_with_web_test_client() {
		assertNotNull(webTestClient);
		webTestClient.get()
				.uri("/todos")
				.exchange()
				.expectStatus()
				.isOk();
	}

	@Test
	@Order(3)
	void test_create_todo() {
		webTestClient.post()
				.uri("/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"task\": \"New Task\"}")
				.exchange()
				.expectStatus()
				.isCreated();

		webTestClient.get()
				.uri("/todos")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("_embedded.todos.length()")
				.isEqualTo("1")
				.jsonPath("_embedded.todos[0].task")
				.isEqualTo("New Task");
	}

	// Test the edit Todo feature
	@Test
	@Order(4)
	void test_edit_todo() {
		webTestClient.put()
				.uri("/todos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"task\": \"Edited Task\", \"completed\": true}")
				.exchange()
				.expectStatus()
				.isCreated();

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
	}

	// Test the delete Todo feature
	@Test
	@Order(5)
	void test_delete_todo() {
		webTestClient.delete()
				.uri("/todos/1")
				.exchange()
				.expectStatus()
				.isOk();

		// Validate the deleted value
		webTestClient.get()
				.uri("/todos/1")
				.exchange()
				.expectStatus()
				.isNotFound();
	}
}
