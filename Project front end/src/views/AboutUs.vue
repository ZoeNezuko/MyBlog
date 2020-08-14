<template>
  <div>
    <el-card shadow="never" style="height: 380px;">
      <div id="box">
        <div class="chars">A</div>
        <div class="chars">B</div>
        <div class="chars">O</div>
        <div class="chars">U</div>
        <div class="chars">T</div>
        <div class="chars">U</div>
        <div class="chars">S</div>
        <div class="ll A"></div>
        <div class="ll N"></div>
        <div class="ll I"></div>
        <div class="ll P"></div>
        <div class="ll L"></div>
        <div class="ll E"></div>
        <div class="ll X"></div>
        <div class="pcov"></div>
      </div>
    </el-card>
    <el-row style="margin-bottom:50px">
      <el-col :span="15">
        <!-- 1 -->
        <div class="paragraph">
          <p class="introduction-title" style="margin-right:140px;">
            Our Goal
          </p>
          <hr class="split-line" />
          <p class="introduction">
            The initial goal is to develop an online knowledges sharting
            flatform
          </p>
          <p class="introduction" style="margin-left: 58px">
            or community that would help users to learn IT knowledges and skills
            easy.
          </p>
          <!-- 2 -->
          <div class="paragraph">
            <p class="introduction-title" style="margin-right:100px;">
              Our Mission
            </p>
            <hr class="split-line" />
            <p class="introduction" style="margin-left: 58px">
              The mission of this knowledges sharing community is to enable IT
              learners
            </p>
            <p class="introduction" style="margin-left: 110px">
              and developers exchange their learning experiences, knowledges,and
              resources.
            </p>
            <p class="introduction" style="margin-left: -45px">
              This is aming for making coding Easy for everyone to learn.
            </p>
            <p class="introduction" style="margin-left: 5px">
              Let’s help IT developers and learners change the world by coding.
            </p>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "AboutUs"
};
</script>

<style scoped>
/* #about {
  height: 450px;
  margin-top: 3px;
  margin-bottom: -10px;
  background: url("~@/assets/wallpaper.png");
  background-position: center;
  background-size: cover;
} */
.introduction-title {
  font-size: 30px;
  color: rgb(103, 132, 212);
}
.introduction {
  margin-right: -220px;
}
.split-line {
  width: 90%;
  margin-left: 370px;
}
.paragraph {
  margin-top: 50px;
}
/* ------------------------------------------------------------- */
/* Animation container settings */
#box {
  background-color: black;
  width: 100%;
  height: 50vh;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  -webkit-perspective: 300;
}
.chars {
  /* The block where the animated character is located */
  color: white;
  position: relative;
  width: 60px;
  height: 60px;
  text-align: center;
  line-height: 60px;
  font-size: 54px;
  font-weight: 900;
  font-style: italic;
  opacity: 0;
  margin: 0 10px;
}

.ll {
  /* block with characters  */
  position: absolute;
  margin-left: -480px;
  width: 60px;
  height: 60px;
  border-radius: 3px;
  opacity: 0;
}
.X {
  border: 4px solid rgb(49, 3, 109);
  animation: xmove linear 2s forwards;
}
.E {
  border: 4px solid rgb(121, 70, 106);
  animation: emove linear 2s forwards;
  animation-delay: 0.1s;
}
.L {
  border: 4px solid rgb(212, 13, 96);
  animation: lmove linear 2s forwards;
  animation-delay: 0.2s;
}
.pcov {
  position: absolute;
  width: 4px;
  height: 60px;
  background-color: rgb(223, 15, 15);
  opacity: 0;
  animation: showp forwards;
  animation-delay: 2.3s;
}
@keyframes showp {
  from {
    opacity: 0;
  }
  to {
    transform:/* translate(4px) */ scale(1, 2);
    opacity: 1;
  }
}
.P {
  border: 4px solid rgb(223, 15, 15);
  animation: pmove linear 2s forwards;
  animation-delay: 0.3s;
}
.I {
  border: 4px solid rgb(212, 13, 96);
  animation: imove linear 2s forwards;
  animation-delay: 0.4s;
}
.N {
  border: 4px solid rgb(121, 70, 106);
  animation: nmove linear 2s forwards;
  animation-delay: 0.5s;
}
.A {
  border: 4px solid rgb(49, 3, 109);
  animation: amove linear 2s forwards;
  animation-delay: 0.6s;
}

@keyframes amove {
  0% {
    opacity: 1;
    transform: rotateY(0deg);
  }
  50% {
    transform: rotateY(180deg);
  }
  100% {
    opacity: 1;
    transform: rotateY(360deg);
  }
}
@keyframes nmove {
  30% {
    opacity: 1;
    transform: translate(30px) rotateY(135deg) scale(1.1, 1.1);
  }
  60% {
    transform: translate(50px) rotateY(45deg) scale(1.1, 1.1);
  }
  85% {
    transform: translate(80px) rotateY(103deg) scale(1.2, 1.2);
  }
  100% {
    opacity: 1;
    z-index: -99;
    transform: translate(80px) rotateY(77deg) scale(1.2, 1.2);
  }
}
@keyframes imove {
  30% {
    opacity: 1;
    transform: translate(60px) rotateY(135deg) scale(1.2, 1.2);
  }
  60% {
    transform: translate(120px) rotateY(45deg) scale(1.3, 1.3);
  }
  85% {
    transform: translate(160px) rotateY(103deg) scale(1.3, 1.3);
  }
  100% {
    opacity: 1;
    transform: translate(164px) rotateY(77deg) scale(1.3, 1.3);
  }
}
@keyframes pmove {
  30% {
    opacity: 1;
    transform: translate(60px) rotateY(135deg) scale(1.2, 1.2);
  }
  60% {
    transform: translate(120px) rotateY(45deg) scale(1.4, 1.4);
  }
  85% {
    transform: translate(240px) rotateY(135deg) scale(1.6, 1.6);
  }
  100% {
    opacity: 1;
    transform: translate(240px) rotateY(90deg) scale(1.6, 1.6);
  }
}
@keyframes lmove {
  33% {
    opacity: 1;
    transform: translate(80px) rotateY(135deg) scale(1.2, 1.2);
  }
  66% {
    transform: translate(240px) rotateY(45deg) scale(1.6, 1.6);
  }
  95% {
    transform: translate(320px) rotateY(100deg) scale(1.4, 1.4);
  }
  100% {
    opacity: 1;
    transform: translate(320px) rotateY(103deg) scale(1.3, 1.3);
  }
}
@keyframes emove {
  33% {
    opacity: 1;
    transform: translate(80px) rotateY(135deg) scale(1.2, 1.2);
  }
  66% {
    transform: translate(240px) rotateY(45deg) scale(1.6, 1.6);
  }
  95% {
    transform: translate(390px) rotateY(135deg) scale(1.2, 1.2);
  }
  100% {
    opacity: 1;
    transform: translate(402px) rotateY(103deg) scale(1.2, 1.2);
  }
}
@keyframes xmove {
  25% {
    opacity: 1;
    transform: translate(100px) rotateY(135deg) scale(1.2, 1.2);
  }
  50% {
    transform: translate(260px) rotateY(45deg) scale(1.6, 1.6);
  }
  85% {
    transform: translate(400px) rotateY(135deg) scale(1.2, 1.2);
  }
  100% {
    opacity: 1;
    transform: translate(484px) rotateY(0deg) scale(1, 1);
  }
}

/* Animation of the first two characters */
@keyframes show12 {
  0% {
    opacity: 1;
    transform: rotateY(0deg);
  }
  100% {
    opacity: 1;
    transform: rotateY(360deg);
  }
}
/* Animation of the third and subsequent characters */
@keyframes show3 {
  0% {
    opacity: 1;
    transform: rotateY(90deg);
  }
  100% {
    opacity: 1;
    transform: rotateY(360deg);
  }
}
#box div:nth-child(1) {
  animation: show12 1.2s linear forwards;
  animation-iteration-count: 2;
  animation-delay: 0.2s;
}
#box div:nth-child(2) {
  animation: show12 1.2s linear forwards;
  animation-iteration-count: 2;
  animation-delay: 0.4s;
}
#box div:nth-child(3) {
  animation: show3 1s linear 1 forwards;
  animation-delay: 0.7s;
}
#box div:nth-child(4) {
  animation: show3 1s linear 1 forwards;
  animation-delay: 0.9s;
}
#box div:nth-child(5) {
  animation: show3 1s linear 1 forwards;
  animation-delay: 1.1s;
}
#box div:nth-child(6) {
  animation: show3 1.2s linear 1 forwards;
  animation-delay: 1.3s;
}
#box div:nth-child(7) {
  animation: show3 1.2s linear 1 forwards;
  animation-delay: 1.6s;
}
</style>
