<template>
    <div>
        <div class="header-div">
            <el-button type="primary" @click="goLogin">登录后台</el-button>
        </div>
        <div class="outer-div">
            <el-row>
                <el-col :span="6">
                    <h3 class="h3_header">壁纸分享系统</h3>
                </el-col>
            </el-row>
            <el-input style="width: 200px;" v-model="title" placeholder="关键字"></el-input>
            <el-button style="margin-left: 5px;"  type="primary" round @click="subhub">搜索</el-button>
        </div>
    </div>
</template>
<script>
    export default{
        name: 'AppHeader',
        data() {
            return {
                title:'',
                WallData:[]
            }
        },
        methods:{
            goLogin:function(){
                this.$router.push('/login').then(() => {
                    window.location.reload();
                })
            },
            subhub(){
                if (this.title==='') {
                    alert("关键字为空")
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
        },
    }
</script>
<style>
    .h3_header{
        font-size: 24px;  
    }
    .outer-div{
        margin-top: 2px;
        margin-left: 200px;
    }
    .header-div{
        display: flex;
        justify-content: flex-end;
        margin-right: 50px;
        margin-top: 30px;
    }
</style>