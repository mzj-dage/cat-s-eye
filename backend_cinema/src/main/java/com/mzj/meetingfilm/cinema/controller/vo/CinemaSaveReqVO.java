package com.mzj.meetingfilm.cinema.controller.vo;

import com.mzj.meetingfilm.utils.common.vo.BaserRequestVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class CinemaSaveReqVO extends BaserRequestVO {
    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    @Override
    public void checkParam() throws CommonServiceException {
        if (brandId.equals(null)||areaId.equals(null)||hallTypeIds.equals(null)||cinemaName.equals(null)
        ||cinemaAddress.equals(null)||cinemaTele.equals(null)||cinemaImgAddress.equals(null)||cinemaPrice.equals(null)){
            throw new CommonServiceException(404,"该参数不能为空!");
        }
    }
}
