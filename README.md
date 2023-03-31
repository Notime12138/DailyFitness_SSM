## 模仿Mall的学习项目

### 简介
目前涵盖：
* SpringBoot 2.7.10
* Mybatis pageHelper 1.4.6
* Swagger 3.0.0
* Hutool 常用工具类库
* SpringSecurity

### 项目地址
* [Github](https://github.com/Notime12138/mall_springboot)
* [Swagger](http://localhost:8080/swagger-ui/index.html)

### 学习进度
* [x] SpringBoot + Mybatis（pageHelper和generator）基本框架配置
* [x] 整合Swagger 3.x 实现在线API文档
* [x] 整合Redis实现缓存功能
* [x] 整合SpringSecurity
* [ ] JWT实现认证和授权
* [ ] 整合SpringTask实现定时任务
* [ ] 整合Elasticsearch实现商品搜索
* [ ] 整合MongoDB实现文档操作
* [ ] 整合RabbitMQ实现延迟消息
* [ ] 整合OSS实现文件上传


* [ ] 尝试添加扫码登录功能
* [ ] 添加邮箱注册并实现发送验证码
* [ ] 将邮箱账户与手机账户关联
* [ ] 


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