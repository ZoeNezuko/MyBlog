<template>
  <el-row :gutter="20" style="margin-bottom: 30px">
    <!-- Blog information -->
    <el-card shadow="hover" style="width:28%; height:260px; margin-left:40px">
      <el-col :span="12">
        <div>
          <p class="text-size">Blog</p>
          <p style="margin-left:-10px; margin-bottom:45px">
            Read and Create Blogs
          </p>

          <!-- Go to button -->
          <el-link href="/blog" :underline="false" target="_blank">
            <el-button type="primary" style="margin-right:400px">
              Go to
            </el-button>
          </el-link>
        </div>
      </el-col>
    </el-card>

    <!-- Download information -->
    <el-card
      shadow="hover"
      style="width:28%; height:260px; margin:-260px 0px 0px 560px"
    >
      <el-col :span="18">
        <div>
          <p class="text-size">Download</p>
          <p style="margin-left:-50px; margin-bottom:45px">
            Upload and download materials
          </p>

          <!-- Go to button -->
          <el-link href="/download" :underline="false" target="_blank">
            <el-button type="primary" style="margin-right:400px">
              Go to
            </el-button>
          </el-link>
        </div>
      </el-col>
    </el-card>

    <!-- About us information -->
    <el-card
      shadow="hover"
      style="width:28%; height:260px; margin:-262px 20px 0px 1080px"
    >
      <el-col :span="18">
        <div>
          <p class="text-size">About Us</p>
          <p style="margin-left:-80px; margin-bottom:45px">
            Let’s find out more about us
          </p>

          <!-- Go to button -->
          <el-link href="/about" :underline="false" target="_blank">
            <el-button type="primary" style="margin-right:400px">
              Go to
            </el-button>
          </el-link>
        </div>
      </el-col>
    </el-card>
  </el-row>
</template>

<script>
export default {
  name: "Gohere"
};
</script>

<style scoped>
.text-size {
  color: rgb(103, 132, 212);
  text-align: left;
  font-size: 28px;
}
</style>
