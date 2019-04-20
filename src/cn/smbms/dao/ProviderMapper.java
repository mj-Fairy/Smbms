package cn.smbms.dao;

import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {

    /**
     * 查询所有供应商
     * @param proCode
     * @param proName
     * @param currPageNo
     * @param pageSize
     * @return
     */
    public List<Provider> getProviders(@Param("proCode") String proCode,
                                       @Param("proName") String proName,
                                       @Param("currPageNo") int currPageNo, //当前页码
                                       @Param("pageSize") int pageSize);//总条数

    //数据总条数
    public int getProvidersCount(@Param("proCode") String proCode,
                                 @Param("proName") String proName);

    public int addProvider(Provider provider);

    public Provider getProviderById(Integer id);

    public int UpdateProvider(Provider provider);
}
