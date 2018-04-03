package com.mogu.GEMAKER.api.action;

import com.mogu.GEMAKER.Enum.ResultCode;
import com.mogu.GEMAKER.constants.CommonConstant;
import com.mogu.GEMAKER.model.params.*;
import com.mogu.GEMAKER.entity.AdminUserDo;
import com.mogu.GEMAKER.service.AdminUserService;
import com.mogu.GEMAKER.util.BizResult;
import com.mogu.GEMAKER.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginCtrl extends BaseCtrl {



    @Autowired
    private AdminUserService adminUserService;




    @RequestMapping(value = "admin/index")
    public ModelAndView  index(){
        return new ModelAndView("index");
    }



    /**
     * 管理员登录
     * @param adminLoginPara
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/doLogin")
    public WebResult adlogin(@Valid @RequestBody AdminLoginPara adminLoginPara, BindingResult result, HttpServletRequest request){
        if (result.hasErrors()) return WebResult.paramError(result.getAllErrors().get(0).getDefaultMessage());
        return new WebResult(adminUserService.doLogin(adminLoginPara,request));
    }

    /**
     * 修改密码
     * @param adminPassPara
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(value = "admin/updatePass",method = RequestMethod.POST)
    public WebResult updatePass(@Valid @RequestBody AdminPassPara adminPassPara, BindingResult result, HttpServletRequest request) {
        return new WebResult(adminUserService.updatePass(adminPassPara));
    }

    /**
     * 删除管理员
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "admin/delAdminUser",method = RequestMethod.POST)
    public WebResult delAdminUser(HttpServletRequest request,@RequestParam(value = "id") int id) {
        HttpSession session = request.getSession();
        AdminUserDo adminUserDo =(AdminUserDo)session.getAttribute(CommonConstant.ADMIN_SESSION);
        if(adminUserDo == null){
            return new WebResult(BizResult.error(ResultCode.NO_AUTH));
        }
        return new WebResult(adminUserService.delAdminUser(adminUserDo,id));
    }

    /**
     * 添加管理员
     * @param adminUserPara
     * @return
     */
    @RequestMapping(value = "admin/addAdminUser",method = RequestMethod.POST)
    public WebResult addAdminUser(@RequestBody AdminUserPara adminUserPara){
        HttpSession session = request.getSession();
        AdminUserDo nowdo =(AdminUserDo)session.getAttribute(CommonConstant.ADMIN_SESSION);
        return  new WebResult(adminUserService.addAdminUser(nowdo,adminUserPara));

    }

    @RequestMapping(value = "admin/updateAdminUser",method = RequestMethod.POST)
    public WebResult lst(@RequestBody AdminUserDo adminUserDo){
        HttpSession session = request.getSession();
        AdminUserDo nowdo =(AdminUserDo)session.getAttribute(CommonConstant.ADMIN_SESSION);
        return new WebResult(adminUserService.updateAdminUser(nowdo,adminUserDo));
    }

    @RequestMapping(value = "admin/lst",method = RequestMethod.POST)
    public WebResult lst(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        HttpSession session = request.getSession();
        AdminUserDo nowdo =(AdminUserDo)session.getAttribute(CommonConstant.ADMIN_SESSION);
        return new WebResult(adminUserService.adminUserList(nowdo,pageNum,pageSize));
    }

}
