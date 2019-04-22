
## 编程规约 ##
1、POJO中的任何布尔类型的变量不能加is，以防部分框架解析引起序列化问题。

2、所有相同类型的包装类型对象间的值比较要用equals。比如Integer对象在-128到127之间赋值时，Integer对象是IntegerCache.cache中产生，会复用已有对象，此时用==是相等的；但超出该范围时会在堆中产生新的对象，并不会复用已有对象，==判定false，equals判定true。

3、基本类型与包装类型的使用：所有POJO属性必须使用包装类型；rpc调用的入参与返回必须使用包装类型；局部变量推荐使用基本类型；

4、定义DO、DTO、VO等POJO时，不能附带任何属性默认值。

5、序列化类增加新属性时不能修改serialVersionUID，避免反序列化失败；如果完全不兼容升级，避免反序列化混乱，则修改serialVersionUID。serialVersionUID不一致会产生序列化运行时异常。

6、对外发布的对象不能包含枚举类型属性，api返回类型不能为枚举类型。

7、POJO必须实现toString()方法，可以用工具生成，也可以用org.apache.commons.lang.builder.ToStringBuilder.reflectionToString()方法。

8、慎用Object的clone方法进行对象拷贝。对象的clone默认是浅拷贝，若要深拷贝则需要重写clone实现属性对象的拷贝。

9、ArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException异常：java.util.RandomAccessSubList cannot be cast to java.util.ArrayList ;
说明： subList 返回的是 ArrayList 的内部类 SubList，并不是 ArrayList ，而是 ArrayList 的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。在subList场景中，高度注意对父集合元素个数的修改，会导致子列表的遍历、增加、删除均产生ConcurrentModificationException 异常。

10、使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出UnsupportedOperationException异常。
说明：asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
String[] str = new String[] { "a", "b" };
List list = Arrays.asList(str);
第一种情况：list.add("c"); 运行时异常。
第二种情况：str[0]= "gujin"; 那么list.get(0)也会随之修改。

11、在JDK7版本以上，Comparator要满足如下三个条件，不然Arrays.sort，Collections.sort会报IllegalArgumentException异常。
说明：
 1） x，y的比较结果和y，x的比较结果相反。
 2） x>y，y>z，则x>z。
 3） x=y，则x，z比较结果和y，z比较结果相同。
总言之，对于Comparator的compare方法中要实现对于相等情形的处理，否则对于JDK1.8及之后的版本，Collections.sort()会抛错，因为其依赖的Arrays.sort()方法不再像1.7之前使用MergeSort（归并排序）,而是使用归并排序与插入排序结合而成的SimSort，simsort对于相等条件下非0时会违反比较约束。

12、不能使用Executors创建线程池，应使用ThreadPoolExecutor替代。使用Executors创建线程池的弊端：
	FixedThreadPoll和SingleThreadPool：任务队列最大值为Interger.MAX_VALUE，可能会堆积大量的请求，从而发生OOM。
	CachedThreadPool和ScheduledThreadPool：可创建线程最大数量为Integer.MAX_VALUE，可能会创建大量的线程，从而引发OOM。

13、不低于JDK1.8的程序中，可以Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormatter，官方的解释是simple beautiful strong immutable thread-safe

14、必须回收自定义的ThreadLocal变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄露等问题。尽量在代理中使用try-finally块进行回收

15、高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁，就不要用类锁。

16、对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁。

*17、并发修改同一记录时，避免更新丢失，要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用version作为更新依据。
说明：如果每次访问冲突概率小于20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得小于3次。
正例：集团很多业务使用TairManager方法：incr(namespace, lockKey, 1, 0, expireTime); 判断返回步长是否为1，实现分布式锁。

18、资金相关的金融敏感信息，使用悲观锁策略。
说明：乐观锁在获得锁的同时已经完成了更新操作，校验逻辑容易出现漏洞，另外，乐观锁对冲突的解决策略有较复杂的要求，处理不当容易造成系统压力或数据异常，所以资金相关的金融敏感信息不建议使用乐观锁更新。

19、使用CountDownLatch进行异步转同步操作，每个线程退出前必须调用countDown方法，线程执行代码注意catch异常，确保countDown方法可以执行，避免主线程无法执行至await方法，直到超时才返回结果。
说明：注意，子线程抛出异常堆栈，不能在主线程try-catch到。

20、避免Random实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一seed 导致的性能下降。
说明：Random实例包括java.util.Random 的实例或者 Math.random()的方式。
正例：在JDK7之后，可以直接使用API ThreadLocalRandom；而在JDK7前，需要编码保证每个线程持有一个单独的Random实例。

21、双重检查锁实现的单例模式可能会产生jvm重排问题导致破坏单例，一种简单的解决方式是将单例成员声明为volatile。

22、volatile解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。如果想取回count++数据，使用如下类实现：AtomicInteger count = new AtomicInteger(); count.addAndGet(1)。

23、尽量少用else，即使一定要用，也不能超过3层。

24、除常用方法（如getXxx/isXxx）等外，不要在条件判断中执行复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量，以提高可读性。

25、接口入参保护，这种场景常见的是用于做批量操作的接口，限制操作数量。

26、方法中需要进行参数校验的场景：
 1） 调用频次低的方法。
 2） 执行时间开销很大的方法，参数校验时间几乎可以忽略不计，但如果因为参数错误导致中间执行回退，或者错误，那得不偿失。
 3） 需要极高稳定性和可用性的方法。
 4） 对外提供的开放接口，不管是HSF/API/HTTP接口。
 5） 敏感权限入口。
方法中不需要参数校验的场景：
 1） 极有可能被循环调用的方法，不建议对参数进行校验。但在方法说明里必须注明外部参数检查。
 2） 底层的方法调用频度都比较高，一般不校验。毕竟是像纯净水过滤的最后一道，参数错误不太可能到底层才会暴露问题。一般DAO层与Service层都在同一个应用中，部署在同一台服务器中，所以DAO的参数校验，可以省略。
 3） 被声明成private只会被自己代码所调用的方法，如果能够确定调用方法的代码传入参数已经做过检查或者肯定不会有问题，此时可以不校验参数。

27、类、类属性、类方法的注释必须使用javadoc规范，使用/**内容*/格式，不得使用//xxx方式。
所有的抽象方法（包括接口中的方法）必须要用javadoc注释、除了返回值、参数、异常说明外，还必须指出该方法做什么事情，实现什么功能。
方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/* */注释，注意与代码对齐。
所有的枚举类型字段必须要有注释，说明每个数据项的用途。

注释示例：
点标识多项列表
<ul>
	<li> </li>
</ul>

换行对齐间距
<dl>
	<dt> </dt>  
	<dd></dd>
</dl>


色彩注释示例：

/**
 * <font color="black">为字符串字节长度校验提供annotation注解。</font>
 *
 * <p>
 *     <b><font color="black">描述:</font></b>
 *     <ul>
 *          <li>此类用来标注需要进行字节长度校验的字段。</li>
 *     </ul>
 *     <b><font color="black">注意事项:</font></b>
 *     <ul>
 *          <li>当所修饰属性值为Null时，不校验；</li>
 *          <li>只用在bean的String属性上。</li>
 *          <li>min为最小值, 比如mix=1 . 那么被校验字段要 >=1；<br>
 *              max为最大值, 比如max=7 . 那么被校验字段要 <=7。<br>
 *     </ul>
 *     <b><font color="black">使用说明:</font></b>
 *     <table border="1">
 *         <tr>
 *             <th>描  述</th>
 *             <th>用  法</th>
 *         </tr>
 *         <tr>
 *             <td>所修饰String对象长度大于等于10</td>
 *             <td>@StringLength(<font color="red">min = 10</font>)</td>
 *         </tr>
 *         <tr>
 *             <td>所修饰String对象长度大于等于10且小于等于100</td>
 *             <td>@StringLength(<font color="red">min = 10, max = 100</font>)</td>
 *         </tr>
 *     </table>
 * </p>
 *
 */

28、在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。说明：不要在方法体内定义：Pattern pattern = Pattern.compile(规则);



## 异常与日志 ##
1、异常不要用来做流程控制，条件控制，因为异常的处理效率比条件分支低。

2、对大段代码进行try-catch，是不负责任的表现。catch时请分清稳定代码和非稳定代码，稳定代码指的是无论如何不会出错的代码。对于非稳定代码的catch尽可能进行区分异常类型，再做对应的异常处理。

3、事务场景中，抛出异常被catch后，如果需要回滚，一定要手动回滚事务。

4、使用日志的Facade，如slf4j、commons-logging、toolkit-common-logging，避免直接使用日志实现类库，如logback、log4j。

