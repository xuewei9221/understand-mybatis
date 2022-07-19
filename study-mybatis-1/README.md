# study-mybatis-1


##一、前言
不得不说Spring给我们带来了很多的便利：IOC解决对象的依赖关系、AOP切面编程、声明式的事务支持、方便集成其它框架等等。
<br />
<br />
对于MyBatis，Spring也提供了快速集成方式，真是宝刀在手，天下我有。但今天我们不是要研究Spring怎么集成MyBatis，而是要研究一下MyBatis脱离了Spring还能玩的起来吗？答案是能肯定的。
<br />
<br />


##二、思路
对于MyBatis的使用，我们需要知道我们的操作是使用的SqlSession也就是一个数据库连接，而SqlSession是由SqlSessionFactory而构建出来的；
<br />
对于SqlSessionFactory有一些配置如：数据库连接信息、mappr路径就能够进行创建。
<br />
<br />
所以我们要进行编写一个mybatis hello world的话，就可以分解成以下几个步骤：
* 1、创建一个maven项目；
* 2、添加mybatis的依赖和数据库驱动的依赖，这里mybatis使用的是3.5.1的版本；
```Xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.5.1</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.16</version>
</dependency>
```
创建`study_mybatis`数据库，并且创建表`mybatis_demo`：
```Sql
DROP TABLE IF EXISTS `mybatis_demo`;
CREATE TABLE `mybatis_demo` (
    id` BIGINT ( 11 ) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR ( 100 ) DEFAULT NULL,
    PRIMARY KEY ( `id` )
);
```

* 3、添加mybatis的配置，主要是数据库连接信息、实体类的路径、mapper的路径；
* 4、编写实体类，通过InputStream载入配置文件，构建SqlSessionFactory；



##三、总结
* 1、MyBatis在没有Spring的庇护下也是能自己运行。
* 2、MyBatis单独使用的话，主要是需要配置mybatis-config.xml配置文件，通过sqlSession可以进行数据库的操作，更优雅的方式就是通过mapper面向接口的方式。

![image](doc/image/640.jpg)