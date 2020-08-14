import Vue from "vue";
import VueRouter from "vue-router";

import Home from "../views/Home.vue";
import AboutUs from "../views/AboutUs.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Blog from "../views/BolgIndex.vue";
import ArticleDetail from "../components/bolg/ArticleDetail.vue";
import Download from "../views/DownloadIndex.vue";
import DocumentDetail from "../components/download/DocumentDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/about",
    name: "AboutUs",
    component: AboutUs
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/blog",
    name: "Blog",
    component: Blog
  },
  {
    path: "/blog/detail",
    name: "ArticleDetail",
    component: ArticleDetail
  },
  {
    path: "/download",
    name: "Download",
    component: Download
  },
  {
    path: "/download/detail",
    name: "DocumentDetail",
    component: DocumentDetail,
    // Access to this route requires authentication
    meta: {
      requireAuth: true
    }
  },
  {
    path: "/user/my",
    name: "Profile",
    component: () => import("@/components/admin/user/Profile"),
    // Access to this route requires authentication
    meta: {
      requireAuth: true
    }
  },
  {
    path: "/md",
    name: "MarkdownEditor",
    component: () => import("@/components/bolg/MarkdownEditor"),
    // Access to this route requires authentication
    meta: {
      requireAuth: true
    }
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
