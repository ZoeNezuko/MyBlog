<template>
  <div>
    <!-- Card body -->
    <el-card shadow="always" id="profileCard">
      <P style="font-size:30px; margin-left:-930px">
        Profile
      </P>
      <hr style="width:83%" />

      <el-col :span="1" style="margin-top:10px; margin-left:100px">
        <el-avatar :size="90" :src="circleUrl"></el-avatar>
      </el-col>

      <div style="font-size:20px; margin-right:780px; margin-top:40px;">
        <P>Username: {{ user.username }}</P>
        <!-- Fake data filling -->
        <P class="text-size" style="margin-right:76px">Following 0 </P>
        <P class="text-size" style="margin-right:-100px; margin-top:-32px;">
          Fans 0
        </P>
      </div>
      <hr style="width:74%; margin-top:10px; margin-right:100px;" />
      <div class="text-size" style="margin-right:630px;">
        <p style="margin-right:-10px">E-Mail: {{ user.email }}</p>
        <!-- Fake data filling -->
        <P class="text-size" style="margin-right:96px">Gender: </P>
        <P class="text-size" style="margin-right:90px;">
          Birthday:
        </P>
        <P class="text-size" style="margin-right:80px;">
          Company:
        </P>
        <P class="text-size" style="margin-right:65px;">
          Introduction:
        </P>
      </div>

      <!-- Reset password form -->
      <el-button
        type="warning"
        plain
        @click="dialogFormVisible = true"
        style="margin:5px 650px 50px 0px;"
      >
        Reset password
      </el-button>

      <!-- Reset password dialog form -->
      <el-dialog
        title="Reset password"
        style="text-align: left;"
        :visible.sync="dialogFormVisible"
        @close="clear"
      >
        <!-- New password -->
        <el-form :model="reset" :rules="rules" ref="ruleForm">
          <el-form-item label="Enter the new password: " prop="newPassword">
            <el-col :span="12">
              <el-input
                type="password"
                v-model="reset.newPassword"
                minlength="8"
                maxlength="15"
                show-password
                placeholder="New password"
                style="margin-left:15px"
              ></el-input>
            </el-col>
          </el-form-item>

          <!-- Confirm new password -->
          <el-form-item
            label="Confirm the new password: "
            prop="repeatNewPassword"
          >
            <el-col :span="12">
              <el-input
                type="password"
                placeholder="Confirm your password"
                v-model="reset.repeatNewPassword"
                minlength="8"
                maxlength="15"
                show-password
              ></el-input>
            </el-col>
          </el-form-item>
        </el-form>

        <!-- Option button -->
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="resetPassword">
            Confirm
          </el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    // Repeat password verification
    var validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Please enter the password again"));
      } else if (value !== this.reset.newPassword) {
        callback(new Error("Password input is inconsistent!"));
      } else {
        callback();
      }
    };
    return {
      user: [],
      dialogFormVisible: false,
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      // From validator
      rules: {
        newPassword: [
          {
            required: true,
            pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,15}$/,
            message:
              "Password must contain 2 types of elements which are numbers and letters, and the password should have 8-15 digits.",
            trigger: "blur"
          }
        ],
        repeatNewPassword: [
          {
            required: true,
            validator: validatePassword,
            trigger: "blur"
          }
        ]
      },
      reset: {
        newPassword: "",
        repeatNewPassword: ""
      }
    };
  },
  mounted() {
    this.loadUser();
  },
  methods: {
    // Get request, get username
    loadUser() {
      var _this = this;
      this.$axios.get("/user/my/" + this.$route.query.id).then(response => {
        if (response && response.status === 200) {
          _this.user = response.data;
        }
      });
    },
    // Put request, reset user password
    resetPassword() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$axios
            .put("user/repassword", {
              username: this.user.username,
              password: this.reset.newPassword
            })
            .then(response => {
              if (response && response.data.code === 200) {
                this.$message({
                  type: "success",
                  message: "Password changed successfully"
                });
                this.dialogFormVisible = false;
              }
            });
        }
      });
    },
    // Clear reset password input box when is close
    clear() {
      this.reset = {
        newPassword: "",
        repeatNewPassword: ""
      };
    }
  }
};
</script>

<style scoped>
.text-size {
  font-size: 15px;
}
#profileCard {
  width: 80%;
  margin: 40px 0px 0px 150px;
}
</style>
