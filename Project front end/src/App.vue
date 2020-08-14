<template>
  <div id="app">
    <!--Nav Bar component-->
    <nav-bar></nav-bar>
    <!-- Back top button-->
    <el-backtop></el-backtop>
    <!-- Make footer still on bottom -->
    <div v-bind:style="{ minHeight: Height + 'px' }">
      <router-view />
    </div>
    <!--Footer component-->
    <Footer></Footer>
  </div>
</template>

<script>
import NavBar from "./components/NavBar";
import Footer from "./components/Footer";
export default {
  name: "App",
  components: { NavBar, Footer },
  data() {
    return {
      Height: 0
    };
  },
  mounted() {
    //Monitor the browser window changes and dynamically set the content height to keep the footer at the bottom
    this.Height = document.documentElement.clientHeight - 100; //
    window.onresize = () => {
      this.Height = document.documentElement.clientHeight - 100;
    };
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
