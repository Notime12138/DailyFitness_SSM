## 模仿Mall的学习项目

### 简介：
学习Github开源项目[mall-tiny](https://github.com/macrozheng/mall-learning) ，并根据自己的理解对原有功能进行学习、理解和完善，并且尝试添加一些功能，以完成学习目的。

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
* RabbitMQ
* Lombok

### 项目地址
* [Github](https://github.com/Notime12138/mall_springboot)
* [Swagger](http://localhost:8080/swagger-ui/index.html)
* [ElasticSearch](http://localhost:9200/)
* [Kibana](http://localhost:5601/)
* [RabbitMQ](http://localhost:15672/)

### 学习进度
* [x] SpringBoot + Mybatis（pageHelper和generator）基本框架配置
* [x] 整合Swagger 3.x 实现在线API文档
* [x] 整合Redis实现缓存功能
* [x] 整合SpringSecurity
* [x] JWT实现认证和授权
* [x] 整合SpringTask实现定时任务
* [x] 配置Spring Data Elasticsearch
* [x] 整合Elasticsearch实现商品搜索
* [ ] 整合MongoDB实现文档操作
* [x] 整合RabbitMQ实现延迟消息
* [ ] 整合OSS实现文件上传
* [ ] 完善下单，去掉订单功能

### 自定义功能
* [ ] 尝试添加扫码登录功能
* [ ] 添加邮箱注册并实现发送验证码
* [ ] 将邮箱账户与手机账户关联
* [ ] 

### 中间件服务启动
```text
启动Redis服务器
```
```text
启动RabbitMQ服务:
rabbitmq-service.bat start
```
```text
启动RabbitMQ服务:
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