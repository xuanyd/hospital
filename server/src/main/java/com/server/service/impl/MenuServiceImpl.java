package com.server.service.impl;

import com.server.dao.MenuMapper;
import com.server.entity.Menu;
import com.server.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectTreeMenus() {
        Map<String, Object> params  = new HashMap<>();
        params.put("level", 0);
        List<Menu> parentMenus = menuMapper.selectByIdsAndParent(params);
        for(Menu menu: parentMenus) {
            params.clear();
            params.put("parent", menu.getId());
            menu.setChilds(menuMapper.selectByIdsAndParent(params));
        }
        return parentMenus;
    }
}
