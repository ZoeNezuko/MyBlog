<template>
  <div>
    <el-row>
      <!-- Back to content management -->
      <el-button
        type="info"
        icon="el-icon-arrow-left"
        plain
        @click="back"
        style="margin: 0px 40px 0px 0px "
      >
        Content management
      </el-button>

      <!-- Title input box -->
      <el-input
        type="text"
        v-model="article.title"
        maxlength="100"
        show-word-limit
        placeholder="Please enter title"
        style="margin: 40px 10px 30px 0px; width:65%"
      >
      </el-input>

      <!-- Post blog button -->
      <el-button
        type="primary"
        style="margin: 0px 10px 0px 40px"
        @click="dialogVisible = true"
      >
        Post blog
      </el-button>
    </el-row>

    <!-- Markdown Editor -->
    <el-row>
      <mavon-editor
        language="en"
        id="md"
        v-model="article.mdContent"
        @change="htmlChang"
      >
      </mavon-editor>

      <!-- summary dialog -->
      <el-dialog
        title="Post blog"
        :visible.sync="dialogVisible"
        width="45%"
        style="text-align:left;"
      >
        <!-- Form -->
        <el-form :model="article" :rules="rules" ref="ruleForm">
          <!-- Title -->
          <el-form-item label="Title:" prop="title" style="margin-top:20px">
            <el-col :span="22">
              <el-input
                v-model="article.title"
                placeholder="Please enter a title"
              ></el-input>
            </el-col>
          </el-form-item>

          <!-- Date -->
          <el-form-item prop="date" label="Date" hidden>
            <el-col :span="11">
              <el-date-picker
                type="date"
                placeholder="Pick a date"
                v-model="article.date"
                style="width: 100%;"
              ></el-date-picker>
            </el-col>
          </el-form-item>

          <!-- Author -->
          <el-form-item label="Author" prop="author" hidden>
            <el-input v-model="article.author"></el-input>
          </el-form-item>

          <!-- Article summary -->
          <el-form-item prop="summary" label="Summary">
            <!-- <el-divider content-position="left">Summary</el-divider> -->
            <el-input
              type="textarea"
              v-model="article.summary"
              rows="6"
              maxlength="150"
              show-word-limit
            ></el-input>
          </el-form-item>
        </el-form>

        <!-- option button -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="publishBlog">
            Confirm
          </el-button>
        </span>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "MarkdownEditor",
  data() {
    return {
      article: {
        title: "",
        summary: "",
        date: new Date()
      },
      dialogVisible: false,
      // Form verification
      rules: {
        title: [
          {
            required: true,
            message: "Title can not be empty",
            trigger: "blur"
          }
        ]
      }
    };
  },
  computed: {
    ...mapState(["username"])
  },
  mounted() {
    this.$set(this.article, "author", this.username.username);
    if (this.$route.params.article) {
      this.article = this.$route.params.article;
    }
  },
  methods: {
    htmlChang(value, render) {
      this.article.htmlContent = render;
      this.article.mdContent = value;
    },
    //Post request, post article
    publishBlog() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$axios
            .post("/blog/update", {
              id: this.article.id,
              title: this.article.title,
              author: this.article.author,
              summary: this.article.summary,
              date: this.article.date,
              htmlContent: this.article.htmlContent,
              mdContent: this.article.mdContent
            })
            .then(response => {
              if (response && response.data.code === 200) {
                this.$message({
                  type: "success",
                  message: "Post Successful"
                });
                this.$router.push("/blog");
              }
            })
            // })
            .catch(failResponse => {
              alert(failResponse.data + " post request failed");
            });
        }
      });
    },
    // back to last page
    back() {
      this.$router.push("/admin/manage/blog");
    }
  }
};
</script>

<style>
#md {
  width: 90%;
  margin: 25px 0px 50px 75px;
  min-height: 500px;
}
</style>
