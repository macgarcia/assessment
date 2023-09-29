package com.axreng.backend.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlProcessor.class);

	private final String URL_TO_SEARCH = System.getenv("BASE_URL");

	public void findDataByTerm(String term, ControllerRequest controllerRequest) {
		try {			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_TO_SEARCH)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			final String content = response.body();
			
			Pattern pattern = Pattern.compile("\\bhttps?://\\S*?" + term + "\\S*\\b");
			Matcher matcher = pattern.matcher(content);
			
			while(matcher.find()) {
				final String link = matcher.group();
				controllerRequest.updateRequest(Thread.currentThread().getName(), link);
			}
			controllerRequest.finishRequest(Thread.currentThread().getName());
		} catch (Exception e) {
			LOGGER.error("Invalid URL");
		}
	}
}
