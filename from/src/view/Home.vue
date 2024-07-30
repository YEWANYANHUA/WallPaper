<template>
    <div>
        <Heater @WallDataEvent="handleWallData"></Heater>
        <el-divider></el-divider>
        <div class="img_quanju">
            <div v-for="(item,index) in paginatedImages" :key="index" class="img_div">
                <el-image class="img_header" :src="item.image_path" :preview-src-list="[item.image_path]"></el-image>
            </div>
        </div>
        <div class="yema">
            <el-pagination  background layout="prev, pager, next" 
                :total="WallData.length"
                :current-page.sync="currentPage"
                :page-size="pageSize"
                @current-change="handlePageChange"> </el-pagination>
        </div>
    </div>
</template>
<script>
    import Heater from '../components/header.vue';
    export default{
        name: 'App_Home',
        data(){
            return{
                WallData: [],
                currentPage: 1,
                pageSize: 15
            }
        },
        components: {
            Heater
        },
        computed: {
            paginatedImages() {
                const start = (this.currentPage - 1) * this.pageSize;
                const end = start + this.pageSize;
                return this.WallData.slice(start, end);
            }
        },
        mounted(){
            this.fetchImages();
        },
        methods:{
            handlePageChange(newPage) {
                this.currentPage = newPage;
            },
            fetchImages() {
                this.axios.get('/getAllWall').then(response => {
                    this.WallData = response.data;
                })
                .catch(error => {
                    console.error('Error fetching images:', error);
                });
            },
            handleWallData(WallData){
                console.log('接收到的子组件数据:', WallData);
                this.WallData=WallData
            }
        },
    }
</script>
<style>
.yema{
    margin-left: 1400px;

}
.img_header{
    border-radius: 15px;
    width: auto;
    height: 210px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.img_div{
    
    padding-left: 5px;
    display: inline;
    margin-left:5;
}
.img_quanju{
    margin-left: 100px;
}
html body{
        /* background-color:rgb(242, 245, 248); */
        background: linear-gradient(to right, rgb(255, 241, 252), rgb(218, 255, 255),rgb(228, 255, 255));
    }
</style>