<template>
  <el-container>
    <el-main>
      <jumbotron></jumbotron>
      <gohere></gohere>
    </el-main>
  </el-container>
</template>

<script>
import jumbotron from "../components/home/Jumbotron";
import gohere from "../components/home/Gohere";
export default {
  name: "Home",
  components: { jumbotron, gohere }
};
</script>

<style scoped></style>
