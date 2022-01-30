package com.devopsusach2020;

import com.devopsusach2020.rest.RestData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevOpsUsach2020ApplicationTests {

	@Autowired
	private RestData controller;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(controller, "controller no puede ser null");
	}

}
