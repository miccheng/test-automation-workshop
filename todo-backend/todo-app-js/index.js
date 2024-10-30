const express = require("express");
const bodyParser = require("body-parser");
const ToDo = require("./models/todo");
const cors = require("cors");

// Initialize the app
const app = express();
const port = process.env.PORT || 3000;

// Middleware to parse request body as JSON
app.use(cors());
app.use(bodyParser.json());

// Create (POST) a new task
app.post("/todos", async (req, res) => {
  try {
    const { task, completed } = req.body;
    const newTodo = await ToDo.create({ task, completed });
    res.status(201).json(newTodo);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// Read (GET) all tasks
app.get("/todos", async (req, res) => {
  try {
    const todos = await ToDo.findAll();
    res.json(todos);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// Read (GET) a specific task by ID
app.get("/todos/:id", async (req, res) => {
  try {
    const todo = await ToDo.findByPk(req.params.id);
    if (!todo) return res.status(404).json({ message: "Task not found" });
    res.json(todo);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// Update (PUT) a task by ID
app.put("/todos/:id", async (req, res) => {
  try {
    const { task, completed } = req.body;
    const todo = await ToDo.findByPk(req.params.id);
    if (!todo) return res.status(404).json({ message: "Task not found" });

    todo.task = task || todo.task;
    todo.completed = completed !== undefined ? completed : todo.completed;
    await todo.save();

    res.json(todo);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// Delete (DELETE) a task by ID
app.delete("/todos/:id", async (req, res) => {
  try {
    const todo = await ToDo.findByPk(req.params.id);
    if (!todo) return res.status(404).json({ message: "Task not found" });

    await todo.destroy();
    res.json({ message: "Task deleted", id: req.params.id });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});

module.exports = app;
