package cn.smbms.service;

import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.Provider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderMapper providerMapper;

    @Override
    public int addProvider(Provider provider) {
        int count = 0;
        try {
            count = providerMapper.addProvider(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int getProvidersCount(String proCode, String proName) {
        int count = 0;
        try {
            count = providerMapper.getProvidersCount(proCode, proName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Provider> getProviders(String proCode, String proName, int currentPageNo, int pageSize) {
        List<Provider> providerList = new ArrayList<>();
        try {
            providerList = providerMapper.getProviders(proCode, proName, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return providerList;
    }

    @Override
    public Provider getProviderById(Integer id) {
        Provider provider = null;
        try {
            provider = providerMapper.getProviderById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    @Override
    public int modify(Provider provider) {
        int count = 0;
        try {
            count = providerMapper.UpdateProvider(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
