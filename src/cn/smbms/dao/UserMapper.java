package cn.smbms.dao;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    /**
     * 判断用户登录
     *
     * @param userCode 用户编码
     * @return
     */
    public User getUsers(@Param("userCode") String userCode);

    /**
     * 添加用户时 使用ajax 判断是否重复
     *
     * @param code 用户编码
     * @return
     */
    public User getUserByCode(String code);

    /**
     * 根据用户ID获得用户
     *
     * @param id 用户ID
     * @return
     */
    public User getUserById(Integer id);


    /**
     * \
     * 分页获得用户列表
     *
     * @param userName      查询条件1  模糊查询用户名
     * @param userRole      查询条件2 精确查询用户角色ID
     * @param currentPageNo 当前页码
     * @param pageSize      页码条数
     * @return
     * @throws Exception
     */
    public List<User> getUserList(@Param("userName") String userName,
                                  @Param("userRole") Integer userRole,
                                  @Param("currentPageNo") Integer currentPageNo,
                                  @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 在分页查询中 根据查询条件 获得 总条数来获得页数
     *
     * @param userName 用户名姓名
     * @param userRole 用户角色ID
     * @return
     */
    public int getUsersListCount(@Param("userName") String userName,
                                 @Param("userRole") Integer userRole);

    /**
     * 添加用户
     *
     * @param user 待添加的用户
     * @return
     */
    public int addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 修改的用户
     * @return
     */
    public int UpdateUser(User user);


    /**
     * 修改用户密码
     *
     * @param user 修改密码的用户
     * @return
     */
    public int updatePwd(User user);


    /**
     * 根据ID 删除用户   如果存在图片资源 先删除资源
     *
     * @param id 要删除的用户ID
     * @return
     */
    public int deleteUser(Integer id);

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int updateUserPwd(User user);

}
