package com.turchanovskyi.virtual_university.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "materials")
public class Material implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "material_id")
	private Long material_id;

	@NotEmpty(message = "Title cannot be empty")
	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(name = "file")
	private Set<File> fileSet = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	public Material() {
	}

	public Material(Long material_id) {
		this.material_id = material_id;
	}

	public Material(String title, String description, Set<File> fileSet) {
		this.title = title;
		this.description = description;
		this.fileSet = fileSet;
	}

	public Material(@NotEmpty(message = "Title cannot be empty") String title, String description) {
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

	public Set<File> getFileSet() {
		return fileSet;
	}

	public void setFileSet(Set<File> fileSet) {
		this.fileSet = fileSet;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
	this.course = course;
	}
}
