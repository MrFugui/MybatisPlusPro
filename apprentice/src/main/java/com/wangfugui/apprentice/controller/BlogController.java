package com.wangfugui.apprentice.controller;


import com.wangfugui.apprentice.dao.domain.Blog;
import com.wangfugui.apprentice.service.IBlogService;
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
public class BlogController extends BaseController<IBlogService, Blog>{



}

