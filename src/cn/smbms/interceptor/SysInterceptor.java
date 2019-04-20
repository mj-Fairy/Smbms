package cn.smbms.interceptor;

import cn.smbms.pojo.User;
import cn.smbms.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysInterceptor extends HandlerInterceptorAdapter {
        private Logger log = Logger.getLogger(SysInterceptor.class);

        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handle) throws Exception {
            log.debug("SysInterceptor preHandle!");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/401.jsp");
                return false;
            }
            return true;
        }
}
