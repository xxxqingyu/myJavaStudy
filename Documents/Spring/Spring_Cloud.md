# Spring Cloud

## Exception

- 运行错误：
    > Error creating bean with name 'eurekaAutoServiceRegistration': Singleton bean creation not allowed while singletons of this factory are in destruction (Do not request a bean from a BeanFactory in a destroy method implementation!)

- 解决方案：
    > pom.xml 添加 spring-boot-starter-web 引用