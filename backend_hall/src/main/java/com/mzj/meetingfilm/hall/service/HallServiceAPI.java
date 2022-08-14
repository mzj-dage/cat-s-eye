package com.mzj.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mzj.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;

public interface HallServiceAPI {

    // 获取全部播放厅接口
    IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException;

    // 保存影厅信息
    void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException;

}
