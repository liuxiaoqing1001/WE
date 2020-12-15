<template>
  <div id="app">
    <img src="./assets/we_logo_black.png">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  /*color: #2c3e50;*/
  /*color: blueviolet;*/
  margin-top: 125px;
  background: aqua;
}
</style>
