package com.dushan.zhongchou.mapper;

import com.dushan.zhongchou.entity.Admin;
import com.dushan.zhongchou.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    /*根据条件查询用户*/
    List<Admin> selectAdminListByKeyword(String keyword);
    //根据id删除用户
    void removeUserById(@Param("ids")List<Integer> ids);
}