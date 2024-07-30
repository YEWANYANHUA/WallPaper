<template>
    <div class="overall">
        <div class="login">
            <div class="form-item"><h3>用户登录界面</h3></div>
            <el-row>
                <el-col>
                    <div class="form-item">
                        <span>用户名：</span>
                        <el-input v-model="login.username" style="width: 250px; "></el-input>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <div class="form-item">
                        <span>密码：</span>
                        <el-input v-model="login.password" style="width: 250px;" show-password></el-input>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <div class="form-item">
                    <el-button type="primary" @click="subhub">登录</el-button>
                    <el-button type="primary" @click="register">注册</el-button>
                </div>
            </el-row>
        </div>
    </div>
    
</template>
<script>
    export default{
        name: 'App_userlogin',
        data() {
            return {
                login:{
                    username:'',
                    password:''
                }
            }
        },
        methods:{
            register: function(){
                this.login.username='';
                this.login.password=''
                this.$router.push('/register').then(() => {
                    window.location.reload();
                })
            },
            subhub: function(){
                this.axios.post('/user/login', this.login).then((response) => {
                    console.log(response.data.msg)
                    if (response.data.code == 0) {
                        alert('登录成功')
                        this.$cookies.set('username', this.login.username,'1d');
                        this.$cookies.set('password', this.login.password,'1d');
                        this.$router.push('/UserHome')
                        console.log(response.data.msg)
                    }else {
                        alert(`登录失败, ${response.data.msg}`)
                    }
                }) 
                .catch((error) => {
                // Handle the error here
                console.error('There was an error!', error);
                alert(`登录失败, 出现错误: ${error.message}`);
            });
            }
        }
    }
</script>
<style>
.login{
    background-color: #FFF9;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    width:600px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    height: 200px;
    padding: 100px;
    border-radius: 10px;
}
.form-item {
    display: flex;
    align-items: center;
    margin-bottom: 15px; /* 每个表单项的下边距 */
    justify-content: center;
    
}

.form-item span {
    margin-right: 10px; /* 标签和输入框之间的间距 */
    width: 80px; /* 标签宽度，确保对齐 */
}
.overall{
    display: flex;
    width: 100%;
    height: 100%;
    margin: 0;
    background-image: url('../image/back.jpg');
    background-size: cover; /* 覆盖整个元素 */
    background-repeat: no-repeat; /* 防止图像重复 */
    background-position: center;
}
html, body {
    height: 100%; /* 设置 html 和 body 的高度为 100% */
    margin: 0; /* 移除默认边距 */
    padding: 0; /* 移除默认填充 */
}
</style>