package com.sinn.dao;

import com.sinn.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
