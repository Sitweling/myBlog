package com.sinn.service;

import com.sinn.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTagByName(String name);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id,Tag tag);

    void  deleteTag(Long id);
}