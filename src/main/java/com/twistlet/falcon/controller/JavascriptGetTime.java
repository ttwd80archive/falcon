package com.twistlet.falcon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JavascriptGetTime {

	@RequestMapping("/js/lib/getTime.js")
	public void getTime(@RequestParam("elem") final String elementIdSuffix, final HttpServletResponse response) {
		final String content = generateContent(elementIdSuffix);
		response.setContentType("text/javascript");
		response.setContentLength(content.length());
		try {
			final PrintWriter out = response.getWriter();
			out.print(content);
			out.flush();
			out.close();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String generateContent(final String elementIdSuffix) {
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("h:mm aa - EEEE, MMMMM dd, yyyy");
		final String dateString = sdf.format(date);
		final StringBuilder sb = new StringBuilder();
		sb.append("var tzTimeSpan = document.getElementById('tzTimeSpan_" + elementIdSuffix + "');");
		sb.append("\n");
		sb.append("if (tzTimeSpan != null) {");
		sb.append("\n");
		sb.append("\n\t tzTimeSpan.innerHTML= '" + dateString + "';");
		sb.append("\n");
		sb.append("}");
		sb.append("\n");
		sb.append("var currentTime = new CurrentTime(document.getElementById(\"tzTimeSpan_" + elementIdSuffix
				+ "\").childNodes[0], 1.37381461E+12, CurrentTime.F12);");
		sb.append("\n");
		sb.append("currentTime.start();");
		sb.append("\n");
		return sb.toString();
	}
}
