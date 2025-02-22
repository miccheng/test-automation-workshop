<template>
  <div id="todoActions">
    <button
      v-if="hasCompleted"
      class="clearCompletedBtn"
      type="button"
      @click="doClearCompleted"
    >
      Clear Completed
    </button>
  </div>
</template>

<script>
export default {
  props: {
    hasCompleted: Boolean,
  },
  methods: {
    doClearCompleted() {
      this.$emit("clear-completed");
    },
  },
};
</script>

<style>
button.clearCompletedBtn {
  font-size: small;
  background-color: goldenrod;
}
</style>
