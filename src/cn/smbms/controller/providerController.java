package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderService;
import cn.smbms.utils.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/*供应商控制器*/
@Controller
@RequestMapping("/sys/provider")
public class providerController {
    @Resource
    private ProviderService providerService;
    /*转到供应商列表*/
    @RequestMapping("/providerlist.html")
    public String list(@RequestParam(value = "queryProCode",required = false) String queryProCode,
                       @RequestParam(value = "queryProName",required = false) String queryProName,
                       @RequestParam(value = "pageIndex",required = false,defaultValue = "1") String currentPageNo,
                       Model model){
        PageSupport p=new PageSupport();
        String newQueryCode=queryProCode;
        String newQueryProName=queryProName;
        int newcurrentPage=Integer.parseInt(currentPageNo); //当前页码

        if (queryProCode == null && queryProCode == "") {
            newQueryCode="";
        }
        if(queryProName==null && queryProName==""){
            newQueryProName="";
        }
        int count = providerService.getProvidersCount(newQueryCode,newQueryProName);
        p.setPageSize(5);   //一页数据容量
        p.setTotalCount(count); //总数据量
        p.setCurrentPageNo(newcurrentPage);//当前页码
        int totalPageCount=count/5;
        int newcurrent=(newcurrentPage-1)*5;
        List<Provider> list= providerService.getProviders(newQueryCode,newQueryProName,newcurrent,5);
        model.addAttribute("list",list);
        model.addAttribute("newQueryCode",newQueryCode);
        model.addAttribute("newQueryProName",newQueryProName);
        //总条数，当前页码，总页数
        model.addAttribute("totalCount",count);
        model.addAttribute("currentPageNo",newcurrentPage);
        model.addAttribute("totalPageCount",totalPageCount);
        return "providerlist";
    }
}
