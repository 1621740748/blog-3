package com.blog.sys.menu.mapper;

import com.blog.sys.menu.model.SysMenuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuInfoMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenuInfo record);

    int insertSelective(SysMenuInfo record);

    SysMenuInfo selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenuInfo record);

    int updateByPrimaryKey(SysMenuInfo record);

    /**
     * @Title: getMenuList
     * @Description:  查询菜单列表
     * @Param: sysMenuInfo
     * @return: java.util.List<com.blog.sys.menu.model.SysMenuInfo>
     * @author: yankai
     * @date   2019/11/11
     */
    List<SysMenuInfo> getMenuList(@Param("vo") SysMenuInfo sysMenuInfo);

    /**
     * @Title: getMaxSortByParentId
     * @Description:  获取同一级别的菜单最大的排序码
     * @Param: parentId 父id
     * @return: int
     * @author: yankai
     * @date   2019/11/11 
     */ 
    int getMaxSortByParentId(String parentId);

    /**
     * @Title: checkMenuNameUnique
     * @Description:  校验同级别的菜单名称是否唯一
     * @Param: menuName 菜单名称
     * @Param: parentId 父级id
     * @return: com.blog.sys.menu.model.SysMenuInfo
     * @author: yankai
     * @date   2019/11/11
     */
    SysMenuInfo checkMenuNameUnique(@Param("parentId") String parentId,@Param("menuName")String menuName);

    /**
     * @Title: getMenuInfoById
     * @Description:  通过主键查询菜单信息 包含父级菜单名称
     * @Param: menuId 菜单主键
     * @return: com.blog.sys.menu.model.SysMenuInfo
     * @author: yankai
     * @date   2019/11/12 
     */ 
    SysMenuInfo getMenuInfoById(String menuId);

    /**
     * @Title: getChildMenuInfoById
     * @Description:  通过主键id查询其子菜单数据
     * @Param: menuId  菜单主键id
     * @return: java.util.List<com.blog.sys.menu.model.SysMenuInfo>
     * @author: yankai
     * @date   2019/11/12
     */
    List<SysMenuInfo> getChildMenuInfoById(String menuId);

    /**
     * @Title: selectMenuAll
     * @Description:  获取所有的菜单列表
     * @Param:
     * @return: java.util.List<com.blog.sys.menu.model.SysMenuInfo>
     * @author: yankai
     * @date   2019/11/12
     */
    List<SysMenuInfo> selectMenuAll();

    /**
     * @Title: selectMenuAllByUserId
     * @Description:  通过用户id获取用户所拥有的菜单权限数据
     * @Param: userId 用户id
     * @return: java.util.List<com.blog.sys.menu.model.SysMenuInfo>
     * @author: yankai
     * @date   2019/11/12
     */
    List<SysMenuInfo> selectMenuAllByUserId(String userId);

    /**
     * @Title: selectMenuTree
     * @Description:  通过角色id获取菜单树
     * @Param: roleId 角色id
     * @return: java.util.List<java.lang.String>
     * @author: yankai
     * @date   2019/11/12
     */
    List<String> selectMenuTree(String roleId);

    /**
     * @method:  getMenuParamsByUserId
     * @description: <p>根据用户查询功能权限</p>
     * @params:  userId 用户id
     * @return: java.util.List<java.lang.String>
     * @date: 2019/11/12 23:01
     * @author: yanakai@126.com       
     */
    List<String> getMenuParamsByUserId(String userId);

    /**
     *
     * <p>Title: selectMenuAllByUserId</p>
     * <p>Description: 查询当前用户id下的菜单数据</p>
     * @param： userId 用户id
     * @param： flag true查询所有菜单和功能点  false查询目录和菜单
     * @return
     * @author yanakai@126.com
     * @date 2019年8月10日
     */
    List<SysMenuInfo> getMenuListByUserId(@Param("flag")boolean flag,@Param("userId")String userId);

    /**
     *
     * <p>Title: getMenuParamsList</p>
     * <p>Description: 查询菜单和功能点</p>
     * @param： flag true查询所有菜单和功能点  false查询目录和菜单
     * @return
     * @author yanakai@126.com
     * @date 2019年8月11日
     */
    List<SysMenuInfo> getMenuParamsList(@Param("flag")boolean flag);
}