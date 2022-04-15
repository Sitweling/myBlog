package com.sinn.service;

import com.sinn.dao.TagRepository;
import com.sinn.exception.NotFoundException;
import com.sinn.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
@Service
public class TagServiceImpl implements TagService{



    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }
    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getById(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Tag byId = tagRepository.getById(id);
        if(byId==null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,byId);
        return  tagRepository.save(byId);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
