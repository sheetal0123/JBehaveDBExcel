package com.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import com.utilities.DBUtilities;

public class SampleSteps {
	@Given("I am connected to database using <user_name> and password <user_password>")
	public void ConnectToDatabase1(@Named("user_name") String user_name,
			@Named("user_password") String user_password) {
		DBUtilities.getConnection(user_name, user_password);
	}

	@Then("I logged in database <database_name> and connected to table name <table_name>")
	public void FetchDataAndWriteIntoExcel(
			@Named("database_name") String database_name,
			@Named("table_name") String table_name) {
		DBUtilities.getDataAndWriteExcel(database_name, table_name);
	}

}
