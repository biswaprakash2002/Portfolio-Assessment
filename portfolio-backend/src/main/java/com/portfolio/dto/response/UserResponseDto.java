package com.portfolio.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import com.portfolio.dto.Status;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String headline;
    private String bio;
    private String profileImageUrl;
    private String contactNumber;
    private String location;
    private Boolean emailVerified;
    private Status accountStatus;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
//    
//    // getters & setters 
//    
//    public UserResponseDto() {}
//
//	public UserResponseDto(Long id, String username, String email, String fullName, String headline, String bio,
//			String profileImageUrl, String contactNumber, String location, Boolean emailVerified, Status accountStatus,
//			List<String> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.fullName = fullName;
//		this.headline = headline;
//		this.bio = bio;
//		this.profileImageUrl = profileImageUrl;
//		this.contactNumber = contactNumber;
//		this.location = location;
//		this.emailVerified = emailVerified;
//		this.accountStatus = accountStatus;
//		this.roles = roles;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getFullName() {
//		return fullName;
//	}
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//	public String getHeadline() {
//		return headline;
//	}
//	public void setHeadline(String headline) {
//		this.headline = headline;
//	}
//	public String getBio() {
//		return bio;
//	}
//	public void setBio(String bio) {
//		this.bio = bio;
//	}
//	public String getProfileImageUrl() {
//		return profileImageUrl;
//	}
//	public void setProfileImageUrl(String profileImageUrl) {
//		this.profileImageUrl = profileImageUrl;
//	}
//	public String getContactNumber() {
//		return contactNumber;
//	}
//	public void setContactNumber(String contactNumber) {
//		this.contactNumber = contactNumber;
//	}
//	public String getLocation() {
//		return location;
//	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
//	public Boolean getEmailVerified() {
//		return emailVerified;
//	}
//	public void setEmailVerified(Boolean emailVerified) {
//		this.emailVerified = emailVerified;
//	}
//	public Status getAccountStatus() {
//		return accountStatus;
//	}
//	public void setAccountStatus(Status accountStatus) {
//		this.accountStatus = accountStatus;
//	}
//	public List<String> getRoles() {
//		return roles;
//	}
//	public void setRoles(List<String> roles) {
//		this.roles = roles;
//	}
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//	
//	@Override
//	public String toString() {
//		return "UserResponseDto [id=" + id + ", username=" + username + ", email=" + email + ", fullName=" + fullName
//				+ ", headline=" + headline + ", bio=" + bio + ", profileImageUrl=" + profileImageUrl
//				+ ", contactNumber=" + contactNumber + ", location=" + location + ", emailVerified=" + emailVerified
//				+ ", accountStatus=" + accountStatus + ", roles=" + roles + ", createdAt=" + createdAt + ", updatedAt="
//				+ updatedAt + "]";
//	}
//    
	
}