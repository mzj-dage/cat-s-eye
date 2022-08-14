package com.mzj.meetingfilm.hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mzj.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mzj.meetingfilm.hall.service.HallServiceAPI;
import com.mzj.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/halls")
public class HallController {

    @Autowired
    private HallServiceAPI hallServiceAPI;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeHalls(HallsReqVO hallReqVO) throws CommonServiceException {
        hallReqVO.checkParam();

        IPage<HallsRespVO> page = hallServiceAPI.describeHalls(hallReqVO);

        Map<String, Object> halls = describePageResult(page, "halls");

        return BaseResponseVO.success(halls);
    }

    // 新增播放厅
    @RequestMapping(value = "/hall:add",method = RequestMethod.POST)
    public BaseResponseVO saveHalls(@RequestBody HallSavedReqVO hallSavedReqVO) throws CommonServiceException {
        //TODO 这里没有做数据校验
        hallSavedReqVO.checkParam();
        hallServiceAPI.saveHall(hallSavedReqVO);
        return BaseResponseVO.success();
    }


    // 获取分页对象的公共接口
    private Map<String,Object> describePageResult(IPage page,String title){
        Map<String,Object> result = Maps.newHashMap();

        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());

        result.put(title,page.getRecords());

        return result;
    }
}
