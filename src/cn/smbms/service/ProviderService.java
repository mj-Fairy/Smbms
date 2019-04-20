package cn.smbms.service;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {
    int getProvidersCount(String proCode, String proName);

    List<Provider> getProviders(String proCode, String proName, int currentPageNo, int pageSize);

    int addProvider(Provider provider);

    Provider getProviderById(Integer id);

    int modify(Provider provider);
}
