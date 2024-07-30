<template>
    <div>
        <UserHeader @WallDataEvent="handleWallData"></UserHeader>
        <div class="button_update">
            <el-button type="primary" @click="dialogVisible=true">上传壁纸</el-button>
            <el-button class="goback_sub" type="primary" @click="goback">返回</el-button>
            <el-dialog
                name="photo"
                title="提示"
                :visible.sync="dialogVisible"
                width="21%">
                <el-row>
                    <span>标签：</span>
                    <el-input style="margin-top: 5px; width: 200px;" v-model="WallData.title"></el-input>
                </el-row>
                <el-upload
                    class="upload-demo"
                    ref="upload"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :file-list="fileList"
                    :auto-upload="false"
                    :on-change="handleFileChange">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                    <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                </el-upload>
            </el-dialog>
        </div>
        <div class="table_data">
            <el-table :data="WallData" style="width: 100%;">
                <el-table-column fixed prop="wallpaperID" label="ID编号" width="300"></el-table-column>
                <el-table-column label="壁纸" width="400">
                    <template slot-scope="{ row }">
                        <el-image :src="row.image_path" :preview-src-list="[row.image_path]" style="width: 100%; height: auto; border-radius: 15px;" alt="壁纸" ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="title" label="标签" width="400"></el-table-column>
                <el-table-column prop="uploaded_at" label="上传时间" width="300"></el-table-column>
                <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="text" size="small">修改</el-button>
                        <el-button type="text" size="small" @click="delwall(scope.row.wallpaperID)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
    import UserHeader from '@/components/Userheader.vue'
    export default{
        data(){
            return{
                WallData:[],
                dialogVisible: false,
                fileList:[],
            }
        },
        components: {
            UserHeader
        },
        methods:{
            goback(){
                this.$router.push('/Home')
            },
            getData: function(){
                this.axios.get('/getAllWall').then(response => {
                    this.WallData = response.data;
                })
                .catch(error => {
                    console.error('Error fetching images:', error);
                });
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            delwall: function(WallpaperID){
                this.axios.get('/delWallById?WallpaperID='+WallpaperID).then(response => {
                        if(response.data.code == 0){
                            alert("删除成功")
                            window.location.reload();
                        } else{
                            alert("删除失败")
                        }
                    })
                .catch(error => {
                    console.error('Error del images:', error);
                });
            },
            handleWallData(WallData){
                console.log('接收到的子组件数据:', WallData);
                this.WallData=WallData
            },
            submitUpload() {
                if (this.fileList.length === 0) {
                    alert("请选择文件");
                    return;
                }
                let formData = new FormData();
                formData.append('photo', this.fileList[0].raw);
                formData.append('title', this.WallData.title);

                this.axios.post('/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then(response => {
                    console.log(response.data);
                    alert("上传成功")
                    window.location.reload();
                    // 处理响应
                }).catch(error => {
                    console.error('上传失败:', error);
                    alert("上传失败")
                });
            },
            handleFileChange(file, fileList) {
                console.log(fileList)
                this.fileList = fileList; // 更新fileList以保存选中的文件
            },
        },
        mounted(){
            this.getData();
        }
    }
</script>
<style>
.table_data{
    margin-left: 200px;
    margin-right: 200px;
    margin-top: 20px;
}
.button_update{
    margin-top: 50px;
    margin-left: 200px;
}
.upload-demo{
    margin-top: 20px;
}
</style>