package com.spring.boot.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.spring.boot.security.vo.AuthToken;
import com.spring.boot.security.vo.LoginUser;

@RunWith(SpringRunner.class)
//@EnableJpaRepositories(basePackages = { "com.spring.boot.security.repository" })
@SpringBootTest(classes = SpringBootSecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootSecurityApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void getAdminPageTest() {
		String token = getAuthentication("admin", "vinay");
		assertNotNull(token);
		String resp = invokeServicesWithToken(token, "/admin");
		assertNotNull(resp);
		assertEquals("admin page is redirected", resp);

	}

	@Test
	public void getUserPageTest() {
		String token = getAuthentication("user", "vinay");
		assertNotNull(token);
		String resp = invokeServicesWithToken(token, "/users");
		assertNotNull(resp);
		assertEquals("user page is returned.", "user page is redirected", resp);
	}

	@Test
	public void getAuthorPageTest() {
		String token = getAuthentication("author", "vinay");
		assertNotNull(token);
		String resp = invokeServicesWithToken(token, "/author");
		assertNotNull(resp);
		assertEquals("author page is returned.", "author page is redirected", resp);

	}

	private String invokeServicesWithToken(String token, String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String invokeRespApi = invokeRespApi(url, HttpMethod.GET, entity);
		String response = null;
		if (invokeRespApi != null) {
			response = invokeRespApi;
		}
		return response;
	}

	private String getAuthentication(String userName, String password) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUsername(userName);
		loginUser.setPassword(password);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LoginUser> entity = new HttpEntity<LoginUser>(loginUser, headers);

		String invokeRespApi = invokeRespApi("public/login", HttpMethod.POST, entity);
		String response = null;
		if (invokeRespApi != null) {
			Gson gson = new Gson();
			response = gson.fromJson(invokeRespApi, AuthToken.class).getToken();
		}
		return response;

	}

	private String invokeRespApi(String url, HttpMethod httpMethod, HttpEntity<?> requestEntity) {
		String response = null;
		try {
			ResponseEntity<String> exchange = restTemplate.exchange(createURLWithPort(url), httpMethod, requestEntity,
					String.class);
			System.out.println("restful service status " + exchange.getStatusCode());
			if (exchange.getStatusCode() == HttpStatus.OK) {
				response = exchange.getBody();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
