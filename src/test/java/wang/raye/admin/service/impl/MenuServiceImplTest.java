package wang.raye.admin.service.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.raye.admin.model.Menu;
import wang.raye.admin.service.MenuService;

import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {
    @Autowired
    private MenuService menuService;

    @Test
    public void selectAll() throws Exception {
        List<Menu> menuList =  menuService.selectAll();
        Assert.assertEquals(menuList.size(),14);
    }

}