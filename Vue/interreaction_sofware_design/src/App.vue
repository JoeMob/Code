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
  font-size: 12px;
}
::-webkit-scrollbar {
  width: 8px;
  height: 8px;
  background-color: #fff;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: rgba(0, 0, 0, 0.1);
}
</style>
