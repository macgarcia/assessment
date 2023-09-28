package com.axreng.backend;

import static spark.Spark.get;
import static spark.Spark.post;

import com.axreng.backend.service.ApplicationService;

public class Main {
	public static void main(String[] args) {

		ApplicationService service = new ApplicationService();

		get("/crawl/:id", (req, res) -> service.get(req.params("id")));

		post("/crawl", (req, res) -> service.startLooking(req.body()));
	}
}
