package wang.raye.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.raye.admin.model.AdminUser;
import wang.raye.admin.model.Role;
import wang.raye.admin.model.WebResult;
import wang.raye.admin.model.mapper.AdminUserMapper;
import wang.raye.admin.model.mapper.UserRoleMapper;
import wang.raye.admin.service.AdminUserService;
import wang.raye.admin.utils.MD5Util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 系统用户相关业务接口实现类
 * Created by Raye on 2017/3/21.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<AdminUser> select(int page, int pageSize, String query) {
        return adminUserMapper.selectByQuery((page - 1) * pageSize,pageSize,"%"+query+"%");
    }

    @Override
    public int selectCount(String query) {
        return adminUserMapper.selectCountByName("%"+query+"%");
    }

    @Override
    public AdminUser login(String name, String pwd) {
        AdminUser temp = new AdminUser();
        temp.setName(name);
        temp.setPsw(MD5Util.MD5(pwd));
        return adminUserMapper.selectOne(temp);
    }

    @Override
    public AdminUser selectById(int id) {
        return adminUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insert(AdminUser user) {
        user.setPsw(MD5Util.MD5(user.getPsw()));
        return adminUserMapper.insertSelective(user) > 0;
    }

    @Override
    public boolean update(AdminUser user) {
        user.setPsw(null);
        return adminUserMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        adminUserMapper.deleteByPrimaryKey(id);
        userRoleMapper.deleteByUserId(id);
        return true;
    }

    @Override
    public List<Role> selectUserRole(int id) {
        List<Role> roles = userRoleMapper.selectByUserId(id);
        return roles;
    }

    @Override
    public boolean updateRoleMenu(String ids, int userid, int creater) {
        if(ids.length() > 0){
            ids = ids.substring(0,ids.length()-1);
        }
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("roleids",ids);
        map.put("creator",creater);
        map.put("userid",userid);
        userRoleMapper.userRoleUpdate(map);
        return true;
    }

    @Override
    public WebResult updatePass(int userId, String psw, String oldPsw) {
        AdminUser user = adminUserMapper.selectByPrimaryKey(userId);
        if(user.getPsw().equalsIgnoreCase(MD5Util.MD5(oldPsw))){
            user.setUpdateuser(userId);
            user.setUpdatetime(new Date());
            user.setPsw(MD5Util.MD5(psw));
            if(adminUserMapper.updateByPrimaryKeySelective(user) > 0){
                return WebResult.success();
            }else{
                return WebResult.unKnown();
            }
        }else{
            return WebResult.error("旧密码错误");
        }
    }

    @Override
    public boolean updateLoginTime(AdminUser user){
        int res = adminUserMapper.updateLoginTime(user);
        return res == 1;
    }
}
