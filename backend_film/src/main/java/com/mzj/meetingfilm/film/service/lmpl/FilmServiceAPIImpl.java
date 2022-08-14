package com.mzj.meetingfilm.film.service.lmpl;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzj.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mzj.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.mzj.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mzj.meetingfilm.film.controller.vo.FilmSaveReqVO;
import com.mzj.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.mzj.meetingfilm.film.dao.entity.MoocFilmInfoT;
import com.mzj.meetingfilm.film.dao.entity.MoocFilmT;
import com.mzj.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.mzj.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.mzj.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.mzj.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.mzj.meetingfilm.film.service.FilmServiceAPI;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import com.mzj.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 影片模块实现层
 */
@Service
public class FilmServiceAPIImpl implements FilmServiceAPI {
    @Resource
    private MoocActorTMapper actorTMapper;

    @Resource
    private MoocFilmTMapper filmTMapper;

    @Resource
    private MoocFilmInfoTMapper filmInfoTMapper;

    @Resource
    private MoocFilmActorTMapper filmActorTMapper;


    /**
     * @Description: 演员查询列表
     * @Param: [nowPage, pageSize]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO>
     * @Author: jiangzh
     */
    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException {
        // 查询演员列表
        return actorTMapper.describeActors(new Page<>(nowPage,pageSize));
    }

    /**
     * @Description: 影片列表查询
     * @Param: [nowPage, pageSize]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO>
     * @Author: jiangzh
     */
    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException {
        return filmTMapper.describeFilms(new Page<>(nowPage,pageSize));
    }


    /**
     * @Description: 根据主键获取电影详情
     * @Param: [filmId]
     * @return: com.mooc.meetingfilm.film.controller.vo.DescribeFilmRespVO
     * @Author: jiangzh
     */
    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        return filmTMapper.descrbeFilmById(filmId);
    }


    /**
     * @Description: 保存电影信息
     * @Param: [filmSavedReqVO]
     * @return: void
     * @Author: jiangzh
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFilm(FilmSaveReqVO reqVO) throws CommonServiceException {
        try{
            // 保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));

            filmTMapper.insert(film);
            // 保存电影子表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();

            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());


            filmInfoTMapper.insert(filmInfo);

            String[] actorId = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if(actorId.length != roleNames.length){
                throw new CommonServiceException(500, "演员和角色名数量不匹配");
            }

            for(int i=0;i<actorId.length;i++){
                // 保存演员映射表
                MoocFilmActorT filmActor = new MoocFilmActorT();

                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);


                filmActorTMapper.insert(filmActor);
            }
        }catch (Exception e){
            throw new CommonServiceException(500, e.getMessage());
        }
    }


}
