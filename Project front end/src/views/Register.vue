<template>
  <body id="paper">
    <div>
      <!-- form -->
      <el-form
        :model="registerForm"
        :rules="rules"
        ref="ruleForm"
        class="login-container"
      >
        <h3 class="login_title">CREATE AN ACCOUNT</h3>

        <!--Username input box-->
        <el-form-item prop="username">
          <el-input
            type="text"
            v-model="registerForm.username"
            placeholder="Username"
          ></el-input>
        </el-form-item>

        <!--Paswword input box-->
        <el-form-item prop="password">
          <el-input
            type="password"
            v-model="registerForm.password"
            minlength="8"
            maxlength="15"
            show-password
            placeholder="password"
          ></el-input>
        </el-form-item>

        <!-- Repeat password -->
        <el-form-item prop="repeatPassword">
          <el-input
            type="password"
            placeholder="Confirm your password"
            v-model="registerForm.repeatPassword"
            minlength="8"
            maxlength="15"
            show-password
            style="margin-top:11px"
          ></el-input>
        </el-form-item>

        <!--Email input box-->
        <el-form-item prop="email">
          <el-input
            type="text"
            v-model="registerForm.email"
            placeholder="E-Mail"
          ></el-input>
        </el-form-item>

        <!--login button-->
        <el-form-item>
          <el-button class="login_button" type="primary" @click="register()">
            Register
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </body>
</template>

<script>
export default {
  data() {
    // Repeat password verification
    var validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Please enter the password again"));
      } else if (value !== this.registerForm.password) {
        callback(new Error("Password input is inconsistent!"));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        username: "",
        password: "",
        repeatPassword: "",
        email: ""
      },
      // From validator
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
            pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,15}$/,
            message:
              "Password must contain 2 types of elements which are numbers and letters, and the password should have 8-15 digits.",
            trigger: "blur"
          }
        ],
        repeatPassword: [
          {
            required: true,
            validator: validatePassword,
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "Please enter your email",
            trigger: "blur"
          },
          {
            type: "email",
            message: "Please enter the correct email address",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    // register request
    register() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          var _this = this;
          this.$axios
            .post("/register", {
              username: this.registerForm.username,
              password: this.registerForm.password,
              repeatPassword: this.registerForm.repeatPassword,
              email: this.registerForm.email
            })
            .then(response => {
              if (response.data.code === 200) {
                this.$alert("Registration success", "Prompt", {
                  confirmButtonText: "OK"
                });
                // Go to login path
                _this.$router.replace("/login");
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
      });
    }
  }
};
</script>

<style>
#paper {
  background: url("../assets/Cyberpunk Hight Rise Apartment.png");
  background-position: center;
  background-size: cover;
  height: 100%;
  width: 100%;
  position: absolute;
}
body {
  margin: -5px 0px;
}
</style>
