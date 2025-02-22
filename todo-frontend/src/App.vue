<template>
  <div>
    <h1>To Do List</h1>
    
    <!-- Add/Edit Form -->
    <TodoForm 
      :editMode="editMode"
      :taskToEdit="taskToEdit"
      @add-task="addTask"
      @update-task="updateTask"
      @cancel-edit="cancelEdit" />

    <div v-if="loading">Loading...</div>
    <div v-else>
      <TodoItem 
        v-for="todo in todos.items" 
        :key="todo.id" 
        :todo="todo" 
        @toggle-complete="toggleComplete"
        @edit-todo="setEditMode"
        @delete-todo="deleteTask" />
      <TodoActions :hasCompleted="hasCompleted" @clear-completed="clearCompleted"></TodoActions>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import TodoItem from './components/TodoItem.vue';
import TodoForm from './components/TodoForm.vue';
import TodoActions from "./components/TodoActions.vue"

const viteApiHost = import.meta.env.VITE_API_HOST;
const apiHost = viteApiHost && viteApiHost !== 'https://-3000.' ? viteApiHost : 'http://localhost:3000';
// const apiHost = 'https://legendary-garbanzo-q9prwq64xh66j7-3000.app.github.dev'

export default {
  components: {
    TodoItem,
    TodoForm,
    TodoActions,
  },
  data() {
    return {
      todos: {
        items: [],
      },
      loading: true,
      editMode: false,
      taskToEdit: null,
    };
  },
  async mounted() {
    this.fetchTodos();
  },
  computed: {
     hasCompleted() {
       return this.todos.items.filter((item) => item.completed).length > 0
     }
   },
  methods: {
    async fetchTodos() {
      this.loading = true;
      try {
        const response = await axios.get(`${apiHost}/todos`);
        this.todos.items = response.data;
      } catch (error) {
        console.error("Error fetching todos:", error);
      } finally {
        this.loading = false;
      }
    },
    async addTask(task) {
      try {
        const response = await axios.post(`${apiHost}/todos`, task);
        this.todos.items.push(response.data);
      } catch (error) {
        console.error("Error adding task:", error);
      }
    },
    async updateTask(updatedTask) {
      try {
        const response = await axios.put(`${apiHost}/todos/${updatedTask.id}`, updatedTask);
        const index = this.todos.items.findIndex(todo => todo.id === updatedTask.id);
        this.todos.items[index] = response.data;
        this.cancelEdit();
      } catch (error) {
        console.error("Error updating task:", error);
      }
    },
    async deleteTask(id) {
      try {
        await axios.delete(`${apiHost}/todos/${id}`);
        this.todos.items = this.todos.items.filter(todo => todo.id !== id);
      } catch (error) {
        console.error("Error deleting task:", error);
      }
    },
    async toggleComplete({ id, completed }) {
      try {
        const todo = this.todos.items.find(todo => todo.id === id);
        todo.completed = completed;
        await axios.put(`${apiHost}/todos/${id}`, todo);
      } catch (error) {
        console.error("Error toggling complete:", error);
      }
    },
    setEditMode(todo) {
      this.editMode = true;
      this.taskToEdit = todo;
    },
    cancelEdit() {
      this.editMode = false;
      this.taskToEdit = null;
    },
    async clearCompleted() {
      try {
        await axios.post(`${apiHost}/todos/clear-completed`);
        this.todos.items = this.todos.items.filter(todo => !todo.completed);
      } catch (error) {
        console.error("Error clearing complete:", error);
      }
    }
  }
}
</script>

<style>
/* Add some basic styling */
.todo-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}

form {
  margin-bottom: 20px;
}
</style>
