package com.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.backend.common.BackendException;
import com.backend.dao.UserDao;
import com.backend.dao.UserInfoDao;
import com.backend.dto.UserDto;
import com.backend.dto.UserInfoDto;
import com.backend.entity.user.User;
import com.backend.entity.user.UserInfo;
import com.backend.util.DtoUtils;

/**
 * 用户服务类
 * 
 * @author LiBing
 * 
 */
@Service
public class UserService {
	
	/**
	 * 多线程并发变量
	 */
	private ThreadLocal<User> currentUser = new ThreadLocal<User>();
	
	/**
	 * 最多每天下发短信验证次数
	 */
	@Value("${max.send.mobile.verification.code.count}")
	private int maxSendMobileVerificationCodeCount;

	/**
	 * 用户数据库接口
	 */
	@Autowired
	private UserDao userDao;
	
	/**
	 * 用户信息数据库接口
	 */
	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * 更改用户密码
	 * 
	 * @param mobile
	 * @param password
	 * @return String
	 */
	@Transactional
	public String changeUserPassword(String mobile, String password) {
		User user = userDao.findByMobile(mobile);
		user.setPassword(password);
		user.setUserToken(UUID.randomUUID().toString());
		return user.getUserToken();
	}

	/**
	 * 验证用户权限
	 * 
	 * @param authorityName
	 */
	@Transactional(readOnly = true)
	public void checkUserAuthority(String authorityName) {
		User user = currentUser.get();
		if (user == null) {
			throw new BackendException(100005);
		}
		if (authorityName == null || authorityName.length() == 0) {
			return;
		}
		throw new BackendException(100007);
	}

	/**
	 * 创建用户令牌
	 * 
	 * @param mobile
	 * @param password
	 * @return String
	 */
	@Transactional
	public String createUser(String mobile, String password) {
		User entity = new User();
		entity.setMobile(mobile);
		entity.setPassword(password);
		return createUser(entity).getUserToken();
	}

	/**
	 * 注册用户
	 * 
	 * @param entity
	 * @return User
	 */
	@Transactional
	public User createUser(User entity) {
		if (StringUtils.isEmpty(entity.getMobile())) {
			throw new BackendException(100004);
		}
		isMobileExist(entity.getMobile());

		// hasSentMaxTimes(dto.getMobile());
		entity.setUserToken(UUID.randomUUID().toString());
		entity.setTokenCreateTime(new Date());
		userDao.save(entity);

		return entity;
	}

	/**
	 * 
	 * 获取用户信息（包括购物车和订单数据）
	 * 
	 * @param userToken
	 * @return UserInfoNew
	 */
//	@Transactional(readOnly = true)
//	public UserInfoNew getUserInfo(String userToken) {
//		User user = getCurrentUserByToken(userToken);
//		UserInfoNew userInfonNew = new UserInfoNew(user);
//		if (user.getUserInfo() != null) {
//			userInfonNew.setAddress(user.getUserInfo().getAddress());
//		}
//		userInfonNew.setOrderCount(orderService.getOrderCountByUserId(user
//				.getId()));
//		userInfonNew.setCarts(cartService.getCartAll(userToken));
//		return userInfonNew;
//	}

	/**
	 * 查找用户
	 * 
	 * @param userId
	 * @return UserDto
	 */
	@Transactional(readOnly = true)
	public UserDto findUser(Long userId) {
		return new UserDto(userDao.findOne(userId));
	}

	/**
	 * 获取当前用户
	 * 
	 * @return User
	 */
	public User getCurrentUser() {
		return currentUser.get();
	}

	/**
	 * 根据用户令牌获取当前用户
	 * 
	 * @param userToken
	 * @return User
	 */
	@Transactional(readOnly = true)
	public User getCurrentUserByToken(String userToken) {
		User user = userDao.findByToken(userToken);
		if (user == null) {
			throw new BackendException(100005);
		}
		if (System.currentTimeMillis() - user.getTokenCreateTime().getTime() > 86400000) {
			throw new BackendException(100006);
		}
		currentUser.set(user);
		return currentUser.get();
	}

	/**
	 * 捕获异常的getCurrentUserByToken()方法
	 * 
	 * @param userToken
	 */
	public void getCurrentUserByTokenNoException(String userToken) {
		try {
			getCurrentUserByToken(userToken);
		} catch (BackendException e) {
		}
	}

	/**
	 * 判断手机是否注册
	 * 
	 * @param mobile
	 */
	private void isMobileExist(String mobile) {
		if (userDao.findByMobile(mobile) != null) {
			throw new BackendException(100001);
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param mobile
	 * @param password
	 * @return String
	 */
	@Transactional
	public String login(String mobile, String password) {
		User user = userDao.findByMobileAndPassword(mobile, password);
		return refreshToken(user);
	}

	/**
	 * 更新用户令牌
	 * 
	 * @param user
	 * @return String
	 */
	private String refreshToken(User user) {
		if (user != null) {
			user.setUserToken(UUID.randomUUID().toString());
			user.setTokenCreateTime(new Date());
			userDao.save(user);
			currentUser.set(user);
			return user.getUserToken();
		}
		throw new BackendException(100005);
	}

	/**
	 * 重新登录
	 * 
	 * @param userToken
	 * @return String
	 */
	@Transactional
	public String reLogin(String userToken) {
		User user = userDao.findByToken(userToken);
		return refreshToken(user);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 */
	@Transactional
	public void updateUserInfo(UserInfoDto userInfo) {
		User user = getCurrentUser();
		UserInfo userInfoEntity = user.getUserInfo();
		if (userInfoEntity == null) {
			userInfoEntity = userInfo.toEntity();
			userInfoEntity.setUser(user);
			userInfoDao.save(userInfoEntity);
		} else {
			userInfoEntity.setAddress(userInfo.getAddress());
		}
	}

	/**
	 * 分页获取用户
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param sortDirection
	 * @param sortProperty
	 * @param email
	 * @param gender
	 * @param mobile
	 * @param name
	 * @param point
	 * @return Page
	 */
	@Transactional
	public Page<UserDto> listUserByPage(int currentPage, int pageSize,
			String sortDirection, String sortProperty, String email,
			Integer gender, String mobile, String name, Integer point) {
		PageRequest pageRequest = new PageRequest(currentPage, pageSize,
				Direction.fromString(sortDirection), sortProperty);
		Page<User> users = userDao.findAll(
				buildSpecification(email, gender, mobile, name, point),
				pageRequest);
		return DtoUtils.userDtoUtil.toDTO(users, pageRequest);
	}

	/**
	 * 创建数据库查询规格模式
	 * 
	 * @param email
	 * @param gender
	 * @param mobile
	 * @param name
	 * @param point
	 * @return Specification
	 */
	private Specification<User> buildSpecification(final String email,
			final Integer gender, final String mobile, final String name,
			final Integer point) {
		return new Specification<User>() {

			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.ge(root.get("status").as(Integer.class), 0));
				if (email != null) {
					predicates.add(cb.like(root.get("email").as(String.class),
							"%" + email + "%"));
				}
				if (gender != null) {
					predicates.add(cb.equal(root.get("gender")
							.as(Integer.class), gender));
				}
				if (mobile != null) {
					predicates.add(cb.like(root.get("mobile").as(String.class),
							"%" + mobile + "%"));
				}
				if (name != null) {
					predicates.add(cb.like(root.get("name").as(String.class),
							"%" + name + "%"));
				}
				if (point != null) {
					predicates.add(cb.ge(root.get("point").as(Integer.class),
							point));
				}
				if (predicates.size() > 0) {
					cq.where(cb.and(predicates.toArray(new Predicate[predicates
							.size()])));
				} else {
					cq.where(cb.conjunction());
				}
				return cq.getRestriction();
			}

		};
	}

	/**
	 * 获取全部用户
	 * 
	 * @return List
	 */
	@Transactional
	public List<UserDto> listUsers() {
		return DtoUtils.userDtoUtil.toDTO(userDao.listUsers());
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userToken
	 * @param para
	 */
	@Transactional
	public void updateUserInfo(String userToken, Map<String, String> para) {
		User user = getCurrentUserByToken(userToken);
		if (para.get("name") != null) {
			user.setName(para.get("name"));
		} else if (para.get("gender") != null) {
			user.setGender(Integer.valueOf(para.get("gender")));
		} else if (para.get("email") != null) {
			user.setEmail(para.get("email"));
		} else if (para.get("password") != null) {
			user.setPassword(para.get("password"));
		} else if (para.get("address") != null) {
			user.getUserInfo().setAddress(para.get("address"));
		} else if (para.get("avator") != null) {
			user.setAvator(para.get("avator"));
		}
	}
}
