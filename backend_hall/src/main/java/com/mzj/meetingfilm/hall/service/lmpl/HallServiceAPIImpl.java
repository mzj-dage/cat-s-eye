package com.mzj.meetingfilm.hall.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzj.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mzj.meetingfilm.hall.apis.FilmFeignApi;
import com.mzj.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mzj.meetingfilm.hall.dao.entity.MoocFieldT;
import com.mzj.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mzj.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mzj.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mzj.meetingfilm.hall.service.HallServiceAPI;
import com.mzj.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class HallServiceAPIImpl implements HallServiceAPI {
    @Resource
    private MoocFieldTMapper fieldTMapper;

    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private FilmFeignApi filmFeignApi;

    /*@Autowired
    private LoadBalancerClient eurekaClient;*/

    /**
     * @Description: 查询影厅列表
     * @Param: [hallsReqVO]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.mooc.meetingfilm.hall.controller.vo.HallsRespVO>
     * @Author: jiangzh
     */
    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        Page<HallsReqVO> page = new Page<>(hallsReqVO.getNowPage(),hallsReqVO.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        if(ToolUtils.strIsNotNull(hallsReqVO.getCinemaId())){
            queryWrapper.eq("cinema_id", hallsReqVO.getCinemaId());
        }

        IPage<HallsRespVO> result = fieldTMapper.describeHalls(page, queryWrapper);

        return result;
    }

    /**
     * @Description: 保存影厅信息
     * @Param: [hallSavedReqVO]
     * @return: void
     * @Author: jiangzh
     */
    @Override
    public void saveHall(HallSavedReqVO reqVO) throws CommonServiceException {
        // 播放厅的列表数据
        MoocFieldT field = new MoocFieldT();

        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));

        fieldTMapper.insert(field);
        // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
        MoocHallFilmInfoT hallFilmInfo = describeFilmInfo(reqVO.getFilmId());

        hallFilmInfoTMapper.insert(hallFilmInfo);
    }

    // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException{
        // 解析返回值
        BaseResponseVO<DescribeFilmRespVO> responseVO = filmFeignApi.describeFilmById(filmId);
        DescribeFilmRespVO filmResult = responseVO.getData();
        if (filmResult.equals(null) || ToolUtils.strIsNull(filmResult.getFilmId())){
            throw new CommonServiceException(404,"invoke film service result data is null，filmId="+filmId); // 标注一个filmId可以快速判断问题所在，可能是缓存中数据丢失或者数据库中数据丢失
        }
        // 组织参数
        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();

//        "filmId":"1",
//        "filmName":"我不是药神",
//        "filmLength":"132",
//        "filmCats":"喜剧,剧情",
//        "actors":"程勇,曹斌,吕受益,刘思慧",
//        "imgAddress":"films/238e2dc36beae55a71cabfc14069fe78236351.jpg",

        hallFilmInfo.setFilmId(ToolUtils.str2Int(filmResult.getFilmId()));
        hallFilmInfo.setFilmName(filmResult.getFilmName());
        hallFilmInfo.setFilmLength(filmResult.getFilmLength());
        hallFilmInfo.setFilmCats(filmResult.getFilmCats());
        hallFilmInfo.setActors(filmResult.getActors());
        hallFilmInfo.setImgAddress(filmResult.getImgAddress());

        return hallFilmInfo;
    }


}
