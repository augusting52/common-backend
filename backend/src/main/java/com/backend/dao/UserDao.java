package com.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.entity.user.User;

/**
 * 用户数据库接口.
 * 
 * @author LiBing
 * 
 */
public interface UserDao extends BaseDao<User, Long> {
	/**
	 * 根据手机号码获取用户信息
	 * 
	 * @param mobile
	 * @return User
	 */
	@Query("from User where status>=0 and mobile =:mobile")
	public User findByMobile(@Param("mobile") String mobile);

	/**
	 * 根据手机与密码获取用户
	 * 
	 * @param mobile
	 * @param password
	 * @return User
	 */
	@Query("from User where status>=0 and mobile =:mobile and password=:password")
	public User findByMobileAndPassword(@Param("mobile") String mobile,
			@Param("password") String password);

	/**
	 * 根据用户令牌获取用户.
	 * 
	 * @param userToken
	 * @return User
	 */
	@Query("from User where status>=0 and token =:userToken")
	public User findByToken(@Param("userToken") String userToken);

	/**
	 * 获取全部用户信息.
	 * 
	 * @return List
	 */
	@Query("from User where status>=0 ")
	public List<User> listUsers();
}
