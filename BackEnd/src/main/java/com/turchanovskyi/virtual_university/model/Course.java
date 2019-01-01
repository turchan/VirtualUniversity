package com.turchanovskyi.virtual_university.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long course_id;

	@Column(name = "title")
	private String title;

	@Column(name = "password")
	private String password;

	@Column(name = "professor")
	private String professor;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Material> materialList = new ArrayList<>();

	@Column(name = "creator")
	private String creator;

	@JsonIgnoreProperties({"courserList", "markList"})
	@ManyToMany(mappedBy = "coursesList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> userList = new ArrayList<>();

	@JsonIgnoreProperties("course")
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Mark> markList = new ArrayList<>();

	public Course() {
	}

	public Course(String title, String password, String professor, String description, String creator) {
		this.title = title;
		this.password = password;
		this.professor = professor;
		this.description = description;
		this.creator = creator;
	}

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<Mark> getMarkList() {
		return markList;
	}

	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
