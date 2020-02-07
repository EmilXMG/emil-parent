package com.wf.ew.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.system.entity.CodeItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeItemMapper extends BaseMapper<CodeItem> {
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
}
