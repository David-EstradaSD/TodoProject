package com.skilldistillery.todo.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TodoTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("TodoJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		todo = em.find(Todo.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		todo = null;
	}

	@Test
	@DisplayName("testing Todo entity mapping")
	void test() {
		assertNotNull(todo);
		assertEquals(2021, todo.getCreatedAt().getYear());
	}
	
	@Test
	@DisplayName("testing Todo to User mapping")
	void test2() {
		assertNotNull(todo);
		assertEquals("shaun", todo.getUser().getUsername());
		// the first todo in the DB belongs to user_id = 1 aka "shaun"
	}


}
