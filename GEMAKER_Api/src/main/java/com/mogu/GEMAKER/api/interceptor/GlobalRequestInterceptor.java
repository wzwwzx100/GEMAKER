package com.mogu.GEMAKER.api.interceptor;

import com.mogu.GEMAKER.constants.CommonConstant;
import com.mogu.GEMAKER.model.entity.AdminUserDo;
import com.mogu.GEMAKER.exceptions.NoToken;
import com.mogu.GEMAKER.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GlobalRequestInterceptor extends AbstractInterceptor {

    private static Logger logger = LoggerFactory.getLogger(GlobalRequestInterceptor.class);
    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //先验证权限
//        if(isPermission(request)) {
//
//            throw new NoToken();
//        }
//
//        if (isExtraUrl(request))return true;//例外


//        //token 验证
//        if(!tokenValid(request)){
//            if (logger.isDebugEnabled())logger.error("无登录令牌");
//            throw new NoToken();
//        }
        //登录验证

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

//    /**
//     * token 验证
//     * @param request
//     * @return
//     */
//    private boolean tokenValid(HttpServletRequest request){
//        String token = request.getHeader("token");
//        if (StringUtils.isNotEmpty(token)){
//            UserDo token1 =  redisService.getObj(token,UserDo.class);
//            if (null != token1)return true;
//        }
//        return false;
//    }
    /**
     * 判断是否是例外的接口，以资源开头为准
     * @param request
     * @return
     */
    private boolean isExtraUrl(HttpServletRequest request){

        String uri = request.getPathInfo();
        if(uri == null){
            return true;
        }
        for (String o:CommonConstant.EXTRA_URI){
            if (uri.startsWith(o)){
                return true;
            }
        }
        return false;
    }

    private boolean isPermission(HttpServletRequest request){
        String uri = request.getPathInfo();
        if(uri != null){
            for(String o : CommonConstant.PERMISSION_URI){
                if (uri.startsWith(o)){
                    HttpSession session = request.getSession();
                    System.out.println("ADMIN SESSION:"+session.getId());
                    AdminUserDo nowdo =(AdminUserDo)session.getAttribute(CommonConstant.ADMIN_SESSION);
                    if(nowdo == null){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 判断 json 请求
     */
    private boolean isJsonPost(HttpServletRequest request){
        String contentType = request.getContentType();
        return "text/json".equalsIgnoreCase(contentType) || "application/json".equalsIgnoreCase(contentType);
    }

}
