package com.example.back.controller;

import com.example.back.dao.WallDao;
import com.example.back.pojo.Wall;
import com.example.back.util.Result;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@Slf4j
@Repository
public class WallController {

    @Autowired
    private WallDao wallDao;

    @RequestMapping(value = "getAllWallPath", method = RequestMethod.GET)
    public List<String> getAllWallPath() {
        List<String> newWallPath = wallDao.findAllImagePath();
        for (int i = 0; i < newWallPath.size(); i++) {
            newWallPath.set(i,"http://localhost:8080"+newWallPath.get(i));
        }
        return newWallPath;
    }

    @RequestMapping(value = "getAllWall", method = RequestMethod.GET)
    public List<Wall> getAllWall() {
        List<Wall> AllWall = wallDao.findAll();
        for (Wall wall:AllWall){
            wall.setImage_path("http://localhost:8080"+wall.getImage_path());
        }
        return AllWall;
    }

    @RequestMapping(value = "delWallById",method = RequestMethod.GET )
    public Result<Wall> delWallById(@RequestParam Integer WallpaperID){
        Wall wall = wallDao.findWallById(WallpaperID);
        wallDao.deleteById(WallpaperID);
        if(wall!=null){
            return Result.success(wall,"删除成功！");
        }else{
            return Result.error("456","删除失败！");
        }
    }
    @PostMapping("upload")
    public String upload(@RequestParam("title") String title,@RequestParam MultipartFile photo, HttpServletRequest request)throws IOException {
        System.out.println("/upload/"+photo.getOriginalFilename());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
        String formattedDate = formatter.format(now);
        Wall wall = new Wall(null,title,photo.getOriginalFilename(),formattedDate);
        wall.setImage_path("/"+wall.getImage_path());
        System.out.println("文件大小："+photo.getSize());
        System.out.println(photo.getContentType());
        System.out.println(photo.getOriginalFilename());
        System.out.println(System.getProperty("user.dir"));
        String path = request.getServletContext().getRealPath("/upload/");
        wallDao.insert(wall);
        savaFile(photo,path);
        return "上传成功";
    }
    @RequestMapping(value = "getWallByTitle",method = RequestMethod.GET)
    public List<Wall> getWallByTitle(@RequestParam String title){
        List<Wall> Wall = wallDao.findAllWallByTitle(title);
        for (Wall newwall : Wall){
            newwall.setImage_path("http://localhost:8080"+newwall.getImage_path());
        }
        return Wall;
    }
    @RequestMapping(value = "getWallPathByTitle",method = RequestMethod.GET)
    public List<String> getWallPathByTitle(@RequestParam String title){
        return wallDao.findAllWallPathByTitle(title);
    }
    public void savaFile(MultipartFile image_path,String path)throws IOException{
        File upDir = new File(path);
        if (!upDir.exists()){
            upDir.mkdirs();
        }
        File file = new File(path+image_path.getOriginalFilename());
        image_path.transferTo(file);
    }
}
