package com.mzj.meetingfilm.user.dao.mapper;

import com.mzj.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author mzj
 * @since 2022-08-03
 */
public interface MoocBackendUserTMapper extends BaseMapper<MoocBackendUserT> {
    List<MoocBackendUserT> selectPwtByUsername(@Param("username") String username);
}
