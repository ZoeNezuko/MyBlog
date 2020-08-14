<template>
  <div id="nav">
    <!-- :default-active="'/'" -->
    <el-menu
      class="el-menu"
      mode="horizontal"
      background-color="#F5F7F7 "
      text-color="#ff9900"
      active-text-color="black"
      router
    >
      <!-- home page path -->
      <el-menu-item index="/">
        Home<!-- <img alt="Vue logo" src="../assets/logo.png" style="width: 30px" /> -->
      </el-menu-item>

      <el-menu-item index="/blog">
        Blog
      </el-menu-item>

      <el-menu-item index="/download">
        Download
      </el-menu-item>

      <!-- user bar  -->
      <el-submenu index="2" v-if="username" id="userProfile">
        <template slot="title">
          {{ username.username }}
        </template>
        <el-menu-item :index="profileUrl">Profile</el-menu-item>
        <el-menu-item index="/admin/manage/blog">My blog</el-menu-item>
        <el-menu-item index="/admin/manage/document">
          My resources
        </el-menu-item>

        <el-menu-item @click="logout">Logout</el-menu-item>
      </el-submenu>

      <!-- Login button -->
      <el-menu-item index="/login" class="loginButton" v-else>
        Login
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "NavBar",
  // Get user data
  computed: {
    ...mapState(["username"]),
    profileUrl() {
      const { username } = this.username;
      return `/user/my?id=${username}`;
    }
  },
  methods: {
    // logout request
    logout() {
      var _this = this;
      this.$axios.get("/logout").then(response => {
        if (response && response.data.code === 200) {
          this.$alert("Account logged out", "Prompt", {
            confirmButtonText: "OK"
          });
          _this.$store.commit("logout");
          _this.$router.replace("/login");
        }
      });
    }
  }
};
</script>

<style>
/* make nav bar fixed on top */
#nav {
  position: sticky;
  z-index: 1;
  top: -1px;
  width: 100%;
}

#userProfile {
  position: relative;
  left: 73%;
}

.loginButton {
  left: 70%;
}
</style>
