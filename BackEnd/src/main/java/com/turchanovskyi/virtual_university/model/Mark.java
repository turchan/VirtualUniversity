package com.turchanovskyi.virtual_university.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "marks")
public class Mark implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mark_id")
	private Long mark_id;

	@Column(name = "title")
	private String title;

	@Column(name = "mark")
	private int mark;

	@JsonIgnoreProperties({"coursesList", "markList"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnoreProperties("userList")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	public Mark() {
	}

	public Mark(Long mark_id, String title, int mark, User user, Course course) {
		this.mark_id = mark_id;
		this.title = title;
		this.mark = mark;
		this.user = user;
		this.course = course;
	}

	public Long getMark_id() {
		return mark_id;
	}

	public void setMark_id(Long mark_id) {
		this.mark_id = mark_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
