<template>
  <el-container id="nav-body">
    <el-container>
      <el-aside>
        <admin-nav></admin-nav>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import AdminNav from "./AdminNav";

export default {
  name: "AdminIndex",
  components: { AdminNav },
  data() {
    return {
      dialogVisible: false
    };
  },
  mounted() {
    // this.$router.replace('/admin/dashboard')
  }
};
</script>

<style>
#nav-body {
  height: 95vh;
}
</style>
