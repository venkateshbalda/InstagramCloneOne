package com.venkyb.instagramone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="posts", schema = "public")
public class posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="postid")
	private int postid;
	@Column(name="userid")
	private int userid;
	@Column(name="username")
	private String username;
	@Column(name="likes")
	private int likes;
	@Column(name="likedby")
	private String likedby;
	public String getLikedby() {
		return likedby;
	}
	public void setLikedby(String likedby) {
		this.likedby = likedby;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="description")
	private String description;
	@Column(name="timestamp")
	private String timestamp;
	@Column(name="imageurl")
	private String imageurl;
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestanp) {
		this.timestamp = timestanp;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Override
	public String toString() {
		return "posts [postid=" + postid + ", userid=" + userid + ", description=" + description + ", timestanp="
				+ timestamp + ", imageurl=" + imageurl + "]";
	}
}
