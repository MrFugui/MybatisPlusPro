package com.wangfugui.apprentice.service.impl;

import com.wangfugui.apprentice.dao.domain.UserStatistics;
import com.wangfugui.apprentice.dao.mapper.UserStatisticsMapper;
import com.wangfugui.apprentice.service.IUserStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户统计表 服务实现类
 * </p>
 *
 * @author MrFugui
 * @since 2021-11-25
 */
@Service
public class UserStatisticsServiceImpl extends ServiceImpl<UserStatisticsMapper, UserStatistics> implements IUserStatisticsService {

}
