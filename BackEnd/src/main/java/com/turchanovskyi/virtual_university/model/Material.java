package com.turchanovskyi.virtual_university.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "materials")
public class Material implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "material_id")
	private Long material_id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	public Material() {
	}

	public Material(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Long getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(Long material_id) {
		this.material_id = material_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
