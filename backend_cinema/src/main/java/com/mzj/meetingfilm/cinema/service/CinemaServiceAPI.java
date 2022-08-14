package com.mzj.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mzj.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.mzj.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;

public interface CinemaServiceAPI {
    // 保存影院的方法
    void saveCinema(CinemaSaveReqVO cinemaSaveReqVO) throws CommonServiceException;

    // 获取影院列表
    IPage<MoocCinemaT> describeCinemas(int nowPage, int pageSize) throws CommonServiceException;
}
