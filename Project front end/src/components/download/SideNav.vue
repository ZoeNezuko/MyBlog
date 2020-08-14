<template>
  <el-menu
    default-active="0"
    class="categories"
    @select="handleSelect"
    active-text-color="#ffd04b"
  >
    <el-menu-item index="0">
      <span slot="title">All</span>
    </el-menu-item>
    <el-menu-item index="1">
      <span slot="title">Python</span>
    </el-menu-item>
    <el-menu-item index="2">
      <span slot="title">Java</span>
    </el-menu-item>
    <el-menu-item index="3">
      <span slot="title">C</span>
    </el-menu-item>
    <el-menu-item index="4">
      <span slot="title">C++</span>
    </el-menu-item>
    <el-menu-item index="5">
      <span slot="title">JavaScript</span>
    </el-menu-item>
    <el-menu-item index="6">
      <span slot="title">MySQL</span>
    </el-menu-item>
    <el-menu-item index="7">
      <span slot="title">PHP</span>
    </el-menu-item>
    <el-menu-item index="8">
      <span slot="title">Angular</span>
    </el-menu-item>
    <el-menu-item index="9">
      <span slot="title">React</span>
    </el-menu-item>
    <el-menu-item index="10">
      <span slot="title">Vue</span>
    </el-menu-item>
    <el-menu-item index="11">
      <span slot="title">Others</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "SideNav",
  data() {
    return {
      cid: []
    };
  },
  // Pass to the parent component to trigger the event
  methods: {
    handleSelect(key) {
      this.cid = key;
      this.$emit("CategorySelect");
    }
  }
};
</script>

<style>
.categories {
  position: sticky;
  /* margin-left: 2%; */
  /* top: 100px; */
  width: 200px;
}
</style>
