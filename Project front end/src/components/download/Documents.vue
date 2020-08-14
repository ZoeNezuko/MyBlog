<template>
  <div>
    <!-- Header -->
    <el-header id="card-header">
      <p style="font-size:18px">Newest upload</p>
    </el-header>

    <!-- Card body -->
    <div class="document-area">
      <el-card style="text-align: left;">
        <div v-for="document in documents" :key="document.id">
          <!-- Document title -->
          <router-link
            class="link"
            :to="{ path: '/download/detail', query: { id: document.id } }"
          >
            <span style="font-size: 18px">
              <strong>{{ document.title }}</strong>
            </span>
          </router-link>
          <!-- Upload date -->
          <div style="margin-top: 11px;">Upload time: {{ document.date }}</div>

          <hr style="margin-top: 20px; margin-bottom:15px;" />
        </div>
      </el-card>
    </div>
    <!-- Pagination for documents -->
    <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      @current-change="pageChange"
      :page-size="pageSize"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "document",
  data() {
    return {
      documents: [],
      pageSize: 5,
      total: 0
    };
  },
  // The code is converted to HTML and replaces the corresponding DOM, and then executes the code in mounted
  mounted() {
    this.loadDocuments();
  },
  methods: {
    // Get all documents request
    loadDocuments() {
      var _this = this;
      this.$axios.get("/download/" + this.pageSize + "/1").then(response => {
        if (response && response.status === 200) {
          _this.documents = response.data.content;
          _this.total = response.data.totalElements;
        }
      });
    },
    // pagination for documents
    pageChange(page) {
      var _this = this;
      this.$axios
        .get("/download/" + this.pageSize + "/" + page)
        .then(response => {
          if (response && response.status === 200) {
            _this.documents = response.data.content;
            _this.total = response.data.totalElements;
          }
        });
    }
  }
};
</script>
<style>
.document-area {
  height: 520px;
  margin-left: auto; /* ← */
  margin-right: 90px;
}
#card-header {
  text-align: left;
  background-color: #d6d8db;
  color: #333;
  line-height: 60px;
  margin-right: 90px;
}
</style>
