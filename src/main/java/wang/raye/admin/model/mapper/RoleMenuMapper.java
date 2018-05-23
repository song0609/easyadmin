package wang.raye.admin.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.common.Mapper;
import wang.raye.admin.model.RoleMenu;
import wang.raye.admin.service.provider.RoleMenuProvider;

import java.util.HashMap;
import java.util.List;

public interface RoleMenuMapper extends Mapper<RoleMenu> {

    @Delete({
            "delete from role_menu",
            "where roleid = #{roleid}"
    })
    int deleteByRoleid(String roleid);


    @InsertProvider(type = RoleMenuProvider.class, method = "insertAll")
    void roleMenuUpdate(List<HashMap<String,Object>> roleMenuList);


    @Delete({
            "delete from role_menu",
            "where menuid = #{menuid}"
    })
    int deleteByMenuid(int menuid);
}