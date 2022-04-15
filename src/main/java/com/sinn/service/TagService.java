package com.sinn.service;

import com.sinn.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    List<Tag> listTag();

    List<Tag> listTag(String ids);
    Tag updateTag(Long id,Tag tag);

    void  deleteTag(Long id);
}
