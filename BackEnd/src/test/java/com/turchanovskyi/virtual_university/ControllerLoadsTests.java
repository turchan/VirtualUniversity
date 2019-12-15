package com.turchanovskyi.virtual_university;

import com.turchanovskyi.virtual_university.controllers.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerLoadsTests {

	@Autowired
	private AuthController authController;

	@Autowired
	private CourseController courseController;

	@Autowired
	private MarkController markController;

	@Autowired
	private MaterialController materialController;

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() throws Exception {
		Assertions.assertThat(courseController).isNotNull();
		Assertions.assertThat(authController).isNotNull();
		Assertions.assertThat(markController).isNotNull();
		Assertions.assertThat(materialController).isNotNull();
		Assertions.assertThat(userController).isNotNull();
	}
}
