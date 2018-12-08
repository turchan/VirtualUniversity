package com.turchanovskyi.virtual_university.model;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {

	@Id
	@Column(name = "title_id")
	private String title_id;

	@Column(name = "mark")
	private int mark;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	private Course course;

	public Mark() {
	}

	public Mark(String title_id, int mark, User user, Course course) {
		this.title_id = title_id;
		this.mark = mark;
		this.user = user;
		this.course = course;
	}

	public String getTitle_id() {
		return title_id;
	}

	public void setTitle_id(String title_id) {
		this.title_id = title_id;
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
