<template>
  <el-container>
    <el-aside>
      <SideNav @CategorySelect="listBytCategory" ref="sideNav"></SideNav>
    </el-aside>
    <el-main>
      <documents ref="documentsArea"></documents>
    </el-main>
  </el-container>
</template>

<script>
import SideNav from "../components/download/SideNav";
import Documents from "../components/download/Documents";

export default {
  name: "Download",
  components: { SideNav, Documents },
  methods: {
    /**
     * Send a request, the backend executes the query code,
     * returns the data, and then uses refs to modify the data of the document component
     * to dynamically render the page
     */
    listBytCategory() {
      var _this = this;
      var cid = this.$refs.sideNav.cid;
      var url = "/download/categories/" + cid + "/document";
      this.$axios.get(url).then(response => {
        if (response && response.status === 200) {
          _this.$refs.documentsArea.documents = response.data;
        }
      });
    }
  }
};
</script>

<style></style>
