package com.example.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {


	@Test
	public void contextLoads() {
		String phone = "2535488888";
		System.out.println(phone.startsWith("2"));
	}
}
