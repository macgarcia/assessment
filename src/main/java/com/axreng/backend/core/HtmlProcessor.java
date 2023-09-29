package com.axreng.backend.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlProcessor {

	private final String URL_TO_SEARCH = System.getenv("BASE_URL");

	public List<String> findDataByTerm(String term) {
		try {
			List<String> result = new ArrayList<String>();
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_TO_SEARCH)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			final String content = response.body();
			
			Pattern pattern = Pattern.compile("\\bhttps?://\\S*?" + term + "\\S*\\b");
			Matcher matcher = pattern.matcher(content);
			
			while(matcher.find()) {
				final String link = matcher.group();
				result.add(link);
			}
			return result;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}
