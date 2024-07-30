package com.example.back.dao;

import com.example.back.pojo.Wall;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface WallDao {
    @Delete("DELETE FROM wall WHERE WallpaperID = #{WallpaperID}")
    void deleteById(Integer WallpaperID);
    @Select("SELECT * FROM wall")
    List<Wall> findAll();
    @Select("SELECT * FROM wall WHERE WallpaperID = #{WallpaperID}")
    Wall findWallById(Integer WallpaperID);
    @Select("SELECT image_path FROM wall")
    List<String> findAllImagePath();
    @Insert("INSERT INTO wall(title, image_path, uploaded_at) VALUES (#{title}, #{image_path}, #{uploaded_at})")
    @Options(useGeneratedKeys = true, keyProperty = "wallpaperID")
    void insert(Wall wall);
    @Select("SELECT * FROM wall WHERE title = #{title}")
    List<Wall> findAllWallByTitle(String title);
    @Select("SELECT image_path FROM wall WHERE title = #{title}")
    List<String> findAllWallPathByTitle(String title);
}
