# MybatisPlusPro

#### 介绍
在MybatisPlus的基础上增加更多的模板功能，站在巨人的肩膀上。MybatisPlusPro是一个 MybatisPlus的增强工具，在 MybatisPlus的基础上只做增强不做改变，为简化开发、提高效率而生。

#### 软件架构
在MybatisPlus的基础上进行功能的抽取，利用springboot进行开发

#### 使用说明
富贵同学在用`MybatisPlus`作为开发的时候，虽然好用，但是大多数都在对dao层面的增删改查，所以打算自己抽取一套在controller层的功能出来，先介绍一下，“`MybatisPlusPro`” ：只要继承一个`BaseController`类，就可以拥有增删改查，查询列表，分页查询，排序，带参数查询，统计数量。话不多说，直接开始吧![在这里插入图片描述](https://img-blog.csdnimg.cn/39bc4bb2c09d4883b405f3e5c0ee74c1.jpg#pic_center)

第一步，引入MybatisPlus的jar包

```java
		  <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.2</version>
        </dependency>
```
第二步，编写util类

```java
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Apprentice系统Util
 *
 * @author MaSiyi
 * @version 1.0.0 2021/11/26
 * @since JDK 1.8.0
 */
public class ApprenticeUtil {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 驼峰转下划线
     *
     * @Param: [str]
     * @return: java.lang.String
     * @Author: MaSiyi
     * @Date: 2021/11/26
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @Param: [str]
     * @return: java.lang.String
     * @Author: MaSiyi
     * @Date: 2021/11/26
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 获取QueryWrapper
     *
     * @Param: [entity]
     * @return: com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<E>
     * @Author: MaSiyi
     * @Date: 2021/11/26
     */
    public static <E> QueryWrapper<E> getQueryWrapper(E entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        QueryWrapper<E> eQueryWrapper = new QueryWrapper<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            //忽略final字段
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            try {
                Object obj = field.get(entity);
                if (!ObjectUtils.isEmpty(obj)) {
                    String name = ApprenticeUtil.humpToLine(field.getName());
                    eQueryWrapper.eq(name, obj);
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return eQueryWrapper;

    }

    /** 反射获取字段值
     * @Param: [entity, value] value 值为 "id" "name" 等
     * @return: java.lang.Object
     * @Author: MaSiyi
     * @Date: 2021/11/26
     */
    public static <E> Object getValueForClass(E entity,String value) {

        Field id = null;
        PropertyDescriptor pd = null;
        try {
            id = entity.getClass().getDeclaredField(value);
            pd = new PropertyDescriptor(id.getName(), entity.getClass());
        } catch (NoSuchFieldException | IntrospectionException e) {
            e.printStackTrace();
        }
        //获取get方法
        Method getMethod = Objects.requireNonNull(pd).getReadMethod();
        return ReflectionUtils.invokeMethod(getMethod, entity);
    }
}

```
第三步，我们编写BaseController类

```java

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangfugui.apprentice.common.util.ApprenticeUtil;
import com.wangfugui.apprentice.common.util.ResponseUtils;
import com.wangfugui.apprentice.dao.dto.PageParamDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/** 核心公共controller类
 * @Param:
 * @return:
 * @Author: MaSiyi
 * @Date: 2021/11/26
 */
public class BaseController<S extends IService<E>, E> {

    @Autowired
    protected S baseService;

    @ApiOperation("增")
    @PostMapping("/insert")
    public ResponseUtils insert(@RequestBody E entity) {
        baseService.save(entity);
        return ResponseUtils.success("添加成功");
    }

    @ApiOperation("删")
    @PostMapping("/deleteById")
    public ResponseUtils delete(@RequestBody List<Integer> ids) {

        baseService.removeByIds(ids);
        return ResponseUtils.success("添加成功");
    }

    @ApiOperation("改")
    @PostMapping("/updateById")
    public ResponseUtils updateById(@RequestBody E entity) {
        baseService.updateById(entity);
        return ResponseUtils.success("添加成功");
    }

    @ApiOperation("查")
    @GetMapping("/getById")
    public ResponseUtils getById(@RequestParam Integer id) {

        return ResponseUtils.success(baseService.getById(id));
    }

    @ApiOperation("存")
    @PostMapping("/save")
    public ResponseUtils save(@RequestBody E entity) {
        baseService.saveOrUpdate(entity);
        return ResponseUtils.success("添加成功");
    }

    @ApiOperation("list查")
    @PostMapping("/list")
    public ResponseUtils list(@RequestBody E entity) {
        QueryWrapper<E> queryWrapper = ApprenticeUtil.getQueryWrapper(entity);
        List<E> list = baseService.list(queryWrapper);
        return ResponseUtils.success(list);
    }

    @ApiOperation("page查")
    @PostMapping("/page")
    public ResponseUtils page(@RequestBody PageParamDto<E> pageParamDto) {
        //限制条件
        if (pageParamDto.getPage() < 1) {
            pageParamDto.setPage(1);
        }

        if (pageParamDto.getSize() > 100) {
            pageParamDto.setSize(100);
        }
        Page<E> page = new Page<>(pageParamDto.getPage(), pageParamDto.getSize());
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        //升序
        String asc = pageParamDto.getAsc();
        if (!StrUtil.isEmpty(asc) && !"null".equals(asc)) {
            String[] split = asc.split(",");
            queryWrapper.orderByAsc(split);
        }
        //降序
        String desc = pageParamDto.getDesc();
        if (!StrUtil.isEmpty(desc) && !"null".equals(desc)) {
            String[] split = desc.split(",");
            queryWrapper.orderByDesc(split);
        }
        Page<E> ePage = baseService.page(page, queryWrapper);
        return ResponseUtils.success(ePage);
    }

    @ApiOperation("获取数量")
    @PostMapping("/count")
    public ResponseUtils count(@RequestBody E entity) {
        QueryWrapper<E> queryWrapper = ApprenticeUtil.getQueryWrapper(entity);
        long count = baseService.count(queryWrapper);
        return ResponseUtils.success(count);
    }


}

```
第四步，由于mybatisplus默认是不支持分页的，我们需要配置一下使他支持

```java
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {


    /** 设置分页插件
     * @Param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @Author: MaSiyi
     * @Date: 2021/11/26
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    
}
```
第五步，我们在自己的controller类中继承BaseController类

```java
import com.wangfugui.apprentice.dao.domain.Dynamic;
import com.wangfugui.apprentice.service.IDynamicService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态表 前端控制器
 * </p>
 *
 * @author MrFugui
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/apprentice/dynamic")
@Api("动态管理")
public class DynamicController extends BaseController<IDynamicService, Dynamic>{

}
```

```java

import com.wangfugui.apprentice.dao.domain.Blog;
import com.wangfugui.apprentice.service.IBlogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 博客表 前端控制器
 * </p>
 *
 * @author MrFugui
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/apprentice/blog")
@Api(tags = "博客管理")
public class BlogController extends BaseController<IBlogService, Blog>{



}

```
这样就能使用啦！
![在这里插入图片描述](https://img-blog.csdnimg.cn/d826e80a840b4a0a81e5731b1b910237.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5o6J5aS05Y-R55qE546L5a-M6LS1,size_20,color_FFFFFF,t_70,g_se,x_16)


#### 参与贡献
仓库地址：
[MybatisPlusPro](https://gitee.com/WangFuGui-Ma/mybatis-plus-pro)
如果对你有用的话，记得关注一下富贵同学，爱你们
![在这里插入图片描述](https://img-blog.csdnimg.cn/134f9d88e8414b36a113ff39cff233bf.png#pic_center)
#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
