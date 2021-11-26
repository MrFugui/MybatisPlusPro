package com.wangfugui.apprentice.dao.dto;

import lombok.Data;

/**
 * @author MaSiyi
 * @version 1.0.0 2021/11/26
 * @since JDK 1.8.0
 */
@Data
public class PageParamDto<T> {
    private Integer page;
    private Integer size;
    private String asc;
    private String desc;
    /**
     * 实体类参数
     */
    private T params;
}
