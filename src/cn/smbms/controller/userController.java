package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.RoleService;
import cn.smbms.service.UserService;
import cn.smbms.utils.Constants;
import cn.smbms.utils.PageSupport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys/user")
public class userController {

    private Logger log = Logger.getLogger(userController.class);
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    /*进入修改密码页面 get请求*/
    @RequestMapping("/modifyPwd.html")
    public String toModify(){
        return "/pwdmodify";
    }

    @RequestMapping("/pwdsave.html")
    public String toSave(@RequestParam("rnewpassword") String rnewpassword,
                         HttpSession session,
                         Model model){
        log.info("密码为："+rnewpassword);
        Integer id=((User)session.getAttribute(Constants.USER_SESSION)).getId();
        User user=new User();
        user.setId(id);
        user.setUserPassword(rnewpassword);
        if(userService.updatePwd(user)){
            model.addAttribute("message","修改密码成功");
        }else{
            model.addAttribute("message","修改密码失败");
        }
        return "pwdmodify";
    }



    @RequestMapping(value = "/list.html")
    public String userlist(Model model,
                           @RequestParam(value = "queryname", required = false) String queryname,
                           @RequestParam(value = "queryUserRole", required = false) String queryUserRole,
                           @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        log.info("userController ========================> :queryname " + queryname);
        log.info("userController ========================> :queryUserRole " + queryUserRole);
        log.info("userController ========================> :pageIndex " + pageIndex);
        int _queryUserRole = 0;
        List<User> userList = null;
        if (queryname == null) {
            queryname = "";
        }
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;
        if (queryUserRole != null && !queryUserRole.equals("")) {
            _queryUserRole = Integer.valueOf(queryUserRole);
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "redirect:/sys/syserror.html";
            }
        }
        int totalCount = userService.getCount(queryname, _queryUserRole);
        PageSupport support = new PageSupport();
        support.setCurrentPageNo(currentPageNo);
        support.setPageSize(pageSize);
        support.setTotalCount(totalCount);


        int currPageNoCount = support.getTotalPageCount();
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > currPageNoCount) {
            currentPageNo = currPageNoCount;
        }
        log.info(currentPageNo);
        int startSize = (currentPageNo - 1) * pageSize;
        userList = userService.getUsrsList(queryname, _queryUserRole, startSize, pageSize);

        List<Role> roles = roleService.getAllList();
        model.addAttribute("queryUserName", queryname);
        model.addAttribute("queryUserRole", _queryUserRole);
        model.addAttribute("userList", userList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        model.addAttribute("totalPageCount", currPageNoCount);
        model.addAttribute("roleList", roles);
        return "/userlist";
    }


    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String add(Model model, User user) {
        model.addAttribute("user", user);
        return "/useradd";
    }

    @RequestMapping(value = "/ucexist.html", method = RequestMethod.GET)
    @ResponseBody
    public Object ucexist(@RequestParam(value = "userCode", required = false) String userCode) {
        User user = userService.selectUserCodeExist(userCode);
        Map<String, Object> map = new Hashtable<>();
        if (user != null) {
            /*已存在*/
            map.put("userCode", "exist");
        } else if (userCode == null || userCode.equals("")) {
            /*用户编码不能为空*/
            map.put("userCode", "novalue");
        } else {
            /*添加成功*/
            map.put("userCode", "noexist");
        }

        return JSON.toJSON(map);
    }

    @RequestMapping(value = "/rolelist.json", method = RequestMethod.GET)
    @ResponseBody
    public Object roleList() {
        List<Role> roleList = roleService.getAllList();
        return JSONArray.toJSON(roleList);
    }

    @RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
    public String addSave(@Valid User user, BindingResult bindingResult, HttpSession session, HttpServletRequest request,
                          @RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
        if (bindingResult.hasErrors()) {
            log.debug("Add user validated has error================");
            return "/useradd";
        }
        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;
        boolean flag = true;
        System.out.println("122222222222222222222222222222222222");
        String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        log.info("uploadFile path =========================> " + path);
        for (int i = 0; i < attachs.length; i++) {
            MultipartFile attach = attachs[i];
            if (!attach.isEmpty()) {
                if (i == 0) {
                    errorInfo = "uploadFileError";
                } else if (i == 1) {
                    errorInfo = "uploadWpError";
                }
                String oldFileName = attach.getOriginalFilename();
                String prefix = FilenameUtils.getExtension(oldFileName);
                int fileSize = 500000;
                if (attach.getSize() > fileSize) {
                    request.setAttribute(errorInfo, "* 上传文件大小不能超过500KB");
                    flag = false;
                } else if (prefix.equalsIgnoreCase("jpg") ||
                        prefix.equalsIgnoreCase("png") ||
                        prefix.equalsIgnoreCase("jpeg") ||
                        prefix.equalsIgnoreCase("pneg")) {
                    String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
                    File targetFile = new File(path, fileName);
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    try {
                        attach.transferTo(targetFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute(errorInfo, "* 上传失败");
                        flag = false;
                    }
                    if (i == 0) {
                        idPicPath = path + File.separator + fileName;
                    } else if (i == 1) {
                        workPicPath = path + File.separator + fileName;
                    }
                    log.debug("idPicPath:" + idPicPath);
                    log.debug("workPicPath:" + workPicPath);
                } else {
                    request.setAttribute(errorInfo, "* 上传文件格式不正确!");
                    flag = false;
                }
            }
        }
        if (flag) {
            user.setIdPicPath(idPicPath);
            user.setWorkPicPath(workPicPath);
            user.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
            user.setCreationDate(new Date());
            if (userService.addUser(user) > 0) {
                session.setAttribute(Constants.USER_SESSION, user);
                return "redirect:/sys/user/list.html";
            }
        }
        return "/useradd";


    }
}
