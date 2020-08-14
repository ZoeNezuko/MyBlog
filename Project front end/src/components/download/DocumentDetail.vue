<template>
  <div>
    <!-- Back to last page button -->
    <el-button type="info" @click="back" style="margin: 60px 1260px 0px 0px">
      Back
    </el-button>

    <!-- Card body -->
    <el-card class="details">
      <!-- Document title -->
      <span style="font-size: 30px;">
        <strong> {{ document.title }}</strong>
      </span>

      <!-- Document format and date -->
      <el-alert type="info" :closable="false" style="margin-top:15px">
        <span
          >Uploaded by: <strong> {{ document.author }}</strong>
        </span>
        <span style="margin-left:15px">Format: {{ document.format }}</span>
        <span style="margin-left:15px">Date: {{ document.date }}</span>
      </el-alert>

      <!-- Document description -->
      <div>
        <p>{{ document.description }}</p>
      </div>

      <!-- Document download button -->
      <el-button style="margin-top:25px" @click="downloadFile(document)">
        Download
      </el-button>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "DocumentDetail",
  data() {
    return {
      document: []
    };
  },
  mounted() {
    this.loadDocument();
  },
  methods: {
    // Get document by id request
    loadDocument() {
      var _this = this;
      this.$axios.get("/download/" + this.$route.query.id).then(response => {
        if (response && response.status === 200) {
          // Data to docuemnt
          _this.document = response.data;
        }
      });
    },
    // Get request, dwonload file
    downloadFile(data) {
      let id = data.id;
      this.$axios
        .get("download/download?id=" + id, { responseType: "blob" })
        .then(
          function(res) {
            console.info(res);
            let url = window.URL.createObjectURL(res.data);
            let link = document.createElement("a");
            link.style.display = "none";
            link.href = url;
            link.setAttribute("download", data.title + "." + data.format);
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
          },
          function() {
            console.log("Request failed");
          }
        );
    },
    // back to last page
    back() {
      this.$router.go(-1);
    }
  }
};
</script>

<style></style>
