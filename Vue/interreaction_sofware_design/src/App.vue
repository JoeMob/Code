<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  methods: {
    setTitle() {
      document.title = this.$route.meta.title;
    }
  },
  mounted() {
    this.setTitle();
  }
});
</script>
<style>
* {
  font-family: "Sarasa Mono Slab SC";
}
#app {
  font-family: "Sarasa Mono Slab SC";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
