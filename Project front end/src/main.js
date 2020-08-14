import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";

// Element-ui
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import locale from "element-ui/lib/locale/lang/en";
Vue.use(ElementUI, { locale });

// Markdown editor
import mavonEditor from "mavon-editor";
import "mavon-editor/dist/css/index.css";
Vue.use(mavonEditor);

// Prevent vue from prompting in production mode at startup
Vue.config.productionTip = false;

// Global registration, and use this.$axios for ajax asynchronous request
Vue.prototype.$axios = axios;

// Cross-domain proxy, front-end request are sent to base url
axios.defaults.baseURL = "http://localhost:8666";

// Bring cookies, and activate withCredentials function through axios
axios.defaults.withCredentials = true;

// Login permission route interceptor, request before accessing each route
router.beforeEach((to, from, next) => {
  // Request nav information when the user is logged in and accesses the path starting with /admin
  if (store.state.username && to.path.startsWith("/admin")) {
    initAdminNav(router, store);
  }
  // Determine if the route requires login permission
  if (to.meta.requireAuth) {
    // Determine whether the user information is stored in the store, if it exists, then pass
    if (store.state.username) {
      // Verify backend authentication API
      axios.get("/authentication").then(response => {
        if (response.data) {
          next();
        }
      });
    } else {
      alert("Login required to access");
      // Store the visited page path (to jump to the visited page after login)
      next({
        path: "/login",
        query: { redirect: to.fullPath }
      });
    }
  } else {
    next();
  }
});

// Refer to the formatRoutes method and add data to the route
const initAdminNav = (router, store) => {
  axios.get("/nav").then(response => {
    if (response && response.status === 200) {
      var fmtRoutes = formatRoutes(response.data);
      router.addRoutes(fmtRoutes);
      store.commit("initAdminNav", fmtRoutes);
    }
  });
};

// Organize the nav data from the database into a format that can be recognized by the routing
const formatRoutes = routes => {
  let fmtRoutes = [];
  // Judge whether Nav contains sub-items, and recurse if it contains
  routes.forEach(route => {
    if (route.children) {
      route.children = formatRoutes(route.children);
    }
    // Resolve path
    let fmtRoute = {
      path: route.path,
      /**
       * Import the components and use Vue's asynchronous
       * component loading mechanism to complete the import while parsing
       */
      component: resolve => {
        require(["./components/admin/" + route.component + ".vue"], resolve);
      },
      name: route.name,
      icon: route.icon,
      children: route.children
    };
    fmtRoutes.push(fmtRoute);
  });
  return fmtRoutes;
};

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
