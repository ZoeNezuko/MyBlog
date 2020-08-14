<template>
  <div style="margin-top: 40px">
    <!-- search blog bar -->
    <search-bar
      id="search"
      @onSearch="searchResult"
      ref="searchBar"
      v-model="keywords"
    >
    </search-bar>

    <!-- Path to markdown page -->
    <el-link
      href="/md"
      :underline="false"
      target="_blank"
      style="margin-left:1160px;"
    >
      <el-button type="primary" style="width:200%; ">
        Write a blog
      </el-button>
    </el-link>

    <!-- Prompt card -->
    <el-card shadow="always" style="width:15% ;margin:20px 0px 0px 1295px;">
      Do not post content involving politics, advertising, marketing,
      illegality, etc.
    </el-card>

    <!-- Card body -->
    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="article in articles" :key="article.id">
          <!-- Article title -->
          <router-link
            class="link"
            :to="{ path: '/blog/detail', query: { id: article.id } }"
          >
            <span style="font-size: 25px">
              <strong>{{ article.title }}</strong>
            </span>
          </router-link>

          <!-- Upload Author -->
          <el-divider content-position="left">
            Uploaded by:<strong> {{ article.author }}</strong>
          </el-divider>

          <!-- Article summary -->
          <div>Summary: {{ article.summary }}</div>

          <hr style="margin-top: 20px; margin-bottom:15px;" />
        </div>
      </el-card>
    </div>
    <!-- Pageinaion for blog -->
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
import SearchBar from "./SearchBar";
export default {
  name: "Articles",
  components: { SearchBar },
  data() {
    return {
      articles: [],
      pageSize: 5,
      total: 0,
      keywords: ""
    };
  },
  mounted() {
    this.loadArticles();
  },
  methods: {
    // Get request, get all blogs
    loadArticles() {
      var _this = this;
      this.$axios.get("/blog/" + this.pageSize + "/1").then(response => {
        if (response && response.status === 200) {
          _this.articles = response.data.content;
          _this.total = response.data.totalElements;
        }
      });
    },
    // pagination for blogs
    pageChange(page) {
      var _this = this;
      this.$axios.get("/blog/" + this.pageSize + "/" + page).then(response => {
        if (response && response.status === 200) {
          _this.articles = response.data.content;
          _this.total = response.data.totalElements;
        }
      });
    },
    // Get request, search for blog
    searchResult() {
      var _this = this;
      this.$axios
        .get("/blog/search?keywords=" + this.$refs.searchBar.keywords, {})
        .then(response => {
          if (response && response.status === 200) {
            _this.articles = response.data;
          }
        });
    }
  }
};
</script>

<style>
#search {
  margin-bottom: 50px;
}

.articles-area {
  width: 60%;
  height: 760px;
  margin-top: -177px;
  margin-left: auto;
  margin-right: auto;
}

.link {
  text-decoration: none;
  color: #51555c;
}

.link:hover {
  color: #54a1ee;
}
</style>
