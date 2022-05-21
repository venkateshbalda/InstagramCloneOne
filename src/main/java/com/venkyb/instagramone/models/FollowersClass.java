package com.venkyb.instagramone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="followers")
public class FollowersClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fid")
	private int fid;
	@Column(name="userid")
	private int userid;
	@Column(name="followingme")
	private String followingme;
	@Column(name="iamfollowing")
	private String iamfollowing;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFollowingme() {
		return followingme;
	}
	public void setFollowingme(String followingme) {
		this.followingme = followingme;
	}
	public String getIamfollowing() {
		return iamfollowing;
	}
	public void setIamfollowing(String iamfollowing) {
		this.iamfollowing = iamfollowing;
	}
	@Override
	public String toString() {
		return "FollowersClass [userid=" + userid + ", followingme=" + followingme + ", iamfollowing="
				+ iamfollowing + "]";
	}
	
}
