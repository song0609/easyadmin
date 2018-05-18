package wang.raye.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.raye.admin.model.Menu;
import wang.raye.admin.model.mapper.MenuMapper;
import wang.raye.admin.service.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 菜单相关数据库业务接口实现类
 * @author Raye
 * @since 2016年12月1日17:36:00
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> selectAll() {
		HashMap<Integer,ArrayList<Menu>> map = new HashMap<Integer, ArrayList<Menu>>();
		List<Menu> menus = menuMapper.selectByExample(null);
		for(Menu menu : menus){
			int parentid = menu.getParentid();
			if(map.containsKey(parentid)){
				map.get(parentid).add(menu);
			}else{
				ArrayList<Menu> temp = new ArrayList<Menu>();
				temp.add(menu);
				map.put(menu.getParentid(),temp);
			}
		}
		for(Menu menu : menus){
			int id = menu.getId();
			if(map.containsKey(id)){
                menu.setType("folder");
				menu.setChildren(map.get(id));
			}else{
                menu.setType("item");
            }
		}
		return map.get(0);
	}

	@Override
	public List<Menu> selectByUser(int userId) {
		HashMap<Integer,ArrayList<Menu>> map = new HashMap<Integer, ArrayList<Menu>>();
		List<Menu> tempMenus = null;
		if(userId == -1){
			tempMenus = menuMapper.selectAllEnable();
		}else {
			tempMenus = menuMapper.selectByUser(userId);
		}
		for(Menu menu : tempMenus){
			int parentid = menu.getParentid();
			if(map.containsKey(parentid)){
				map.get(parentid).add(menu);
			}else{
				ArrayList<Menu> temp = new ArrayList<Menu>();
				temp.add(menu);
				map.put(menu.getParentid(),temp);
			}
		}
		for(Menu menu : tempMenus){
			int id = menu.getId();
			if(map.containsKey(id)){
				menu.setType("folder");
				menu.setChildren(map.get(id));
			}else{
				menu.setType("item");
			}
		}
		return map.get(0);
	}

	@Override
	public List<String> selectAuthorities(int userId) {
		if(userId == -1){
			return menuMapper.selectAuthoritiesByRoot();
		}
		return menuMapper.selectAuthorities(userId);
	}

	public boolean addMenu(Menu menu) {
		return menuMapper.insertSelective(menu) > 0;
	}

	@Override
	public boolean updateMenu(Menu menu) {
		return menuMapper.updateByPrimaryKeySelective(menu) > 0;
	}

    @Override
    @Transactional
    public boolean delete(int id) {
        HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("menuid",id);
		menuMapper.deleteMenuById(map);
        return true;
    }

}
