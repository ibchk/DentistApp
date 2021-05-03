package com.cgi.dentistapp;

import com.cgi.dentistapp.dto.ShowDentistVisitDTO;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DentistAppApplicationTests extends RestTemplateTests {

	@Test
	public void contextLoads() {
	}

}
