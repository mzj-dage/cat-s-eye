package com.mzj.meetingfilm.hall.controller.vo;

import com.mzj.meetingfilm.utils.common.vo.BaserRequestVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class HallSavedReqVO extends BaserRequestVO {
    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
