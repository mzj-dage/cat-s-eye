package com.mzj.meetingfilm.hall.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzj.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mzj.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mzj.meetingfilm.hall.dao.entity.MoocFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author mzj
 * @since 2022-08-08
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {
    IPage<HallsRespVO> describeHalls(Page<HallsReqVO> page, @Param("ew") QueryWrapper queryWrapper);
}
