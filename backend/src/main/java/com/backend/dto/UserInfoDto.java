package com.backend.dto;

import org.springframework.beans.BeanUtils;

import com.backend.entity.user.UserInfo;

public class UserInfoDto {
	private String address;
	private String image;
	private Long orderCount;
	private UserDto user;
	private Long welfareCount;

	public UserInfoDto() {
	}

	public UserInfoDto(UserInfo entity) {
		if (entity.getUser() != null) {
			user = new UserDto(entity.getUser());
		}
		BeanUtils.copyProperties(entity, this);
	}

	public String getAddress() {
		return address;
	}

	public UserDto getUser() {
		return user;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public UserInfo toEntity() {
		UserInfo entity = new UserInfo();
		if (user != null) {
			entity.setUser(user.toEntity());
		}
		BeanUtils.copyProperties(this, entity);
		return entity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}

	public Long getWelfareCount() {
		return welfareCount;
	}

	public void setWelfareCount(Long welfareCount) {
		this.welfareCount = welfareCount;
	}
}
