1Type is missing a javadoc commentClass 缺少类型说明

2“{” should be on the previous line“{” 应该位于前一行。解决方法：把“{”放到上一行去

3Methos is missing a javadoc comment 方法前面缺少javadoc注释。解决方法：添加javadoc注释 类似这样：

/**

* set default mock parameter.（方法说明）

* @param additionalParameters parameter additional(参数名称)

* @return data manager(返回值说明)

* @throws Exception if has error(异常说明)

*/

4 Expected @throws tag for “Exception”在注释中希望有@throws的说明

解决方法：在方法前得注释中添加这样一行：* @throws Exception if has error(异常说明)

5“.” Is preceeded with whitespace “.” 前面不能有空格。解决方法：把“（”前面的空格去掉

6“.” Is followed by whitespace“.” 后面不能有空格。解决方法：把“）”后面的空格去掉

7“=” is not preceeded with whitespace“=” 前面缺少空格。解决方法：在“=”前面加个空格

8“=” is not followed with whitespace“=” 后面缺少空格。解决方法：在“=”后面加个空格

9“}” should be on the same line“}” 应该与下条语句位于同一行。解决方法：把“}”放到下一行的前面

10Unused @param tag for “unused”没有参数“unused”，不需注释

解决方法：“* @param unused parameter additional(参数名称)” 把这行unused参数的注释去掉“

11 Variable “CA” missing javadoc变量“CA”缺少javadoc注释

解决方法：在“CA“变量前添加javadoc注释：/** CA. */（注意：一定记得加上“.”）

12 Line longer than 80characters行长度超过80  。解决方法：把它分成多行写。必要时候，可以ctrl+shift+f

13 Line contains a tab character行含有”tab” 字符。快速解决方法：可以使用editplus中的format功能，把tab字符转化为空格，然后保存Editplus英文版安装文件在我机子上有。需要的可以来拷贝。注册Editplus，点击安装文件中注册的文件

14 Redundant “Public” modifier冗余的“public” modifier   。解决方法：冗余的“public”

15 Final modifier out of order with the JSL suggestion Final modifier的顺序错误

16 Avoid using the “.*” form of importImport格式避免使用“.*”

17 Redundant import from the same package从同一个包中Import内容

18 Unused import-java.util.listImport进来的java.util.list没有被使用。解决方法：去掉导入的多余的类

19 Duplicate import to line 13重复Import同一个内容    解决方法：去掉导入的多余的类

20 Import from illegal package从非法包中 Import内容

21 “while” construct must use “{}”  “while” 语句缺少“{}”

22 Variable “sTest1” must be private and have accessor method变量“sTest1”应该是private的，并且有调用它的方法

23 Variable “ABC” must match pattern “^[a-z][a-zA-Z0-9]*$”变量“ABC”不符合命名规则“^[a-z][a-zA-Z0-9]*$”解决方法：把这个命名改成符合规则的命名 “aBC”

24 “(” is followed by whitespace“(” 后面不能有空格 25“)”is proceeded by whitespace“)” 前面不能有空格

解决方法：把前面或者后面的空格去掉

25 First sentence should end with a period.解决方法：你的注释的第一行文字结束应该加上一个"."。

26 Redundant throws: 'NameNotFoundException' is subclass of 'NamingException'. 'NameNotFoundException '是'NamingException'的子类重复抛出异常。

解决方法：如果抛出两个异常，一个异常类是另一个的子类，那么只需要写父类

去掉NameNotFoundException异常，对应的javadoc注释异常注释说明也需要去掉

27 Parameter docType should be final. 参数docType应该为final类型  解决方法：在参数docType前面加个final

28 Line has trailing spaces. 多余的空行  解决方法：去掉这行空行

29 Must have at least one statement.  至少一个声明

解决方法：} catch (NumberFormatException nfe) {

LOG.error("Auto Renews the agreement failed", nfe);//异常捕捉里面不能为空，在异常里面加一句话。如打印等等

30 '>' is not followed by whitespace.并且又有 '(' is preceded with whitespace.

定义集合和枚举的时候的时候，最后一个“>”后面要有空格，“（”前面不容许有空格。解决方法：去掉泛型

31 Got an exception - java.lang.RuntimeException: Unable to get class information for @throws tag 'SystemException'.原因：不合理的throws。

解决方法：要确保某些类型，如某些类、接口不被throws。把声明的异常去掉。在实现类中抛出异常

网上参考解决方法：1、这是CheckStyle报的错。通常需要Refreh, clean/build这个Project. 如果不行，可以尝试clean all projects, restart Eclipse.

2、因为编译好的类没有在checkstyle的classpath中.所以, 只要将编译好的class配置到在<checkstyle/>的classpath中就没有这个问题了.另外, 还发现checkstyle的line length好像也有点问题, 明明没有超过120个字符, 却还是报错.无奈, 我把Eclipse中java > code style > formatter中的Maximum line with改成了100, 然后format一下, 基本就没有问题了

32 File does not end with a newline.解决方法：删掉报错的类，新建一个同名的类，把代码全部复制过去

33 Utility classes should not have a public or default constructor. 接口中的内部类中不应该有公共的或者默认的构造方法

解决方法：在内部类中，定义一个私有的构造方法，然后内部类声明为final类型。如果前面有static，那么final还必须放在static之后

34 Variable 'functionCode' must be private and have accessor methods.变量要改成private然后提供访问的方法

解决方法：给这些变量的修饰符改成private，然后提供set，get方法，并加上对应的方法javadoc注释、参数注释。并在返回值和参数类型前添加final。并把调用了这个变量的地方改成通过方法访问

35   'X' hides a field.

public class Foo
{
    private int bar;

    public Foo(int bar)
    {
        this.bar = bar;
    }

    public final int getBar()
    {
        return bar;
    }
}

全局private int bar;和局部public Foo(int bar)的bar变量名字重复。
解决方法：把方法里面的参数名称改变下就可以了public Foo(int newBar)
    {
        this.bar = newBar;
    }。

36 Got an exception - Unexpected character 0xfffd in identifier

这是因为CheckStyle不能识别制定的编码格式。

网上参考解决方法：

1、Eclipse中可以配置，在Other-->checker中可以指定

2、可以修改checkstyle配置文件：

<module name="Checker">

<property name="severity" value="warning"/>

<property name="charset" value="UTF-8"/>

<module name="TreeWalker">

如果是UTF-8的话，就添加加粗斜体的那条语句，就可以了。

37  Got an exception - java.lang.RuntimeException: Unable to get class information for @throws tag *whatever*.
网上参考解决方法：选中CheckSytle的JavaDoc --> Method JavaDoc --> logLoadErrors。如果是CheckStyle自己加载时出错的，打个Log就可以了，不要整出Errors吓人。
还有一处也可能包出同样的错误。Coding Problems --> Redundant Throws --> logLoadErrors选中即可

38 Expected @param tag for 'dataManager'. 缺少dataManager参数的注释   解决方法：在注释中添加@param dataManager DataManager

网上一些其他错误的解答：
1. Parameter X should be final.
public class Foo
{
    private int bar;

    public Foo(int bar)
    {
        this.bar = bar;
    }

    public final int getBar()
    {
        return bar;
    }
}

解释：public Foo(int bar)的局部变量，被认为是不可改变的，检查需要加上final关键字定义public Foo(final int bar)此错误，可以忽略不检查。

2. Redundant 'X' modifier.

public interface CacheHRTreeService extends Manager {

/**
  * Organization Tree
  * @param orgDto
  * @return
  * @throws Exception
  */
public void setOrganization(OrganizationDTO orgDto) throws Exception;

/**
  * Organization Tree
  * @return
  * @throws Exception
  */
public OrganizationDTO getOrganization() throws Exception;
......
}

解释：多余的字段。public OrganizationDTO getOrganization() throws Exception;此时public为多余的字段，因为interface定义的时候，就是public的。

需要检查。

3. - Class X should be declared as final.

解释：对于单例设计模式，要求返回唯一的类对象。但是HRFactory和ContextFactory为优化的两个类，不需求检查。
其他的单例类，依然需要进行检查。

4. - Method 'addChildrenId' is not designed for extension - needs to be
  abstract, final or empty.

解释：通过父类继承的，此类有点特殊可以忽略此类。

5. Variable 'id' must be private and have accessor methods.解释：BaseHRDTO类，为父类，属性给子类继承，比较特殊。但是其他的类，声名需要加上范围'private'关键字。需要检查。

6. -Array brackets at illegal position.解释：代码写法，习惯不一样。需要检查，仅仅提示