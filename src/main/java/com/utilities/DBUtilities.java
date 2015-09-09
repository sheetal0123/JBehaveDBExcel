package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import junit.framework.Assert;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("deprecation")
public class DBUtilities {
	static Statement statement = null;
	static String outputFile = "C:\\Users\\sheetalsingh\\Desktop\\Xebia\\labs\\jbehaveoutput.xlsx";

	public static void getConnection(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect;
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", username, password);
			statement = connect.createStatement();
		} catch (ClassNotFoundException e) {
			Assert.fail("Class Not Found Exception coming");
			e.printStackTrace();
		} catch (SQLException e) {
			Assert.fail("SQL Exception coming");
			e.printStackTrace();
		}

	}

	public static void getDataAndWriteExcel(String db, String table) {
		ResultSet resultSet = null;
		String query = "SELECT * FROM " + db + "." + table;
		try {
			resultSet = statement.executeQuery(query);

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet("sheet1");
			XSSFRow row = spreadsheet.createRow(0);
			XSSFCell cell;
			cell = row.createCell(0);
			cell.setCellValue("EMP ID");
			cell = row.createCell(1);
			cell.setCellValue("EMP NAME");
			cell = row.createCell(2);
			cell.setCellValue("PROJECT");

			int i = 1;

			while (resultSet.next()) {
				row = spreadsheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(resultSet.getString("eid"));
				cell = row.createCell(1);
				cell.setCellValue(resultSet.getString("ename"));
				cell = row.createCell(2);
				cell.setCellValue(resultSet.getString("eproject"));
				i++;
			}

			FileOutputStream out = null;
			out = new FileOutputStream(new File(outputFile));

			workbook.write(out);
			workbook.close();
			out.close();
		} catch (FileNotFoundException e) {
			Assert.fail("File Not Found Exception coming");
			e.printStackTrace();
		} catch (SQLException e) {
			Assert.fail("SQL Exception coming");
			e.printStackTrace();
		} catch (IOException e) {
			Assert.fail("IO Exception coming");
			e.printStackTrace();
		}
		System.out.println("Data written successfully");
	}

}
