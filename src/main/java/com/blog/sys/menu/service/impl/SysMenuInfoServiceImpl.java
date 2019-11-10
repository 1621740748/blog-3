package com.blog.sys.menu.service.impl;

import com.blog.sys.menu.mapper.SysMenuInfoMapper;
import com.blog.sys.menu.service.ISysMenuInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName: blog
 * @Package: com.blog.sys.menu.service.impl
 * @ClassName: SysMenuInfoServiceImpl
 * @Description: java类作用描述
 * @Author: yanakai@126.com
 * @CreateDate: 2019/11/10 20:55
 * @UpdateUser: yanakai@126.com
 * @UpdateDate: 2019/11/10 20:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class SysMenuInfoServiceImpl implements ISysMenuInfoService {
    @Resource
    private SysMenuInfoMapper sysMenuInfoMapper;
    

}