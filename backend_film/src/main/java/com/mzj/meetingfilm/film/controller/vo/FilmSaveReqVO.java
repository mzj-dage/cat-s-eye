package com.mzj.meetingfilm.film.controller.vo;

import com.mzj.meetingfilm.utils.common.vo.BaserRequestVO;
import com.mzj.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 保存电影信息对象
 */
@Data
public class FilmSaveReqVO extends BaserRequestVO {
    private String filmStatus; //电影状态， 1-正在热映，2-即将上映，3-经典影片
    private String filmName;   //电影中文名称
    private String filmEnName; //电影英文名称
    private String mainImgAddress; //电影宣传图地址
    private String filmScore;  //电影评分
    private String filmScorers; //电影评分人数
    private String preSaleNum; //电影预售票房
    private String boxOffice;  //电影实时票房
    private String filmSourceId; //电影来源编号，映射source的字典表
    private String filmCatIds; //电影分类编号，映射cat的字典表，多个以#号分割
    private String areaId; //电影地区编号，映射year的字典表
    private String dateId; //电影日期编号，映射date的字典表
    private String filmTime; //电影上映日期;
    private String directorId; //电影导演编号
    private String actIds; //电影演员编号列表，多个以#号分割
    private String filmLength; //电影时长
    private String biography; //电影介绍
    private String filmImgs; //电影经典图片地址，多个以逗号分割
    private String filmTypeId; //片源类型编号，0-2D,1-3D,2-3DIMAX,4-无
    private String roleNames; //角色名称，多个以#号分割，与演员编号一一对应

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
