package com.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 移动端异常崩溃记录
 * @author LiBing
 */
@Entity
@Table(name = "app_crush")
public class AppCrush extends BaseEntity {
	private Integer id;
	private Integer OSType;
	private String exception;
	private String logUrl;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, precision = 20, scale = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "os_type", precision = 4, scale = 0)
	public Integer getOSType() {
		return OSType;
	}

	public void setOSType(Integer oSType) {
		this.OSType = oSType;
	}

	@Column(name = "exception", nullable = false, length = 100)
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Column(name = "log_url", nullable = false, length = 200)
	public String getLogUrl() {
		return logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

}
