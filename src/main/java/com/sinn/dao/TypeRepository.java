package com.sinn.dao;

import com.sinn.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/14
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    //自定义查询，根据分页的排序信息获得前N条博客数目最多的Type
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
