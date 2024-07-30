<template>
    <div>
        <div class="outer-div">
            <el-row>
                <el-col :span="6">
                    <h3 class="h3_header">壁纸分享系统(后台管理)</h3>
                </el-col>
            </el-row>
            <div>
                <el-input style="width: 400px;" v-model="title" placeholder="关键字"></el-input>
                <el-button style="margin-left: 5px;"  type="primary" round @click="subhub">搜索</el-button>
            </div>
        </div>
    </div>
</template>
<script>
    export default{
        name:'AppUserHeader',
        data(){
            return{
                WallData:[],
                title:''
            }
        },
        methods:{
            subhub(){
                if(this.title===''){
                    alert('关键字为空!')
                }else{
                    this.axios.get('/getWallByTitle?title='+this.title).then(response =>{
                        if (response.status === 200) {
                            alert("搜索成功")
                            console.log(response)
                            this.WallData = response.data
                            /* window.location.reload(); */
                            this.$emit('WallDataEvent', this.WallData);
                        } else{
                            alert("搜索失败")
                        }
                    })
                }
            }
        }
    }
</script>
<style>
.goback_sub{
    display: inline;
}

</style>