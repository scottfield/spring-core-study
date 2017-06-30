![](img/spring-overview.png) 

**spring modules**
========================================================
![](img/container-magic.png) 

**spring container工作原理图**
========================================================
![](img/beanFactory%20hierarchy.PNG) 

**BeanFactory类结构**
========================================================
##应用上下文初始化流程
1. 修改当前context的状态为active，将systemProperties和systemEnvironment的值加载到Environment,并验证required的property是否都已经配置
2. 创建BeanFactory，加载bean definition
3. 对bean factory进行标准的初始化,例如设置bean classloader和注册beanPostProcessor
4. 调用postProcessBeanFactory,对bean factory进行一些后处理，默认是不做任何处理
5. 调用invokeBeanFactoryPostProcessors,注册bean factory postprocessor并调用
6. 调用registerBeanPostProcessors,注册定义的bean postprocessor
7. 调用initMessageSource,初始化message sources,这里就会可以读取我们定义的message source bean，但是名称必须为messageSource
8. 调用initApplicationEventMulticaster,初始化事件发布器，会读取我们定义的名称为applicationEventMulticaster的Bean,如果没有,默认使用SimpleApplicationEventMulticaster
9. 调用onRefresh,初始化其他特殊的Bean，默认实现为空，可以在子类中进行自定义
10. 调用registerListeners,将事件监听器（实现了ApplicationListener的Bean）注册到事件发布器中，并且广播earlyApplicationEvents已有的事件(event)
11. 调用finishBeanFactoryInitialization,进行factory bean的最后初始化，主要是注册conversion service bean,embeddedValueResolvers,LoadTimeWeaverAware bean 以及其他的singleton bean，并且会将bean factory的配置进行冻结。
12. 调用finishRefresh,进行life cycle processor的注册和context refresh事件的通知，使用事件发布器发布ContextRefreshedEvent事件，将当前的应用上下文注册到LiveBeansView
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
- bean初始化顺序
  1. BeanNameAware's setBeanName
  2. BeanClassLoaderAware's setBeanClassLoader
  3. BeanFactoryAware's setBeanFactory
  4. EnvironmentAware's setEnvironment
  5. EmbeddedValueResolverAware's setEmbeddedValueResolver
  6. ResourceLoaderAware's setResourceLoader (only applicable when running in an application context)
  7. ApplicationEventPublisherAware's setApplicationEventPublisher (only applicable when running in an application context)
  8. MessageSourceAware's setMessageSource (only applicable when running in an application context)
  9. ApplicationContextAware's setApplicationContext (only applicable when running in an application context)
  10. ServletContextAware's setServletContext (only applicable when running in a web application context)
  11. postProcessBeforeInitialization methods of BeanPostProcessors
  12. InitializingBean's afterPropertiesSet
  13. a custom init-method definition
  14. postProcessAfterInitialization methods of BeanPostProcessors
- bean销毁顺序
  1. postProcessBeforeDestruction methods of DestructionAwareBeanPostProcessors
  2. DisposableBean's destroy
  3. a custom destroy-method definition
- 不同init方法调用顺序:
  * Methods annotated with @PostConstruct
  * afterPropertiesSet() as defined by the InitializingBean callback interface
  * A custom configured init() method
- 不同destroy方法调用顺序:
  * Methods annotated with @PreDestroy
  * destroy() as defined by the DisposableBean callback interface
  * A custom configured destroy() method
- 通过Lifecycle,SmartLifecycle对Bean实现生命周期管理
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