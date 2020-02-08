package com.wf.ew.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.system.entity.CodeItem;

import java.util.List;


public interface CodeItemService extends IService<CodeItem> {

    PageResult<CodeItem> listFull(PageParam page);

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
     * @param codeName
     * @return
     */
    List<CodeItem> getCodeItem(String codeName);
}
