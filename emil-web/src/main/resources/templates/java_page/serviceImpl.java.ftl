package ${package.ServiceImpl};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.ew.common.PageParam;
import com.wf.ew.common.PageResult;
import com.wf.ew.${package.ModuleName}.mapper.${table.mapperName};
import com.wf.ew.${package.ModuleName}.entity.${entity};
import com.wf.ew.${package.ModuleName}.service.${table.serviceName};
import org.springframework.stereotype.Service;

import java.util.List;

/**
* ${table.comment!}
*
* @author ${author}
* @since ${date}
* @version v1.0
*/
@Service
public class ${entity}ServiceImpl extends ServiceImpl <${table.mapperName}, ${entity}> implements ${table.serviceName}   {

/**
*${table.comment!}列表
*
* @param page
* @return
*/
@Override
public PageResult<${entity}> listFull(PageParam page) {
List<${entity}> ${table.entityPath}s = baseMapper.listFull(page);
return new PageResult<>(${table.entityPath}s, page.getTotal());
}
}
