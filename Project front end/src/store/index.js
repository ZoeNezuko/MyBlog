import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //Assign a value to the user object when this method is triggered
    /**
     * Determine whether there is a user in the local storage,
     * if there is a username object,
     * otherwise set the username to empty
     */
    username:
      window.localStorage.getItem("username") == null
        ? ""
        : JSON.parse(window.localStorage.getItem("username" || "[]")),
    adminNavs: []
  },
  mutations: {
    initAdminNav(state, navs) {
      state.adminNavs = navs;
    },
    login(state, data) {
      state.username = data;
      window.localStorage.setItem("username", JSON.stringify(data));
    },
    logout(state) {
      state.username = "";
      window.localStorage.removeItem("username");
      state.adminNavs = [];
    }
  },
  actions: {},
  modules: {}
});
