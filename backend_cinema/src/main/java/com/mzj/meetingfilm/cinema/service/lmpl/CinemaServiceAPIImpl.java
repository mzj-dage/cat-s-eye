package com.mzj.meetingfilm.cinema.service.lmpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzj.meetingfilm.cinema.controller.vo.CinemaSaveReqVO;
import com.mzj.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.mzj.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mzj.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.mzj.meetingfilm.cinema.service.CinemaServiceAPI;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CinemaServiceAPIImpl implements CinemaServiceAPI {
    @Resource
    private MoocCinemaTMapper moocCinemaTMapper;

    @Override
    public void saveCinema(CinemaSaveReqVO cinemaSaveReqVO) throws CommonServiceException {
        MoocCinemaT cinemaT = new MoocCinemaT();

        cinemaT.setCinemaName(cinemaSaveReqVO.getCinemaName());
        cinemaT.setCinemaPhone(cinemaSaveReqVO.getCinemaTele());
        cinemaT.setBrandId(ToolUtils.str2Int(cinemaSaveReqVO.getBrandId()));
        cinemaT.setAreaId(ToolUtils.str2Int(cinemaSaveReqVO.getAreaId()));
        cinemaT.setHallIds(cinemaSaveReqVO.getHallTypeIds());
        cinemaT.setImgAddress(cinemaSaveReqVO.getCinemaImgAddress());
        cinemaT.setCinemaAddress(cinemaSaveReqVO.getCinemaAddress());
        cinemaT.setMinimumPrice(ToolUtils.str2Int(cinemaSaveReqVO.getCinemaPrice()));

        moocCinemaTMapper.insert(cinemaT);
    }

    @Override
    public IPage<MoocCinemaT> describeCinemas(int nowPage, int pageSize) throws CommonServiceException {
        Page<MoocCinemaT> page = new Page<>(nowPage, pageSize);
        IPage<MoocCinemaT> moocCinemaTIPage = moocCinemaTMapper.selectPage(page, null);
        return moocCinemaTIPage;
    }
}
