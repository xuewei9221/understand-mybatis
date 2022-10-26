package com.example.mybatis.main;

import com.example.mybatis.entity.MybatisDemoDTO;
import com.example.mybatis.mapper.StudyMybatisMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * study-mybatis-1
 * </p>
 *
 * @author xuewei
 * @since 2022-07-14 17:18
 */
public class App {


    /**
     *
     * 通过InputStream就可以构造SqlSessionFactory，有了SqlSessionFactory就可以打开一个SqlSession了，SqlSession就是一个数据库连接，就可以进行数据库操作了
     *
     */
    public static void main(String[] args) throws IOException {
        // mybatis配置文件地址
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建数据库会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 使用sqlSession直接查询
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // true 不开启事务，自动提交

        // 不定义Mapper接口调用(使用这种方式,不利于代码的维护,看起来也不清爽)
        MybatisDemoDTO mybatisDemo = sqlSession.selectOne("com.example.mybatis.mapper.MybatisDemoMapper.getById",1L);
        System.out.println(mybatisDemo);

        List<MybatisDemoDTO> demos = sqlSession.selectList("com.example.mybatis.mapper.MybatisDemoMapper.getAll");
        System.out.println(demos);

        System.out.println("---------------------分割线---------------------");

        // 定义Mapper接口调用
        StudyMybatisMapper studyMybatisMapper = sqlSession.getMapper(StudyMybatisMapper.class);
        System.out.println(System.currentTimeMillis());
        MybatisDemoDTO demo1 = studyMybatisMapper.getById(1);
        System.out.println(System.currentTimeMillis());
        MybatisDemoDTO demo2 = studyMybatisMapper.getById(2);
        System.out.println(System.currentTimeMillis());
        MybatisDemoDTO demo3 = studyMybatisMapper.getById(1);
        System.out.println(System.currentTimeMillis());
        System.out.println(demo1);
        List<MybatisDemoDTO> demoList = studyMybatisMapper.getAll();
        System.out.println(demoList);

    }



}
