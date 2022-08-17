package com.mzj.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mzj.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mzj.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mzj.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mzj.meetingfilm.film.controller.vo.FilmSaveReqVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;

public interface FilmServiceAPI {

    // 获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize) throws CommonServiceException;

    // 获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException;

    // 根据主键获取电影信息
    DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException;

    // 保存电影信息
    void saveFilm(FilmSaveReqVO filmSavedReqVO) throws CommonServiceException;

}

