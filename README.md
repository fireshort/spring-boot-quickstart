spring-boot-quickstart是基于Spring Boot技术，Pragmatic风格的JavaEE应用参考示例，是JavaEE世界中的主流技术选型，最佳实践的总结与演示。spring-boot-quickstart采用了江南白衣SpringSide里面的代码例子，但是用Spring Boot来重新集成。

## 运行
配置好maven之后，来到项目的根目录，执行 mvn spring-boot:run 启动项目，然后在浏览器打开 http://localhost/ 就可以访问项目。

Spring Boot可以打包生成一个可执行的jar，由于jsp的限制，必须打包成war格式。直接mvn package就可以打包生成可执行的war。

cd target

java -jar spring-boot-quickstart-0.0.1-SNAPSHOT.war 

就可以运行项目。

项目默认用h2database，在application.properties中设置了"spring.datasource.initialize=true"和"spring.datasource.platform=h2"，Spring Boot会自动导入schema-h2.sql、data-h2.sql来初始化数据库。如果要切换到使用mysql，只要在pom.xml中反注释掉mysql相关的dependency（同时把h2database的dependency注释掉），再修改application.properties中的相关配置即可。

## Spring Boot介绍
### Spring Boot不是什么
### Spring Boot的特性
