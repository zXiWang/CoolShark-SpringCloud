import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        children: [
            {
                path: '/sys-admin/temp/admin/list',
                component: () => import('../views/sys-admin/temp/AdminListView.vue')
            },
            {
                path: '/sys-admin/temp/album/add-new',
                component: () => import('../views/sys-admin/temp/AlbumAddNewView.vue')
            },
            {
                path: '/sys-admin/temp/album/list',
                component: () => import('../views/sys-admin/temp/AlbumListView.vue')
            },
            {
                path: '/sys-admin/temp/brand/add-new',
                component: () => import('../views/sys-admin/temp/BrandAddNewView.vue')
            },
            {
                //品牌列表
                path: '/sys-admin/temp/brand/list',
                component: () => import('../views/sys-admin/temp/BrandListView.vue')
            },
            {
                path: '/sys-admin/temp/category/add-new',
                component: () => import('../views/sys-admin/temp/CategoryAddNewView.vue')
            },
            {
                path: '/sys-admin/temp/attributeTemplate/add-new',
                component: () => import('../views/sys-admin/temp/AttributeTemplateAddNewView.vue')
            },
            {
                //属性模板列表
                path: '/sys-admin/temp/attributeTemplate/list',
                component: () => import('../views/sys-admin/temp/AttributeTemplateListView.vue')
            },
            {
                //添加属性界面
                path: '/sys-admin/temp/attribute/add-new',
                component: () => import('../views/sys-admin/temp/AttributeAddNewView.vue')
            },

        ]
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '/login',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/LoginView.vue')
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
