![](img/container-magic.png) 

spring container工作原理图
========================================================
![](img/beanFactory%20hierarchy.PNG) 

BeanFactory类结构
========================================================
##容器初始化流程
1. 初始化environment,如果是web环境，会读取相应的initparams
2. 关闭已有的bean factory，创建新的bean factory并设置bean overriding和circular reference，通过beanDefinitionReader加载bean定义
3. 初始化bean factory,主要是一些default bean和beanPostProcessor的注册
4. 注册servletContextAwareProcessor
##依赖注入
###依赖注入方式:
- Constructor-based DI
  * constructor
  * static factory method
  * bean factory method
- Setter-based DI
  * setter method
###注入选取原则:
- 如果是必须的依赖，建议选择构造器注入
  * 构造器注入的优缺点：
  有利于创建不可变的对象(immutable bean)，可以保证bean创建成功后，所有的依赖bean都可以立即实用，免去开发人员对依赖的Bean做非空检测的麻烦，
  但是在依赖Bean太多的情况下，就会显得太臃肿。但是在这种情况下也说明了一个问题，即该bean的职责太多，
  可以考虑将职责进行拆分，以便能够创建一个更加松耦合的系统。
- 如果是可选依赖，建议选择setter方法注入
  * setter注入的优缺点：
    主要用于可选依赖的注入，可以在bean被创建成功后，进行再次的配置，配置更加的灵活，但是无法保证依赖bean在使用时一定是可用的(2.0后可以通过@Required注解进行弥补)
##自动编织
- byType
- byName
- byConstructor
##生命周期管理
- init:
  * Methods annotated with @PostConstruct
  * afterPropertiesSet() as defined by the InitializingBean callback interface
  * A custom configured init() method
- destroy:
  * Methods annotated with @PreDestroy
  * destroy() as defined by the DisposableBean callback interface
  * A custom configured destroy() method
- Lifecycle,SmartLifecycle
- common aware interface(not recommend)
  * ApplicationContextAware
  * ApplicationEventPublisherAware
  * BeanClassLoaderAware
  * BeanFactoryAware
  * BeanNameAware
  * BootstrapContextAware
  * LoadTimeWeaverAware
  * MessageSourceAware
  * NotificationPublisherAware
  * ResourceLoaderAware
  * ServletConfigAware
  * ServletContextAware
- Container extension
  * BeanPostProcessor
    * AutowiredAnnotationBeanPostProcessor
    * CommonAnnotationBeanPostProcessor
    * PersistenceAnnotationBeanPostProcessor
    * RequiredAnnotationBeanPostProcessor
  * BeanFactoryPostProcessor
    * PropertyPlaceholderConfigurer
    * PropertyOverrideConfigurer
##FactoryBean
- JndiObjectFactoryBean
- ProxyFactoryBean
- ServiceLocatorFactoryBean
- ObjectFactoryCreatingFactoryBean
##实用功能
1. 别名
   * `<alias name="fromName" alias="toName"/>`
2. 循环依赖问题
   * 可以使用setter注入来解决
3. 如何解决不同生命周期的Bean依赖，如singleton的Bean依赖prototype的Bean
   * 实现ApplicationContextAware 接口，手动的调用getBean
   * 使用method injection
   * ObjectFactoryCreatingFactoryBean
   * ServiceLocatorFactoryBean
   * `<aop:scoped-proxy/>`