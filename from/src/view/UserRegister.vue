<template>
    <div class="overall">
        <div class="login">
            <div class="form-item"><h3>用户注册界面</h3></div>
            <el-row>
                <el-col>
                    <div class="form-item">
                        <span>用户名：</span>
                        <el-input v-model="register.username" style="width: 250px; "></el-input>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <div class="form-item">
                        <span>邮箱：</span>
                        <el-input v-model="register.email" style="width: 250px;"></el-input>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <div class="form-item">
                        <span>密码：</span>
                        <el-input v-model="register.password" style="width: 250px;" show-password></el-input>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <div class="form-item">
                    <el-button type="primary" @click="subhub">注册</el-button>
                    <el-button type="primary" @click="goback">返回</el-button>
                </div>
            </el-row>
        </div>
    </div>
</template>
<script>
    export default{
        name: 'App_userRegister',
        data() {
            return {
                register:{
                    username:'',
                    password:'',
                    email:''
                }
            }
        },
        methods:{
            goback: function () {
                this.$router.push('/login')
            },
            subhub: function () {
                if (this.register.user == "") {
                    alert("用户名不能为空！")
                } else if(this.register.password == "") {
                    alert("密码不能为空！")
                } else if(this.register.email == ""){
                    alert("邮箱不能为空！")
                } else {
                    this.axios.post('/user/register', this.register).then((response) => {
                        if (response.data.code == 0) {
                            alert('注册成功')
                            this.$router.push('/login')
                        }else {
                            alert(`注册失败, ${response.data.data}`)
                        }
                    })
                }
            },
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