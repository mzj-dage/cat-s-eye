package com.mzj.meetingfilm.apis.film.vo;

import lombok.Data;

/**
 * 通过主键获取影片信息对象
 */
@Data
public class DescribeFilmRespVO {
    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;
}
