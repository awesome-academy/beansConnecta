package vn.sun.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import vn.sun.entities.User;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = Logger.getLogger(MyAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) {
		HttpSession session = httpServletRequest.getSession();
		boolean hasCandidateRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals(User.role.CANDIDATE.toString()));
		session.setAttribute("currentUser", authentication.getName());
		session.setAttribute("isCandidate", hasCandidateRole);
		try {
			httpServletResponse.sendRedirect("/beanconnecta/");
		} catch (IOException e) {
			logger.error("Error in Authenication Handler: " + e.getMessage());
		}
	}
}
