package com.sinn.service;

import com.sinn.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/14
 */
public interface TypeService {
    Type saveType(Type type);

    Type getTypeByName(String name);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
