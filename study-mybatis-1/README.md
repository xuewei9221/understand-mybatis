# MyBatis脱离Spring运行


## 一、前言
不得不说Spring给我们带来了很多的便利：IOC解决对象的依赖关系、AOP切面编程、声明式的事务支持、方便集成其它框架等等。
<br />
<br />
对于MyBatis，Spring也提供了快速集成方式，真是宝刀在手，天下我有。但今天我们不是要研究Spring怎么集成MyBatis，而是要研究一下MyBatis脱离了Spring还能玩的起来吗？答案是能肯定的。
<br />
<br />


## 二、思路
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


## 三、MyBatis的工作原理
![image](https://raw.githubusercontent.com/xuewei9221/understand-mybatis/main/doc/image/640.jpg)

+ （1）读取MyBatis配置文件
    + 读取 MyBatis 配置文件：mybatis-config.xml，mybatis-config.xml 为 MyBatis 的全局配置文件，配置了 MyBatis 的运行环境等信息，例如数据库连接信息。


+ （2）加载映射文件
    + 加载通过Mapper.xml配置或者注解的SQL映射，，该文件中配置了操作数据库的 SQL 语句，需要在 MyBatis 配置文件 mybatis-config.xml 中加载。


+ （3）构造会话工厂：SqlSessionFactory
    + 通过 MyBatis 的环境等配置信息构建会话工厂 SqlSessionFactory。


+ （4）创建会话对象：SqlSession
    + 由会话工厂创建 SqlSession 对象，该对象中包含了执行 SQL 语句的所有方法。


+ （5）Executor执行器
    + MyBatis 底层定义了一个 Executor 接口来操作数据库，它将根据 SqlSession 传递的参数动态地生成需要执行的 SQL 语句，同时负责查询缓存的维护。


+ （6）MappedStatement对象
    + 在 Executor 接口的执行方法中有一个 MappedStatement 类型的参数，该参数是对映射信息的封装，用于存储要映射的 SQL 语句的 id、参数等信息。也就是Mapper.xml文件配置的最终是MappedStatement实体类进行对应。


+ （7）输入参数映射
    + 输入参数类型可以是 Map、List 等集合类型，也可以是基本数据类型和 POJO 类型。输入参数映射过程类似于 JDBC 对 preparedStatement 对象设置参数的过程。


+ （8）输出结果映射
    + 输出结果类型可以是 Map、 List 等集合类型，也可以是基本数据类型和 POJO 类型。输出结果映射过程类似于 JDBC 对结果集的解析过程。



## 四、MyBatis的核心组件
MyBatis 的核心组件分为 4 个部分
+ （1）SqlSessionFactoryBuilder（构造器）
    + 会根据配置或者代码来生成 SqlSessionFactory，采用的是分步构建的 Builder 模式。


+ （2）SqlSessionFactory（工厂接口）
    + 依靠它来生成 SqlSession，使用的是工厂模式。


+ （3）SqlSession（会话）
    + 一个既可以发送 SQL 执行返回结果，也可以获取 Mapper 的接口。在现有的技术中，一般我们会让其在业务逻辑代码中“消失”，而使用的是 MyBatis 提供的 SQL Mapper 接口编程技术，它能提高代码的可读性和可维护性。


+ （4）SQLMapper（映射器）
    + MyBatis 新设计存在的组件，它由一个 Java 接口和 XML 文件（或注解）构成，需要给出对应的 SQL 和映射规则。它负责发送 SQL 去执行，并返回结果。



## 五、总结
* 1、MyBatis在没有Spring的庇护下也是能自己运行。
* 2、MyBatis单独使用的话，主要是需要配置mybatis-config.xml配置文件，通过sqlSession可以进行数据库的操作，更优雅的方式就是通过mapper面向接口的方式。


