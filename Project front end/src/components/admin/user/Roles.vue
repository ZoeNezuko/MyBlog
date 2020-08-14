<template>
  <div>
    <el-row style="margin: 10px 0px 0px 20px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>User management</el-breadcrumb-item>
        <el-breadcrumb-item>Role configuration</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>

    <!-- Card body -->
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table :data="roles" fit>
        <!-- Role ID -->
        <el-table-column prop="id" label="ID" fit> </el-table-column>

        <!-- Role name -->
        <el-table-column prop="name" label="Role" fit> </el-table-column>

        <!-- Role description -->
        <el-table-column
          prop="description"
          label="Description"
          fit
        ></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Roles",
  data() {
    return {
      dialogFormVisible: false,
      roles: [],
      navs: [],
      selectedRole: [],

      selectedNavsIds: [],
      props: {
        id: "id",
        label: "name",
        children: "children"
      }
    };
  },
  mounted() {
    this.loadRoles();
  },
  methods: {
    // Get request, get all roles
    loadRoles() {
      var _this = this;
      this.$axios.get("/role/info").then(resp => {
        if (resp && resp.status === 200) {
          _this.roles = resp.data;
        }
      });
    }
  }
};
</script>

<style></style>
