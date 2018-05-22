package wang.raye.admin.service.provider;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/22.
 */
@Component
public class RoleMenuProvider {

    public String insertAll(Map map) {
        List<HashMap<String,Object>> roleMenuList = (List<HashMap<String,Object>>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO role_menu ");
        sb.append("(roleid,menuid,creator) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].roleid}, #'{'list[{0}].menuid}, #'{'list[{0}].userid})");
        for (int i = 0; i < roleMenuList.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < roleMenuList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
