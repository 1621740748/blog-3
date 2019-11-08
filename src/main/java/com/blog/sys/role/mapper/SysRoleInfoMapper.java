package com.blog.sys.role.mapper;

import com.blog.sys.role.model.SysRoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleInfoMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRoleInfo record);

    int insertSelective(SysRoleInfo record);

    SysRoleInfo selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRoleInfo record);

    int updateByPrimaryKey(SysRoleInfo record);

    /**
     * @Title: getList
     * @Description:  获取角色列表数据
     * @Param: sysRoleInfo 角色对象
     * @return: java.util.List<com.blog.sys.role.model.SysRoleInfo>
     * @author: yankai
     * @date   2019/11/8
     */
    List<SysRoleInfo> getList(@Param("vo") SysRoleInfo sysRoleInfo);
}