package com.venkyb.instagramone.ServiceClass;

import org.springframework.stereotype.Service;

@Service
public class ServiceClass {
	private int CurrentUserId;
	private String CurrentUserName;
	
	public int getCurrentUserId() {
		return CurrentUserId;
	}
	public void setCurrentUserId(int currentUserId) {
		CurrentUserId = currentUserId;
	}
	public String getCurrentUserName() {
		return CurrentUserName;
	}
	public void setCurrentUserName(String currentUserName) {
		this.CurrentUserName = currentUserName;
	}
	@Override
	public String toString() {
		return "ServiceClass [CurrentUserId=" + CurrentUserId + ", CurrentUserName=" + CurrentUserName + "]";
	}
	
	public String getCurrentLoggedInUser() {
		String currentloggeduser="venky";
		return(currentloggeduser);
	}
	
}
