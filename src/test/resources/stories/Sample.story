Sample story

Narrative:
In order to copy data from MySQL and save it into MS Excel
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  exporting data into excel
Given I am connected to database using <user_name> and password <user_password>
Then I logged in database <database_name> and connected to table name <table_name>

Examples:
|user_name  	|user_password	|database_name	|table_name	 |
|root			|xebia@123		|xebia		  	|demotable	 |
|root	 	    |xebia@123		|InvalidDatabase|InvalidTable|
|InvalidUserName|InvalidPassword|xebia			|demotable	 |