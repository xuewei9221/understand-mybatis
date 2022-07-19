package com.example.mybatis.entity;

/**
 * <p>
 * 测试对象
 * </p>
 *
 * @author xuewei
 * @since 2022-07-14 11:36
 */
public class MybatisDemoDTO {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "MybatisDemoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
