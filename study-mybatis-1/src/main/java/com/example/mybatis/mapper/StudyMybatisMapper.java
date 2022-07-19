package com.example.mybatis.mapper;

import com.example.mybatis.entity.MybatisDemoDTO;

import java.util.List;

/**
 * <p>
 * 测试Mapper
 * </p>
 *
 * @author xuewei
 * @since 2022-07-18 17:32
 */
public interface StudyMybatisMapper {


    MybatisDemoDTO getById(long id);


    List<MybatisDemoDTO> getAll();


}
