<template>
  <div>
    <el-card shadow="always" id="jumbotron">
      <el-row>
        <el-col :span="9" style="margin: 60px 0px 0px 230px;">
          <div>
            <p style="font-size:45px;" class="homeText">Make Coding Easy</p>
            <p style="margin: -40px 0px 0px 95px;" class="homeText">
              Let’s help IT developers and learners change the world by coding.
            </p>
          </div>
        </el-col>
        <el-col :span="9" style="margin: 120px 0px 0px -120px">
          <div>
            <p class="homeText">
              This is a knowledge-sharing community for IT developers and
              learners.
            </p>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Jumbotron"
};
</script>

<style>
#jumbotron {
  height: 350px;
  margin-bottom: 20px;
  background: url("~@/assets/guidao.png");
  background-position: center;
  background-size: cover;
}

.homeText {
  color: rgb(255, 255, 255);
}
</style>
