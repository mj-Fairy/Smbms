package cn.smbms.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private int age;
    @NotEmpty(message = "用户编码不能为空")
    private String userCode;
    @NotEmpty(message = "用户名称不能为空")
    private String userName;
    @Length(min = 6, max = 10, message = "用户密码长度为6到10")
    private String userPassword;
    private int gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String phone;
    private String address;
    private int userRole;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private String userRoleName;
    private String idPicPath;
    private String workPicPath;
}
