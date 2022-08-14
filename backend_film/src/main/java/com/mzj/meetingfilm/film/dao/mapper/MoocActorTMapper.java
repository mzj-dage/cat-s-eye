package com.mzj.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzj.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mzj.meetingfilm.film.dao.entity.MoocActorT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author mzj
 * @since 2022-08-06
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {
    IPage<DescribeActorsRespVO> describeActors(Page<DescribeActorsRespVO> page);
}
