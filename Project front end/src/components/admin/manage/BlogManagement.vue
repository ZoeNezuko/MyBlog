<template>
  <div>
    <el-row style="margin: 10px 0px 0px 20px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>Content management</el-breadcrumb-item>
        <el-breadcrumb-item>Bolg information</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>

    <!-- Path to markdown page -->
    <el-link href="/md" :underline="false" target="_blank" class="add-link">
      <el-button type="primary" style="margin: 0px 0 0 10px;">
        Write a blog
      </el-button>
    </el-link>

    <!-- Card body -->
    <el-card style="margin: 18px; width: 95%">
      <el-table :data="articles" stripe style="width: 100%">
        <!-- Open to see the summary -->
        <el-table-column width="5"> </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item>
                <span>summary: {{ props.row.summary }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

        <!-- Blog title -->
        <el-table-column
          prop="title"
          label="Title (Expand to view summary)"
          fit
        >
        </el-table-column>

        <!-- Blog author -->
        <el-table-column prop="author" label="Author" fit></el-table-column>

        <!-- Upload date -->
        <el-table-column prop="date" label="Date" fit></el-table-column>

        <!-- Operating bolg -->
        <el-table-column fixed="right" label="Operating" width="180">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editArticle(scope.row)"
              type="text"
              size="small"
            >
              Edit
            </el-button>
            <el-button
              @click.native.prevent="deleteArticle(scope.row.id)"
              type="text"
              size="small"
            >
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- pagination for blog -->
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="pageChange"
          :page-size="pageSize"
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "ArticleManagement",
  data() {
    return {
      articles: [],
      pageSize: 6,
      total: 0
    };
  },
  mounted() {
    this.loadArticles();
  },
  methods: {
    // Get request, get all blogs by upload user
    loadArticles() {
      var _this = this;
      this.$axios.get("/blog/user/" + this.pageSize + "/1").then(response => {
        if (response && response.status === 200) {
          _this.articles = response.data.content;
          _this.total = response.data.totalElements;
        }
      });
    },
    // pagination for blogs
    pageChange(page) {
      var _this = this;
      this.$axios
        .get("/blog/user/" + this.pageSize + "/" + page)
        .then(response => {
          if (response && response.status === 200) {
            _this.articles = response.data.content;
            _this.total = response.data.totalElements;
          }
        });
    },
    // Get article data go to Markdown page
    editArticle(article) {
      this.$router.push({
        name: "MarkdownEditor",
        params: {
          article: article
        }
      });
    },
    // Post request, delete article by id
    deleteArticle(id) {
      this.$confirm(
        "This operation will permanently delete the document, do you want to continue ?",
        "Prompt",
        {
          confirmButtonText: "Confirm",
          cancelButtonText: "Cancel",
          type: "warning"
        }
      )
        .then(() => {
          this.$axios.post("/blog/delete", { id: id }).then(response => {
            if (response && response.status === 200) {
              this.loadArticles();
              this.$message({
                type: "info",
                message: "Deleted"
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "Cancelled"
          });
        });
    }
  }
};
</script>

<style>
.add-link {
  margin: 18px 0 15px 10px;
  float: left;
}
</style>
