<template>
  <!-- :default-active="'/admin/user/info'" -->
  <el-menu
    class="el-menu-admin"
    router
    mode="vertical"
    background-color="#304156"
    text-color="#fff"
    active-text-color="#ffd04b"
  >
    <div style="height: 20px;"></div>

    <!-- Traverse nav and all sub-navs -->
    <el-submenu
      v-for="(item, nav) in adminNavs"
      :key="nav"
      :index="nav.toString()"
      style="text-align: left"
    >
      <span slot="title" style="font-size: 16px;">
        <i :class="item.icon"></i>
        {{ item.name }}
      </span>
      <el-menu-item
        v-for="child in item.children"
        :key="child.path"
        :index="child.path"
      >
        <i :class="child.icon"></i>
        {{ child.name }}
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "AdminNav",
  computed: {
    adminNavs() {
      return this.$store.state.adminNavs;
    }
  }
};
</script>

<style scoped>
.el-menu-admin {
  height: 100%;
}
</style>
