package com.backend.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.backend.entity.BaseEntity;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	private String email;
	private Integer gender;
	private Long id;
	private String mobile;
	private String name;
	private String password;
	private Date tokenCreateTime;
	private UserInfo userInfo;
	private String userToken;
	private Integer point;
	private String avator;

	@Column(name = "email", unique = true, length = 50)
	public String getEmail() {
		return email;
	}
	
	@Column(name = "gender", precision = 1, scale = 0)
	public Integer getGender() {
		return gender;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getId() {
		return id;
	}

	@Column(name = "mobile", unique = true, nullable = false, length = 20)
	public String getMobile() {
		return mobile;
	}

	@Column(name = "name", unique = true, length = 50)
	public String getName() {
		return name;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "token_create_time", length = 50)
	public Date getTokenCreateTime() {
		return tokenCreateTime;
	}

	@OneToOne
	@PrimaryKeyJoinColumn
	public UserInfo getUserInfo() {
		return userInfo;
	}

	@Column(name = "token", length = 36)
	public String getUserToken() {
		return userToken;
	}
	
	@Column(name = "point", precision = 20, scale = 0, columnDefinition = "INT DEFAULT 0")
	public Integer getPoint() {
		return point;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTokenCreateTime(Date tokenCreateTime) {
		this.tokenCreateTime = tokenCreateTime;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Column(name = "avator", length = 300)
	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

}
