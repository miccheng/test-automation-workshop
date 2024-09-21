<template>
  <form @submit.prevent="submitForm">
    <input type="text" v-model="form.task" placeholder="Enter a task" required>
    <button type="submit">{{ editMode ? 'Update' : 'Add' }} Task</button>
    <button v-if="editMode" type="button" @click="cancelEdit">Cancel</button>
  </form>
</template>

<script>
export default {
  props: {
    editMode: Boolean,
    taskToEdit: Object,
  },
  data() {
    return {
      form: {
        task: '',
        completed: false
      }
    };
  },
  watch: {
    taskToEdit: {
      immediate: true,
      handler(newTask) {
        if (newTask) {
          this.form = { ...newTask };
        }
      }
    }
  },
  methods: {
    submitForm() {
      if (this.editMode) {
        this.$emit('update-task', this.form);
      } else {
        this.$emit('add-task', this.form);
      }
      this.form = { task: '', completed: false };
    },
    cancelEdit() {
      this.$emit('cancel-edit');
    }
  }
}
</script>
