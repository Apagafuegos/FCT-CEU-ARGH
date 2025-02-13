package com.arg.fct.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {
	@NotBlank
	@NotNull
	private Long userId;
	@NotNull
	@Size(min = 8, max = 100)
	@NotBlank(message = "La antigua contraseña es obligatoria")
	private String oldPassword;
	@NotNull
	@Size(min = 8, max = 100)
	@NotBlank(message = "La nueva contraseña es obligatoria")
	private String newPassword;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
