<template>
  <div>
    <el-card class="comment">
      <span>
        <strong>{{ username.username }}</strong>
      </span>
      <!-- Comment input box -->
      <el-input
        type="text"
        v-model="comment.content"
        maxlength="500"
        show-word-limit
        placeholder="Please enter "
        style="width:76%; margin-left:20px"
      ></el-input>

      <!-- Comment submit button -->
      <el-button
        style="margin-left:10px"
        type="primary"
        plain
        @click="onSubmit"
      >
        Comment
      </el-button>

      <!-- All comments -->
      <div style="text-align:left; margin: 40px 0px 0px -25px;">
        <ul v-for="(item, itemIndex) in currentPageData" :key="itemIndex">
          <p>{{ item.username }}: {{ item.content }}</p>
          <el-divider></el-divider>
        </ul>
      </div>

      <!-- pagination for comments -->
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="pageChange"
          :current-page="currentPage"
          :page-size="pagesize"
          :total="allDatalength"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "Comment",
  props: {
    aid: Number
  },
  data() {
    return {
      currentPage: 1,
      pagesize: 8,
      comment: {},
      articleComments: []
    };
  },
  computed: {
    ...mapState(["username"]),
    // Comments pagination
    currentPageData() {
      const { currentPage, pagesize, articleComments } = this;
      return articleComments.slice(
        (currentPage - 1) * pagesize,
        currentPage * pagesize
      );
    },
    allDatalength() {
      return this.articleComments.length;
    }
  },
  methods: {
    // Get request, get all comment by blog
    loadComment(articleId) {
      this.$axios.get("/comment/" + articleId + "/blog").then(response => {
        if (response && response.status === 200) {
          this.articleComments = response.data;
          // console.log("this.articleComments:", this.articleComments);
        }
      });
    },
    // Post request, add comment to blog
    onSubmit() {
      this.$axios
        .post("/comment/add", {
          id: this.comment.id,
          content: this.comment.content,
          blogArticle: { id: this.aid }
        })
        .then(response => {
          if (response && response.data.code === 200) {
            this.$message({
              type: "success",
              message: "Comment successful"
            });
            // Refresh the current page
            this.$router.go(0);
          } else {
            this.$alert("Please login before commenting", {
              confirmButtonText: "OK"
            });
          }
        })
        .catch(failResponse => {
          alert(failResponse.data + " post request failed");
        });
    },
    // pagination for comments
    pageChange: function(currentPage) {
      this.currentPage = currentPage;
    }
  }
};
</script>

<style>
.comment {
  width: 900px;
  margin: 10px auto 40px auto;
}
</style>
