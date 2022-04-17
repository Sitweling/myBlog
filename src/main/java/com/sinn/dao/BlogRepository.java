package com.sinn.dao;

import com.sinn.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend= true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Blog b set b.views=b.views+1 where b.id=?1")
    int updateViews(Long id);

    @Query(value = "select DATE_FORMAT(b.update_time, '%Y') from t_blog b group by DATE_FORMAT(b.update_time, '%Y') order by b.update_time desc ",nativeQuery = true)
    List<String> findGroupYear();


    /*本方法存在表的映射问题，是关于驼峰命名转换的
    * 但是由于之前的查询已经确认，现在修改驼峰规则可能导致不可预知的错误，因此本错误不修改
    * 数据库字段与实体类字段名不一致，需要取消自动将驼峰命名转为下划线形式
    * */
    @Query(value = "select  * from t_blog b where DATE_FORMAT(b.update_time, '%Y') =#{year} " , nativeQuery = true)
    List<Blog> findByYear(String year);
}
