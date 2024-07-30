import Login from '@/view/UserLogin.vue';
import Register from '@/view/UserRegister.vue';
import Home from '@/view/Home.vue'
import UserHome from '@/view/UserHome.vue'

export default [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path:'/register',
        component: Register
    },
    {
        path:'/login',
        component: Login
    },
    {
        path:'/home',
        component: Home
    },
    {
        path:'/userhome',
        component: UserHome
    },
]