package com.example.dao.mapper;

import com.example.dao.dos.Archives;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ArchivesMapper {

    /**
     *文章归档
     */
    @Select("select year(from_unixtime(create_date/1000)) as year,month(from_unixtime(create_date/1000)) as month,count(*) as count from ms_article group by year,month")
    List<Archives> selectArchives();
}
