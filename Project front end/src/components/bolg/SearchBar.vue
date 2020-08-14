<template>
  <div>
    <!-- input box -->
    <el-input
      @keyup.enter.native="searchClick"
      placeholder="Search for..."
      prefix-icon="el-icon-search"
      size="small"
      style="width: 380px;"
      v-model="keywords"
    >
    </el-input>

    <!-- Search button -->
    <el-button
      size="small"
      type="primary"
      icon="el-icon-search"
      style="margin-left: 10px;"
      @click="searchClick"
      >Search
    </el-button>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  data() {
    return {
      keywords: "",
      articles: []
    };
  },
  methods: {
    // Search results pass the custom event of the parent component
    searchClick() {
      this.$emit("onSearch");
    }
  }
};
</script>

<style></style>
