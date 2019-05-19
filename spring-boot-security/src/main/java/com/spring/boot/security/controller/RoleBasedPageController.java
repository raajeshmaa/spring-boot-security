package com.spring.boot.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RoleBasedPageController {

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/admin")
	public String getAdminPage() {
		return "admin page is redirected";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/users")
	public String getUserPage() {
		return "user page is redirected";
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping(value = "/author")
	public String getAuthorPage() {
		return "author page is redirected";
	}

}
