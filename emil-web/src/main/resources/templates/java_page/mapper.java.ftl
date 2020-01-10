package ${package.Mapper};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.ew.common.PageParam;
import com.wf.ew.${package.ModuleName}.entity.${entity};
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* ${table.comment!}

* @author ${author}
* @since ${date}
* @version v1.0
*/
public interface ${entity}Mapper extends BaseMapper<${entity}> {

/**
*${table.comment!}列表
*
* @param page
* @return
*/
List<${entity}> listFull(@Param("page") PageParam page);
}