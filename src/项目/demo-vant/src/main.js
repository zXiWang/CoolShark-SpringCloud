import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vant, {AddressList, Area, Field, Form, List} from 'vant'
import 'vant/lib/index.css'

Vue.use(List);

Vue.use(AddressList);
Vue.use(Area);
Vue.use(Form);
Vue.use(Field);
Vue.use(Vant)

Vue.config.productionTip = false


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
