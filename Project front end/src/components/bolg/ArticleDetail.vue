<template>
  <div>
    <!-- Back to last page button -->
    <el-button type="info" @click="back" style="margin: 60px 1260px 0px 0px">
      Back
    </el-button>

    <!-- Card body -->
    <el-card class="details">
      <!-- Article title -->
      <span style="font-size: 30px">
        <strong>{{ article.title }}</strong>
      </span>

      <!-- Article date -->
      <el-divider content-position="left">{{ article.date }}</el-divider>
      <span>
        Uploaded by: <strong> {{ article.author }}</strong>
      </span>
      <hr style="margin: 20px 0px 0px 0px" />

      <!-- Article Content -->
      <div style="margin-top:30px">
        <div v-html="article.htmlContent"></div>
      </div>
    </el-card>
    <!-- <el-card class="comment">comment here</el-card> -->
    <comment ref="comment" :aid="article.id"></comment>
  </div>
</template>

<script>
import Comment from "../comment/Comment";
export default {
  name: "ArticleDetails",
  components: { Comment },
  data() {
    return {
      article: []
    };
  },
  mounted() {
    this.loadArticle();
  },
  methods: {
    // Get request, get blog details
    loadArticle() {
      var _this = this;
      this.$axios.get("/blog/" + this.$route.query.id).then(response => {
        if (response && response.status === 200) {
          _this.article = response.data;
          // After the article is loaded, load the comments
          this.$refs.comment.loadComment(_this.article.id);
        }
      });
    },
    // back to last page
    back() {
      this.$router.go(-1);
    }
  }
};
</script>

<style>
.details {
  text-align: left;
  width: 900px;
  margin: -42px auto auto auto;
}
</style>
