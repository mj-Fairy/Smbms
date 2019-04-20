package cn.smbms.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;
    private String roleCode;
    private String roleName;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}
