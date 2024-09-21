import request from 'supertest';
import { describe, it, expect, beforeEach, afterEach } from 'vitest';
import app from '../index';  // Assuming your Express app is exported from app.js
import Todo from '../models/todo';  // Sequelize model

// Mock database setup/teardown
beforeEach(async () => {
  // Clear and reset the database before each test
  await Todo.sync({ force: true });
});

afterEach(async () => {
  // Optional: teardown after each test, e.g., closing DB connections
});

describe('Todo API Routes', () => {
  it('should create a new task', async () => {
    const newTodo = { task: 'Test task', completed: false };

    const response = await request(app)
      .post('/todos')
      .send(newTodo)
      .expect(201);

    expect(response.body.task).toBe(newTodo.task);
    expect(response.body.completed).toBe(false);
  });

  it('should get all tasks', async () => {
    // Create a task
    await Todo.create({ task: 'Test task 1', completed: false });
    await Todo.create({ task: 'Test task 2', completed: true });

    const response = await request(app)
      .get('/todos')
      .expect(200);

    expect(response.body).toHaveLength(2);
    expect(response.body[0].task).toBe('Test task 1');
    expect(response.body[1].completed).toBe(true);
  });

  it('should get a single task by id', async () => {
    const todo = await Todo.create({ task: 'Test task', completed: false });

    const response = await request(app)
      .get(`/todos/${todo.id}`)
      .expect(200);

    expect(response.body.task).toBe(todo.task);
  });

  it('should update a task', async () => {
    const todo = await Todo.create({ task: 'Test task', completed: false });

    const updatedTask = { task: 'Updated task', completed: true };

    const response = await request(app)
      .put(`/todos/${todo.id}`)
      .send(updatedTask)
      .expect(200);

    expect(response.body.task).toBe(updatedTask.task);
    expect(response.body.completed).toBe(true);
  });

  it('should delete a task', async () => {
    const todo = await Todo.create({ task: 'Test task', completed: false });

    await request(app)
      .delete(`/todos/${todo.id}`)
      .expect(200);

    const remainingTodos = await Todo.findAll();
    expect(remainingTodos).toHaveLength(0);
  });
});
