# MyBatis缓存


## 一、缓存介绍
MyBatis中提供查询缓存，用于减轻数据库的压力，提高数据库的性能。  
MyBatis提了了一级缓存和二级缓存。  
![image](https://raw.githubusercontent.com/xuewei9221/understand-mybatis/main/doc/image/03c3491e481640928d7d9daf83996e22.png)
<br><br>
#### 一级缓存
一级缓存是SQLSession级别缓存，在操作数据库时都需要构造SQLSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据，不同的SQLSession之间的缓存数据区域是互不影响的。
<br><br>
Mybatis 一级缓存的作用域是同一个 SqlSession，在同一个 sqlSession 中两次执行相同的 sql 语句，第一次执行完毕会将数据库中查询的数据写到缓存（内存），第二次会从缓存中获取数据将不再从数据库查询，从而提高查询效率。
<br><br>
当一个 sqlSession 结束后该 sqlSession 中的一级缓存也就不存在了。Mybatis 默认开启一级缓存。
<br><br>
#### 二级缓存
Mybatis 二级缓存是多个 SqlSession 共享的，其作用域是 mapper 的同一个 namespace，不同的 sqlSession 两次执行相同 namespace 下的 sql 语句且向 sql 中传递参数也相同即最终执行相同的 sql 语句，第一次执行完毕会将数据库中查询的数据写到缓存（内存），第二次会从缓存中获取数据将不再从数据库查询，从而提高查询效率。
<br><br>
Mybatis 默认没有开启二级缓存需要在 setting 全局参数中配置开启二级缓存。