package ${package.Service};

import com.baomidou.mybatisplus.extension.service.IService;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.${package.ModuleName}.entity.${entity};

/**
* ${table.comment!}

* @author ${author}
* @since ${date}
* @version v1.0
*/
public interface I${entity}Service extends IService<${entity}> {
/**
*${table.comment!}列表
*
* @param page
* @return
*/
PageResult<${entity}> listFull(PageParam page);
}
