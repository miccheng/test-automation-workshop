// index.js

const express = require('express');
const bodyParser = require('body-parser');
const sqlite3 = require('sqlite3').verbose();

// Initialize the app
const app = express();
const port = 3000;

// Middleware to parse request body as JSON
app.use(bodyParser.json());

// Initialize SQLite database
const db = new sqlite3.Database('./database.db', (err) => {
    if (err) {
        console.error('Error opening database: ' + err.message);
    } else {
        console.log('Connected to SQLite database');
    }
});

// Create (POST) a new task
app.post('/todos', (req, res) => {
    const { task, completed } = req.body;
    db.run(`INSERT INTO todos (task, completed) VALUES (?, ?)`, [task, completed ? 1 : 0], function (err) {
        if (err) {
            return res.status(400).json({ error: err.message });
        }
        res.status(201).json({ id: this.lastID, task, completed: completed ? true : false });
    });
});

// Read (GET) all tasks
app.get('/todos', (req, res) => {
    db.all(`SELECT * FROM todos`, [], (err, rows) => {
        if (err) {
            return res.status(400).json({ error: err.message });
        }
        res.json(rows);
    });
});

// Read (GET) a specific task by ID
app.get('/todos/:id', (req, res) => {
    const { id } = req.params;
    db.get(`SELECT * FROM todos WHERE id = ?`, [id], (err, row) => {
        if (err) {
            return res.status(400).json({ error: err.message });
        }
        if (!row) return res.status(404).json({ message: 'Task not found' });
        res.json(row);
    });
});

// Update (PUT) a task by ID
app.put('/todos/:id', (req, res) => {
    const { id } = req.params;
    const { task, completed } = req.body;
    db.run(`UPDATE todos SET task = ?, completed = ? WHERE id = ?`, [task, completed ? 1 : 0, id], function (err) {
        if (err) {
            return res.status(400).json({ error: err.message });
        }
        if (this.changes === 0) {
            return res.status(404).json({ message: 'Task not found' });
        }
        res.json({ id: parseInt(id), task, completed });
    });
});

// Delete (DELETE) a task by ID
app.delete('/todos/:id', (req, res) => {
    const { id } = req.params;
    db.run(`DELETE FROM todos WHERE id = ?`, [id], function (err) {
        if (err) {
            return res.status(400).json({ error: err.message });
        }
        if (this.changes === 0) {
            return res.status(404).json({ message: 'Task not found' });
        }
        res.json({ message: 'Task deleted', id });
    });
});

// Start the server
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
