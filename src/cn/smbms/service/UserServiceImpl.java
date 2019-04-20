package cn.smbms.service;

import cn.smbms.dao.UserMapper;
import cn.smbms.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userCode, String userPassword) {
        User user = null;
        user = userMapper.getUserByCode(userCode);
        if (user != null) {
            if (!user.getUserPassword().equals(userPassword)) {
                user = null;
            }
        }
        return user;
    }

    @Override
    public List<User> getUsrsList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize) {
        List<User> userList = new ArrayList<User>();
        try {
            userList = userMapper.getUserList(userName, userRole, currentPageNo, pageSize);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int getCount(String userName, Integer userRole) {

        int count = 0;
        try {

            count = userMapper.getUsersListCount(userName, userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }

    @Override
    public int addUser(User user) {

        int count = 0;
        try {

            count = userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }

    @Override
    public int modify(User user) {
        int count = 0;
        try {
            count = userMapper.UpdateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        try {
            user = userMapper.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public User selectUserCodeExist(String userCode) {
        User user = null;
        try {
            user = userMapper.getUserByCode(userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public Boolean updatePwd(User user) {
        int count = userMapper.updateUserPwd(user);
        return count>0;
    }

    @Override
    public int deleteUserByID(Integer id) {
        int count = 0;
        try {
            User user = userMapper.getUserById(id);

            if (user.getIdPicPath() != null || !user.getIdPicPath().equals("")) {
                File file = new File(user.getIdPicPath());
                if (file.exists()) {
                    file.delete();
                }
            }
            if (user.getWorkPicPath() != null || !user.getWorkPicPath().equals("")) {
                File file = new File(user.getWorkPicPath());
                if (file.exists()) {
                    file.delete();
                }
            }
            count = userMapper.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}

