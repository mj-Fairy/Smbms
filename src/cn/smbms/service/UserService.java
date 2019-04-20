package cn.smbms.service;

import cn.smbms.pojo.User;

import java.util.List;

public interface UserService {
    User login(String userCode, String userPassword);

    List<User> getUsrsList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize);

    int getCount(String userName, Integer userRole);

    int addUser(User user);

    int modify(User user);

    User getUserById(Integer id);

    User selectUserCodeExist(String userCode);

    int deleteUserByID(Integer id);

    Boolean updatePwd(User user);


}
