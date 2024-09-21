<template>
  <div class="todo-item">
    <input type="checkbox" :checked="todo.completed" @change="toggleComplete(todo.id, !todo.completed)">
    <span :class="{ completed: todo.completed }">{{ todo.task }}</span>
    <button @click="editTodo(todo)">Edit</button>
    <button @click="deleteTodo(todo.id)">Delete</button>
  </div>
</template>

<script>
export default {
  props: {
    todo: Object,
  },
  methods: {
    toggleComplete(id, completed) {
      this.$emit('toggle-complete', { id, completed });
    },
    editTodo(todo) {
      this.$emit('edit-todo', todo);
    },
    deleteTodo(id) {
      this.$emit('delete-todo', id);
    }
  }
}
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
