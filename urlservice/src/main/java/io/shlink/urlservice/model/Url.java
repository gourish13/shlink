package io.shlink.urlservice.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "urls")
public class Url {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	@Column(name = "url_code", unique = true, nullable = false)
	private String urlCode;
	@Column(name = "long_url", nullable = false)
	private String longUrl;
	@Column(name = "created_at", nullable = false)
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