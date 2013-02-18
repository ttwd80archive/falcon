package com.twistlet.falcon.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.falcon.security.service.SecurityContextService;

@Controller
public class MainController {

	private final SecurityContextService securityContextService;
	private final Map<String, String> map;

	@Autowired
	public MainController(final SecurityContextService securityContextService,
			@Qualifier("welcomePageMap") final Map<String, String> map) {
		this.securityContextService = securityContextService;
		this.map = Collections.unmodifiableMap(map);
	}

	@RequestMapping("/main")
	public ModelAndView main() {
		final List<GrantedAuthority> list = securityContextService
				.getAuthorities();
		final List<String> grantedAuthorityList = new ArrayList<>();
		for (final GrantedAuthority grantedAuthority : list) {
			grantedAuthorityList.add(grantedAuthority.toString());
		}
		final Set<String> roleSet = map.keySet();
		for (final String role : roleSet) {
			if (grantedAuthorityList.contains(role)) {
				final String view = map.get(role);
				final ModelAndView mav = new ModelAndView(view);
				return mav;
			}
		}
		return null;

	}
}
