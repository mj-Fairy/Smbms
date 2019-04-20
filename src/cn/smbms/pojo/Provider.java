package cn.smbms.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Provider {
    private Integer id;
    @NotEmpty(message = "供应商编码不能为空")
    private String proCode;
    @NotEmpty(message = "供应商名称不能为空")
    private String proName;
    private String proDesc;
    @NotEmpty(message = "联系人不能为空")
    private String proContact;
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机格式必须为")
    private String proPhone;
    private String proAddress;
    private String proFax;
    private Integer createdBy;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    //企业营业执照照片功能
    private String companyLicPicPath;
    //组织机构代码证
    private String orgCodePicPath;
}
