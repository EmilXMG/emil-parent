package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.CodeItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author emil
 */
public interface CodeItemMapper extends BaseMapper<CodeItem> {
    /**
     * 获取列表
     *
     * @param page
     * @return
     */
    List<CodeItem> listFull(@Param("page") PageParam page);

    /**
     * 获取代码项子级的数量
     *
     * @param codeId
     * @return
     */
    int getCodeItemCount(Integer codeId);

    /**
     * 获取当前代码项下的子项
     *
     * @param codeId
     * @return
     */
    List<CodeItem> getCodeItemByMainId(int codeId);

    /**
     * 根据代码项名获取代码子项列表
     *
     * @param codeName
     * @return
     */
    List<CodeItem> getCodeItem(String codeName);
}
