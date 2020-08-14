<template>
  <div>
    <!-- Edite user dialog -->
    <el-dialog title="Set user status" :visible.sync="dialogFormVisible">
      <el-form v-model="selectedUser" style="text-align: left">
        <!-- Username -->
        <el-form-item label="username:" label-width="120px" prop="username">
          <label>{{ selectedUser.username }}</label>
        </el-form-item>

        <!-- User emial -->
        <el-form-item label="E-mail:" label-width="100px" prop="email">
          <label>{{ selectedUser.email }}</label>
        </el-form-item>

        <!-- Set user roles -->
        <el-form-item label="Role setting:" label-width="135px" prop="roles">
          <el-checkbox-group v-model="selectedRolesIds">
            <el-checkbox v-for="(role, i) in roles" :key="i" :label="role.id">
              {{ role.name }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>

      <!-- option button -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="onSubmit(selectedUser)">
          Confirm
        </el-button>
      </div>
    </el-dialog>

    <!-- User management nav -->
    <el-row style="margin: 10px 0px 0px 20px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>User management</el-breadcrumb-item>
        <el-breadcrumb-item>User configuration</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>

    <!-- Card body -->
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="users.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
        :default-sort="{ prop: 'id', order: 'ascending' }"
        style="width: 100%"
        fit
      >
        <!-- User ID -->
        <el-table-column prop="id" label="ID" fit> </el-table-column>

        <!-- Username -->
        <el-table-column prop="username" label="Username" fit></el-table-column>

        <!-- User emial -->
        <el-table-column prop="email" label="E-Mail" fit> </el-table-column>

        <!-- User status -->
        <el-table-column label="Accessibility status" fit>
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enable"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="value => commitStatusChange(value, scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>

        <!-- Edit user -->
        <el-table-column label="Role status" width="100">
          <template slot-scope="scope">
            <el-button @click="editUser(scope.row)" size="small">
              Edit
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- pagination for user -->
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="pageChange"
          :current-page="currentPage"
          :page-size="pagesize"
          :total="users.length"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {
      currentPage: 1,
      pagesize: 7,
      users: [],
      roles: [],
      dialogFormVisible: false,
      selectedUser: [],
      selectedRolesIds: []
    };
  },
  mounted() {
    this.loadUsers();
    this.loadRoles();
  },
  methods: {
    // Get request, get all users
    loadUsers() {
      var _this = this;
      this.$axios.get("/user/info").then(response => {
        if (response && response.status === 200) {
          _this.users = response.data;
        }
      });
    },
    // Get request, get all user roles id
    loadRoles() {
      var _this = this;
      this.$axios.get("/role/info").then(response => {
        if (response && response.status === 200) {
          _this.roles = response.data;
        }
      });
    },
    // pagination for users
    pageChange: function(currentPage) {
      this.currentPage = currentPage;
    },
    // Put request, change the user status
    commitStatusChange(value, user) {
      if (user.username !== "Admin") {
        this.$axios
          .put("/user/status", {
            enable: value,
            username: user.username
          })
          .then(response => {
            if (response && response.data.code === 200) {
              if (value) {
                this.$message("User [" + user.username + "] activated");
              } else {
                this.$message("User [" + user.username + "] disabled");
              }
            }
          });
      } else {
        user.enable = true;
        this.$alert("Cannot disable administrator");
      }
    },
    onSubmit(user) {
      let _this = this;
      // Send role information to the backend according to the role id bound to the view
      let roles = [];
      /**
       * selectedRolesIds is an Array type variable, which is the id of the role corresponding to the current user.
       * The component will match the label value according to the data in it, and select the corresponding content
       */
      for (let i = 0; i < _this.selectedRolesIds.length; i++) {
        for (let j = 0; j < _this.roles.length; j++) {
          if (_this.selectedRolesIds[i] === _this.roles[j].id) {
            roles.push(_this.roles[j]);
          }
        }
      }
      // Put request, edit user role
      this.$axios
        .put("/user/edit", {
          username: user.username,
          email: user.email,
          roles: roles
        })
        .then(response => {
          if (response && response.status === 200) {
            this.$message({
              type: "success",
              message: "User information modified successfully"
            });
            this.dialogFormVisible = false;
            // Re-request user information after modifying the role and update the view
            this.loadUsers();
          } else {
            this.$alert(response.data.message);
          }
        });
    },
    /**
     * Call the processing method when the edit button is clicked to
     * get the id of the role corresponding to the current user
     */
    editUser(user) {
      this.dialogFormVisible = true;
      this.selectedUser = user;
      let roleIds = [];
      for (let i = 0; i < user.roles.length; i++) {
        roleIds.push(user.roles[i].id);
      }
      this.selectedRolesIds = roleIds;
    }
  }
};
</script>

<style></style>
