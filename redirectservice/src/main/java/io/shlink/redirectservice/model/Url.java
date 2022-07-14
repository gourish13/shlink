package io.shlink.redirectservice.model;

import java.sql.Timestamp;

public class Url {

	private Long id;
	private Integer userId;
	private String urlCode;
	private String longUrl;
	private Timestamp createdAt;
	
	public Url() {
		super();
	}
	
	public Url(Long id, Integer userId, String urlCode, String longUrl, Timestamp createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.urlCode = urlCode;
		this.longUrl = longUrl;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", userId=" + userId + ", urlCode=" + urlCode + ", longUrl=" + longUrl + ", createdAt="
				+ createdAt + "]";
	}

}
