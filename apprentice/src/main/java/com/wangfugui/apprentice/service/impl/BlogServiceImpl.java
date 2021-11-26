package com.wangfugui.apprentice.service.impl;

import com.wangfugui.apprentice.dao.domain.Blog;
import com.wangfugui.apprentice.dao.mapper.BlogMapper;
import com.wangfugui.apprentice.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author MrFugui
 * @since 2021-11-25
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
