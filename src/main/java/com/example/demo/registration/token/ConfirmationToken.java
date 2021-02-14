package com.example.demo.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;

import com.example.demo.appUser.AppUser;

import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
public class ConfirmationToken {
	
	
	@SequenceGenerator(
			name="confirmation_token_sequence",
			sequenceName="confirmation_token_sequence",
			allocationSize=1
		)
		@Id
		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator="confirmation_token_sequence"
			)

	private Long Id;
	
	@Column( nullable = false)
	private String token;
	
	@Column( nullable = false)
	private LocalDateTime createdAt;
	
	@Column( nullable = false)
	private LocalDateTime expriredAt;
	private LocalDateTime confirmedAt;
	
	
	@ManyToOne
	@JoinColumn(
			nullable=false,
			name="app_user_id"
		)
	private AppUser appUser;
	
	
	
	public ConfirmationToken() {
		super();
	}
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expriredAt,AppUser appUser) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expriredAt = expriredAt;
		this.appUser=appUser;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpriredAt() {
		return expriredAt;
	}
	public void setExpriredAt(LocalDateTime expriredAt) {
		this.expriredAt = expriredAt;
	}
	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}
	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
	
}

