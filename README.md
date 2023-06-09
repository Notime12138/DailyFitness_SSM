## Daily Fitness

### 简介：
使用多种数据库(MySQL,CouchDB,MongoDB)、AWS云服务、Docker、React、Swagger等，从Express.js框架迁移至SSM框架，用于查询并记录膳食营养、分析每日营养摄入、记录运动数据的Web服务。

目前更多的是架构上的迁移。

### 技术栈：
目前涵盖：
* SpringBoot 2.7.10
* Mybatis PageHelper 1.4.6
* Mybatis Generator
* Redis
* Swagger 3.0.0
* Hutool 常用工具类库
* SpringSecurity 和 JWT认证
* SpringTask
* Elasticsearch 8.7.0
* MongoDB
* RabbitMQ
* Lombok

### 项目地址
* [Github](https://github.com/Notime12138/DailyFitness_SSM)
* [Swagger](http://localhost:8080/swagger-ui/index.html)
* [ElasticSearch](http://localhost:9200/)
* [Kibana](http://localhost:5601/)
* [RabbitMQ](http://localhost:15672/)
* [MongoDB](http://localhost:27017/)

### 学习进度
* [x] SpringBoot + Mybatis（pageHelper和generator）基本框架配置
* [x] 整合Swagger 3.x 实现在线API文档
* [x] 整合Redis实现缓存功能
* [x] 整合SpringSecurity
* [x] 动态配置接口权限
* [x] JWT实现认证和授权
* [x] 整合SpringTask实现定时任务
* [x] 配置Spring Data Elasticsearch
* [x] 整合Elasticsearch实现商品搜索
* [x] 整合MongoDB实现文档操作
* [x] 整合RabbitMQ实现延迟消息
* [x] AOP切面整合接口访问日志
* [ ] 整合OSS实现文件上传
* [ ] 完善下单，完善订单功能

### 自定义功能
* [ ] 尝试添加扫码登录功能
* [x] 添加邮箱注册并实现发送验证码

### 中间件服务启动
```text
启动Redis服务
@echo off
cd D:\App\Redis
start redis-server.exe redis.windows.conf
```
```text
启动RabbitMQ服务(超时取消订单):
rabbitmq-service.bat start
```
```text
启动Elasticearch和kibana
```

### 参考文档
* [ChatGPT](https://chat.openai.com/chat)
* [Mybatis](https://mybatis.org/mybatis-3/zh/index.html)
* [Mybatis Generator](http://mybatis.org/generator/)
* [Mybatis PageHelper](https://pagehelper.github.io/docs/)
* [Swagger](https://swagger.io/docs/)
* [Redis](https://redis.io/docs/)
* [Hutool](https://hutool.cn/docs/#/)
* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
* [JWT Token](https://jwt.io/introduction)
* [Spring Task](https://spring.io/guides/gs/scheduling-tasks/)
* [Spring Data ES](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/)
* [Elasticsearch](https://www.elastic.co/guide/en/welcome-to-elastic/current/getting-started-general-purpose.html)
* [Kibana](https://www.elastic.co/guide/en/kibana/current/get-started.html)
* [RabbitMQ](https://www.rabbitmq.com/getstarted.html)
* [MongoDB](https://www.mongodb.com/docs/guides/atlas/account/)

### 遇到的困难
* Maven 依赖爆红
```text
Intellij 20.2.3 不支持JDK 14 以上的版本，降低至JDK 11
不支持Maven 2.8.3 版本（版本过高），降低至 2.6.3
```
* SpringBoot 3.x版本报错
```text
Mybatis 找不到 'SqlSessionFactory' 或 'SqlSessionTemplate' ，需要在MybatisConfig文件中手动注入。
调整至SpringBoot 2.x 版本后解决
```
* Swagger 不兼容
```text
Swagger 2.x 版本对SpringBoot 2.5.x 以上版本不支持，将MVC匹配模式更改至ant_path_matcher后也未能解决。
方案1：降低SpringBoot版本
方案2：将Swagger升级至 3.x ，并额外配置SwaggerConfig文件
```
* Swagger 兼容性
```text
ApiKey是SecurityScheme的子类但是无法转换
原因：在Swagger 3中，securitySchemes配置只接受SecurityScheme类型的安全方案，因此当你试图使用ApiKey时，就会提示类型不兼容的错误。

解决方案：创建ApiKey对象后，使用stream将ApiKey转换成SecurityScheme后再返回。
```
* Bean 循环依赖
```text
securityConfig和umsAdminServiceImpl循环依赖
方案1： @Lazy懒加载，在被使用到的时候再去加载
方案2： 拆分SecurityConfig这个configuration，将多余的bean转移到其他的configuration文件中
方案3： 允许循环依赖（不推荐）
```
* Elasticsearch 无法解析响应正文
```text
Elasticsearch Unable to parse response body
api 返回code 500，但使用kibana查询时有结果
方案1：降低Elasticsearch版本到7.17.4
方案2：捕捉Spring data Elasticsearch的getAll方法的异常并抛出。
```
* 日志文件会把账号密码以及登录返回的JWT密钥明文记录
```text
描述：判断ParamBody或ResponseBody中是否有password或者token字段，使用占位符或敏感信息脱敏。
logback和log4j，可以通过配置加密算法来加密敏感信息，例如对称加密算法（如AES）、非对称加密算法（如RSA）或哈希算法（如SHA256）等。
```
```text
配置文件脱敏：
jasypt 
引入 jasypt-spring-boot-starter jar
配置 jasypt.encryptor.password 和 加密后的内容格式
```
```text
接口返回数据脱敏：
1 Mybaits插件，在查询时脱敏

2 整合Jackson，在序列化阶段脱敏

3 基于Sharding Sphere实现数据脱敏
配置脱敏注释和脱敏策略
定制JSON序列化实现（通过注释在属性上来判断需要脱敏的字段）

4 jasypt接口脱敏
在方法上添加@EncryptMethod，再将@EncryptField字段加密
使用AOP处理需要加密的字段
参考：https://jianshu.com/p/8b223bd81166
```

```text
日志文件脱敏：
将Spring Boot日志框架从logback切换到log4j2
在/resource下配置log4j2.xml
自定义PatternLayout实现数据脱敏
```

* 基于路径的动态权限控制代替基于方法的权限控制
```text
原本的方法：@PreAuthorize配置访问接口需要的权限，使用时从数据库获取用户的权限进行对比。
现在的方法：使用doFilter（DynamicSecurityFilter中）方法设置白名单和需要鉴权的接口，调用AccessDecisionManager中的decide方法用于鉴权操作，而decide方法中的configAttributes参数（当前接口所需要的权限）会通过SecurityMetadataSource中的getAttributes方法来获取。
当需要开启动态权限控制的时候，只要创建一个DynamicSecurityService对象即可。
```
参考：[配置、接口返回数据、日志数据脱敏](https://juejin.cn/post/7004641512596176910)
