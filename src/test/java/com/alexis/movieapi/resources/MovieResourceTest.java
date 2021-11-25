package com.alexis.movieapi.resources;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MovieResourceTest {
	
	// ATENÇÃO necessário altetrar no RunConfiguration da classe para JUnit 4
	@Test
	public void testaRequisicaoMinMaxInterval() throws Exception {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/movies";
		
		when()
			.get("/min-max-interval")
			.then()
			.log().all()
			.statusCode(200)
			// min
			.body("min[0].name", is("Joel Silver"))
			.body("min[0].interval", is(1))
			.body("min[0].previousWin", is("1990"))
			.body("min[0].followingWin", is("1991"))
			// max
			.body("max[0].name", is("Matthew Vaughn"))
			.body("max[0].interval", is(13))
			.body("max[0].previousWin", is("2002"))
			.body("max[0].followingWin", is("2015"))
			;
	}
}
