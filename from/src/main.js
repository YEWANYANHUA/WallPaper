import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import VueRouter from 'vue-router';
import routes from './router';
import axios from "axios";
import VueAxios from 'vue-axios';
import VueCookies from 'vue-cookies';


axios.defaults.baseURL='/api'

Vue.use(ElementUI);
Vue.use(VueCookies);
Vue.use(VueRouter);
Vue.use(VueAxios, axios);
Vue.config.productionTip = false

Vue.$cookies.config('7d');

const router = new VueRouter({
  routes
})

new Vue({
  router: router,
  render: h => h(App),
}).$mount('#app')