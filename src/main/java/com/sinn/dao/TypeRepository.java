package com.sinn.dao;

import com.sinn.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/14
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
