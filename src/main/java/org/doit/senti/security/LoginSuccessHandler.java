package org.doit.senti.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("loginSuccessHandler")
@Log4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.warn("> Login Success...");

		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(auth -> {
			roleNames.add(auth.getAuthority());
		});

		log.warn("> ROLE NAMES : " + roleNames);

		if (roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/main.do");
		} else if (roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/main.do");
		} else {
			response.sendRedirect("redirect:/signUp/login.do?logout=true");
		}
	}
}
