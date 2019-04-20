package cn.smbms.service;

import cn.smbms.dao.RoleMapper;
import cn.smbms.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllList() {
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = roleMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }
}
