package com.mert.loanapp.client.dto.response;

import java.util.Date;
import java.util.Objects;

public class BaseResponseDto {

	private Date createdAt;
	
	private Date updatedAt;

	public BaseResponseDto() {}

	public BaseResponseDto(Date createdAt, Date updatedAt) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseResponseDto other = (BaseResponseDto) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "BaseResponseDto [createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
