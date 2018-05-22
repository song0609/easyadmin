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
public class UserRoleProvider {

    public String insertAll(Map map) {
        List<HashMap<String,Object>> userRoleList = (List<HashMap<String,Object>>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO user_role ");
        sb.append("(userid,roleid,creator) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].userid}, #'{'list[{0}].roleid}, #'{'list[{0}].creator})");
        for (int i = 0; i < userRoleList.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < userRoleList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
