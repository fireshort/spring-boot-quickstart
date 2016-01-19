spring-boot-quickstart是基于Spring Boot技术，Pragmatic风格的JavaEE应用参考示例，是JavaEE世界中的主流技术选型，最佳实践的总结与演示。spring-boot-quickstart采用了江南白衣SpringSide里面的代码例子（向江南白衣致敬），但是用了Spring Boot重新集成。

一个迷你的TodoList应用，任务管理是一个典型的CRUD场景，还提供了任务管理的Restful API。用户注册、用户资料修改，管理员后台用户管理可以作为很多项目的初始骨架。

## 运行
项目用了redis来当缓存，必须先配置好redis，然后在application.properties里面改变相应的配置。

配置好maven之后，来到项目的根目录，执行 mvn spring-boot:run 启动项目，然后在浏览器打开 http://localhost/ 就可以访问项目。这种方式启动支持代码热加载。

Spring Boot可以打包生成一个可执行的jar，由于jsp的限制，必须打包成war格式。直接mvn package就可以打包生成可执行的war。
```batch
cd target
java -jar spring-boot-quickstart-0.0.1-SNAPSHOT.war 
```
就可以运行项目。

项目默认用h2database，在application.properties中设置了"spring.datasource.initialize=true"和"spring.datasource.platform=h2"，Spring Boot会自动导入schema-h2.sql、data-h2.sql来初始化数据库。如果要切换到使用mysql，只要在pom.xml中反注释掉mysql相关的dependency（同时把h2database的dependency注释掉），再修改application.properties中的相关配置即可。

## 更新日志
* 2016-1-7 BootStrap升级到3.3.6;
 
  BootStrap3.0版本最大的改变就是移动优先（mobile first），任意一个使用了BootStrap3.0以上的页面，在移动终端上会有比较好的输出和响应；

* 2016-1-19 增加redis作为缓存；

在UserService中演示；
@Cacheable/@CacheEvict 同时和 @Transactional 在一起的话不生效，所以缓存方法从AccountService抽出来，单独放到这个类。

## Spring Boot介绍
Spring Boot不是又一个Spring的子项目，本身并不提供Spring框架的核心特性以及扩展功能，是用于快速、敏捷地开发新一代基于Spring框架的应用程序。也就是说，它并不是用来替代Spring的解决方案，而是和Spring框架紧密结合用于提升Spring开发者体验的工具。同时它通过Maven/Gradle集成了大量常用的第三方库配置（例如Spring Security、JPA、JDBC、Mongo、Redis、Mail等等），Spring Boot应用中这些第三方库几乎可以零配置的开箱即用（out-of-the-box），大部分的Spring Boot应用都只需要非常少量的配置代码，开发者能够更加专注于业务逻辑。

传统基于Spring的Java Web应用，需要配置web.xml、一坨坨的applicationContext*.xml，将应用打成war包放入应用服务器(Tomcat, Jetty等)中并运行。

## 参考资料
1. [Spring Boot官方的Samples](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples);
2. [Spring Boot Rock'n'Roll](http://afoo.me/posts/2015-07-09-how-spring-boot-works.html);
 > 挖财专家[王福强](http://afoo.me/whoami.html)不错的Spring Boot入门文章。
3. [Spring Cache](http://blog.csdn.net/qq_25223941/article/details/50495607);
 > Spring Cache中文说明，简明扼要。
