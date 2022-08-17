package com.mzj.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzj.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mzj.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mzj.meetingfilm.film.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author mzj
 * @since 2022-08-06
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {
    IPage<DescribeFilmsRespVO> describeFilms(Page<DescribeFilmsRespVO> page);

    DescribeFilmRespVO descrbeFilmById(@Param("filmId") String filmId);
}
