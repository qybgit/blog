package com.example.dao.mapper;

import com.example.dao.popj.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {
    /**
     * 通过authorId查找作者
     * @param id
     * @return
     */
    @Select("select * from ms_sys_user where id=${id}")
    SysUser selectUserById(long id);

    /**
     * 登陆查询
     * @param account
     * @param password
     * @return
     */
    @Select("select account, avatar,id,nickname from ms_sys_user where account='${account}' and password='${password}' limit 1")
    SysUser selectUser(String account, String password);

    /**
     * 注册前通过account查找用户
     * @param account
     * @return
     */
    @Select("select * from ms_sys_user where account=#{account} limit 1")
    SysUser selectByAccount(String account);

    /**
     * 插入用户数据
     * @param sysUser
     */
    @Insert("insert into ms_sys_user(account,admin,avatar,create_date,deleted,email,last_Login,mobile_phone_number,nickname,password,salt,status) values (#{account},#{admin},#{avatar},#{create_Date},#{deleted},#{email},#{last_Login},#{mobile_Phone_Number},#{nickname},#{password},#{salt},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(SysUser sysUser);

    /**
     *通过toUSerId查找ToUser
     * @param user_id
     * @return
     */
    @Select("select * from ms_comment where to_uid=#{user_id}")
    SysUser selectToUserById(long user_id);
}
