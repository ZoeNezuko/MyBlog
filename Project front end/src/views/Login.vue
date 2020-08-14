<template>
  <body id="wallpaper">
    <!--Form -->
    <el-form :model="loginForm" :rules="rules" class="login-container">
      <h3 class="login_title">User login</h3>

      <!-- Username input box -->
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          type="text"
          placeholder="Username"
        ></el-input>
      </el-form-item>

      <!-- Paswword input box -->
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          placeholder="Password"
          @keyup.enter.native="login"
        ></el-input>
      </el-form-item>

      <!-- Remember me button -->
      <el-checkbox
        v-model="checked"
        class="login_remember"
        label="Remember me"
      ></el-checkbox>

      <!-- Login button -->
      <el-form-item>
        <el-button type="primary" class="login_button" @click="login">
          Login
        </el-button>

        <!-- Register link -->
        <router-link to="register">
          <p>Go to create an account</p>
        </router-link>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      // Form verification
      rules: {
        username: [
          {
            required: true,
            message: "Please enter your username",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "Please enter your password",
            trigger: "blur"
          }
        ]
      },
      // Remember me option
      checked: true,
      // default login form
      loginForm: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    // login request
    login() {
      var _this = this;
      console.log(this.$store.state);
      this.$axios
        .post("/login", {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(response => {
          if (response.data.code === 200) {
            /**
             * When the success code returned by the backend is triggered,
             * the login() method in the store in vuex is triggered,
             * and the loginForm object is passed to the user object in the store
             */
            _this.$store.commit("login", _this.loginForm);
            this.$router.push("/");
          } else {
            this.$alert(response.data.message, "Prompt", {
              confirmButtonText: "OK"
            });
          }
        })
        .catch(failResponse => {
          alert(failResponse.data + " post request failed");
        });
    }
  }
};
</script>

<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 30px auto;
  text-align: center;
}

.login_remember {
  margin: 10px 0px 25px 0px;
}

.login_button {
  width: 100%;
}

#wallpaper {
  background: url("../assets/Cyberpunk_2077_4K.png");
  background-position: center;
  background-size: cover;
  height: 100%;
  width: 100%;
  position: absolute;
}
/* body {
  margin: 0;
} */
</style>
