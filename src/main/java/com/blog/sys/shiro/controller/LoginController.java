package com.blog.sys.shiro.controller;

import com.blog.sys.common.base.BaseController;
import com.blog.sys.common.base.ResponseData;
import com.blog.sys.common.utils.StringUtils;
import com.blog.sys.menu.model.SysMenuInfo;
import com.blog.sys.menu.service.ISysMenuInfoService;
import com.blog.sys.shiro.utils.ShiroUtils;
import com.blog.sys.user.model.SysUserInfo;
import com.blog.sys.user.service.ISysUserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: com.blog.sys.shiro.controller
 * @ClassName: LoginController
 * @Description: 登录控制层
 * @Author: yanakai@126.com
 * @CreateDate: 2019/11/12 22:32
 * @UpdateUser: yanakai@126.com
 * @UpdateDate: 2019/11/12 22:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
public class LoginController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    /**
     * 用户业务层接口
     */
    @Resource
    private ISysUserInfoService sysUserInfoService;

    /**
     * 菜单业务层接口
     */
    @Resource
    private ISysMenuInfoService sysMenuInfoService;

    @GetMapping({"/login",""})
    public String login() {
        return "/sys/login";
    }
    /**
     * @method:  unauthorize
     * @description: <p>无权限跳转页面</p>
     * @params:
     * @return: java.lang.String
     * @date: 2019/11/12 22:34
     * @author: yanakai@126.com
     */
    @RequestMapping("/unauth")
    public String unauthorize() {
        return "/error/unauth";
    }
    /**
     * @method:  logout
     * @description: <p>退出返回登录页面</p>
     * @params:
     * @return: java.lang.String
     * @date: 2019/11/12 22:35
     * @author: yanakai@126.com
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    /**
     * @method:  userLoginWithRealmAction
     * @description: <p>登录 </p>
     * @params:  userName 用户名
     * @Param password 密码
     * @return: ResponseData
     * @date: 2019/11/12 22:35
     * @author: yanakai@126.com       
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseData userLoginWithRealmAction (String userName,String password,String rememberMe){
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            return operateSucess();
        }catch (AuthenticationException e){
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())){
                msg = e.getMessage();
            }
            return operateFailed(msg);
        }
    }

    /**
     *
     * <p>Title: index</p>
     * <p>Description: 跳转值系统首页</p>
     * @param: model
     * @return
     * @author yanakai@126.com
     * @date 2019年7月27日
     */
    @RequestMapping(value="index",method = RequestMethod.GET)
    public String index(Model model){
        SysUserInfo sysUserInfo= ShiroUtils.getSysUser();
        //查询目录和菜单
        List<SysMenuInfo> menuList=sysMenuInfoService.getMenuListByUser(sysUserInfo);
        model.addAttribute("menuList", menuList);
        model.addAttribute("user", sysUserInfo);
        return "/sys/index";
    }
    // 系统介绍页
    @GetMapping("/sys/main")
    public String main(Model model) {
        return "/sys/main";
    }
}