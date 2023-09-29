package com.axreng.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.axreng.backend.model.ResponseModel;
import com.axreng.backend.model.Status;
import com.axreng.backend.service.ApplicationService;
import com.google.gson.Gson;

public class MainTest {

	@Test
	void testOnVariableOfSystemNotNull() {
		String getenv = System.getenv("BASE_URL");
		assertNotNull(getenv);
	}

	@Test
	void testOnProcessPostFailKeywordWith0Caracters() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"\"}";
		assertThrows(IllegalArgumentException.class, () -> service.startLooking(jsonPost));
	}

	@Test
	void testOnProcessPostFailKeywordShorterThan4Caracters() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"gnu\"}";
		assertThrows(IllegalArgumentException.class, () -> service.startLooking(jsonPost));
	}

	@Test
	void testOnProcessPostFailKeywordLongerThan32Caracters() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"sdfasdfasdfasdfasdgaewqerqwerwerqwerqwerqwerqwerqwerqwerqwereqw\"}";
		assertThrows(IllegalArgumentException.class, () -> service.startLooking(jsonPost));
	}

	@Test
	void testOnProcessPostSucecss() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"kernel\"}";
		String responseBody = service.startLooking(jsonPost);
		assertNotNull(responseBody);
	}

	@Test
	void testOnProcessPostSuccessKeywordWith4Caracters() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"gnud\"}";
		String responseBody = service.startLooking(jsonPost);
		assertNotNull(responseBody);
	}

	@Test
	void testOnProcessPostSuccessKeywordWith32Caracters() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"anticonstitucionalissimamente\"}";
		String responseBody = service.startLooking(jsonPost);
		assertNotNull(responseBody);
	}

	@Test
	void testOnProcessGet() {
		ApplicationService service = new ApplicationService();
		String jsonPost = "{\"keyword\": \"gnud\"}";
		String responseBody = service.startLooking(jsonPost);
		String justId = getId(responseBody);
		String response = service.get(justId);
		ResponseModel fromJson = new Gson().fromJson(response, ResponseModel.class);
		assertEquals(fromJson.getStatus(), Status.ACTIVE.getValue());
	}

	private String getId(String json) {
		// Encontre a posição do início do campo "id"
		int startIndex = json.indexOf("\"id\":\"") + 6;

		// Encontre a posição do fim do valor do campo "id"
		int endIndex = json.indexOf("\"", startIndex);

		// Extraia o valor do campo "id"
		return json.substring(startIndex, endIndex);
	}

}
