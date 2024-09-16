package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.model.jobs.JobDetailedDTO;
import com.licnosoft.linkedin.model.network.UserConnectionDTO;
import com.licnosoft.linkedin.model.posts.CommentDTO;
import com.licnosoft.linkedin.model.posts.LikeDTO;
import com.licnosoft.linkedin.model.posts.PostDetailedDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "user")
public class UserDetailedDTO extends UserBasicDTO {

	protected List<UserConnectionDTO> connections;
	protected List<LikeDTO> likes;
	protected List<CommentDTO> comments;

	protected List<PostDetailedDTO> posts;

	protected List<JobDetailedDTO> jobs;
	@XmlElementWrapper(name = "connections")
	@XmlElement(name = "connection")
	public List<UserConnectionDTO> getConnections() {return connections;}

	public void setConnections(List<UserConnectionDTO> connections) {this.connections = connections;}
	@XmlElementWrapper(name = "likes")
	@XmlElement(name = "like")
	public List<LikeDTO> getLikes() {return likes;}

	public void setLikes(List<LikeDTO> likes) {this.likes = likes;}
	@XmlElementWrapper(name = "comments")
	@XmlElement(name = "comment")
	public List<CommentDTO> getComments() {return comments;}

	public void setComments(List<CommentDTO> comments) {this.comments = comments;}
	@XmlElementWrapper(name = "posts")
	@XmlElement(name = "post")
	public List<PostDetailedDTO> getPosts() {return posts;}

	public void setPosts(List<PostDetailedDTO> posts) {this.posts = posts;}
	@XmlElementWrapper(name = "jobs")
	@XmlElement(name = "job")
	public List<JobDetailedDTO> getJobs() {return jobs;}

	public void setJobs(List<JobDetailedDTO> jobs) {this.jobs = jobs;}
}
