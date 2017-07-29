package com.dms.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.dms.constant.DmsConstant;
import com.dms.dto.SalaryBill;
import com.dms.enums.CompanyEnum;

public class FinanceExportXls implements StreamSource {

	private Workbook wb;
	private Sheet sheet1;
	private Sheet sheet2;
	private List<SalaryBill> bills;

	public FinanceExportXls(List<SalaryBill> bills) {

		this.bills = bills;

		wb = new HSSFWorkbook();

		sheet1 = wb.createSheet();
		sheet2 = wb.createSheet();

		wb.setSheetName(0, "员工工资");
		wb.setSheetName(1, "工资条");
	}

	@Override
	public InputStream getStream() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		buildSheet1();
		buildSheet2();

		wb.write(out);
		out.close();

		return new ByteArrayInputStream(out.toByteArray());
	}

	private void buildSheet1() {
		createEmployeeWageHeader();
		createEmployeeWageBody();
	}

	private void createEmployeeWageHeader() {
		int column = 0;
		Row header = sheet1.createRow(0);
		for (String name : DmsConstant.employeeWageHeader) {
			Cell cell = header.createCell(column++);
			cell.setCellValue(name);
		}
	}

	private void createEmployeeWageBody() {
		int rowNumber = 1;
		for (SalaryBill bill : bills) {
			Row row = sheet1.createRow(rowNumber++);

			int cellNumber = 0;

			Cell cell1 = row.createCell(cellNumber++);
			cell1.setCellValue(CompanyEnum.fromDbConstant(bill.getCompanyId()).getText());

			Cell cell2 = row.createCell(cellNumber++);
			cell2.setCellValue(bill.getEmployeeName());

			Cell cell3 = row.createCell(cellNumber++);
			cell3.setCellValue(bill.getIdentityCardNumber());

			Cell cell4 = row.createCell(cellNumber++);
			cell4.setCellValue(bill.getBankCardNumber());

			Cell cell5 = row.createCell(cellNumber++);
			cell5.setCellValue(bill.getBeforeTaxSalary() == null ? StringUtils.EMPTY : bill.getBeforeTaxSalary().toString());

			Cell cell6 = row.createCell(cellNumber++);
			cell6.setCellValue(bill.getPersonalIncomeTax() == null ? StringUtils.EMPTY : bill.getPersonalIncomeTax().toString());

			Cell cell7 = row.createCell(cellNumber++);
			cell7.setCellValue(bill.getAfterTaxSalary() == null ? StringUtils.EMPTY : bill.getAfterTaxSalary().toString());

			Cell cell8 = row.createCell(cellNumber++);
			cell8.setCellValue(bill.getSalaryCash() == null ? StringUtils.EMPTY : bill.getSalaryCash().toString());

		}
	}

	private void buildSheet2() {
		int row = 0;
		for (SalaryBill bill : bills) {
			createSalaryBillHeader(row++);
			createSalaryBillBody(bill, row++);
		}
	}

	private void createSalaryBillHeader(int row) {
		int column = 0;
		Row header = sheet2.createRow(row);
		for (String name : DmsConstant.salaryBillHeader) {
			Cell cell = header.createCell(column++);
			cell.setCellValue(name);
		}
	}

	private void createSalaryBillBody(SalaryBill bill, int row) {

		Row body = sheet2.createRow(row);

		int column = 0;

		Cell cell1 = body.createCell(column++);
		cell1.setCellValue(CompanyEnum.fromDbConstant(bill.getCompanyId()).getText());

		Cell cell36 = body.createCell(column++);
		cell36.setCellValue(bill.getDepartment());

		Cell cell2 = body.createCell(column++);
		cell2.setCellValue(bill.getMonth());

		Cell cell3 = body.createCell(column++);
		cell3.setCellValue(bill.getEmployeeName());

		Cell cell4 = body.createCell(column++);
		cell4.setCellValue(bill.getPosition());

		Cell cell5 = body.createCell(column++);
		cell5.setCellValue(bill.getHiredate() == null ? StringUtils.EMPTY : bill.getHiredate().toString("yyyy.MM.dd"));

		Cell cell6 = body.createCell(column++);
		cell6.setCellValue(bill.getBaseWage() == null ? StringUtils.EMPTY : bill.getBaseWage().toString());

		Cell cell7 = body.createCell(column++);
		cell7.setCellValue(bill.getOtherSubsidyCard() == null ? StringUtils.EMPTY : bill.getOtherSubsidyCard().toString());

		Cell cell8 = body.createCell(column++);
		cell8.setCellValue(bill.getMealsSubsidy() == null ? StringUtils.EMPTY : bill.getMealsSubsidy().toString());

		Cell cell9 = body.createCell(column++);
		cell9.setCellValue(bill.getSecrecySubsidy() == null ? StringUtils.EMPTY : bill.getSecrecySubsidy().toString());

		Cell cell10 = body.createCell(column++);
		cell10.setCellValue(bill.getBonusCard() == null ? StringUtils.EMPTY : bill.getBonusCard().toString());

		Cell cell11 = body.createCell(column++);
		cell11.setCellValue(bill.getWorkingAgeSubsidy() == null ? StringUtils.EMPTY : bill.getWorkingAgeSubsidy().toString());

		Cell cell12 = body.createCell(column++);
		cell12.setCellValue(bill.getPerformanceAppraisalCard() == null ? StringUtils.EMPTY : bill.getPerformanceAppraisalCard().toString());

		Cell cell13 = body.createCell(column++);
		cell13.setCellValue(bill.getCommunicationFee() == null ? StringUtils.EMPTY : bill.getCommunicationFee().toString());

		Cell cell15 = body.createCell(column++);
		cell15.setCellValue(bill.getCharge() == null ? StringUtils.EMPTY : bill.getCharge().toString());

		Cell cell16 = body.createCell(column++);
		cell16.setCellValue(bill.getExhibitionCharge() == null ? StringUtils.EMPTY : bill.getExhibitionCharge().toString());

		Cell cell17 = body.createCell(column++);
		cell17.setCellValue(bill.getCasualLeave() == null ? StringUtils.EMPTY : bill.getCasualLeave().toString());

		Cell cell18 = body.createCell(column++);
		cell18.setCellValue(bill.getSickLeave() == null ? StringUtils.EMPTY : bill.getSickLeave().toString());

		Cell cell19 = body.createCell(column++);
		cell19.setCellValue(bill.getStorage() == null ? StringUtils.EMPTY : bill.getStorage().toString());

		Cell cell20 = body.createCell(column++);
		cell20.setCellValue(bill.getGrossPay() == null ? StringUtils.EMPTY : bill.getGrossPay().toString());

		Cell cell21 = body.createCell(column++);
		cell21.setCellValue(bill.getMedicalInsurance() == null ? StringUtils.EMPTY : bill.getMedicalInsurance().toString());

		Cell cell22 = body.createCell(column++);
		cell22.setCellValue(bill.getHousingFund() == null ? StringUtils.EMPTY : bill.getHousingFund().toString());

		Cell cell23 = body.createCell(column++);
		cell23.setCellValue(bill.getBeforeTaxSalary() == null ? StringUtils.EMPTY : bill.getBeforeTaxSalary().toString());

		Cell cell24 = body.createCell(column++);
		cell24.setCellValue(bill.getPersonalIncomeTax() == null ? StringUtils.EMPTY : bill.getPersonalIncomeTax().toString());

		Cell cell25 = body.createCell(column++);
		cell25.setCellValue(bill.getAfterTaxSalary() == null ? StringUtils.EMPTY : bill.getAfterTaxSalary().toString());

		// Cell cell26 = body.createCell(column++);
		// cell26.setCellValue(bill.getBankCardNumber());

		// add blank column
		Cell cell27 = body.createCell(column++);
		cell27.setCellValue(StringUtils.EMPTY);

		Cell cell28 = body.createCell(column++);
		cell28.setCellValue(bill.getPostAllowance() == null ? StringUtils.EMPTY : bill.getPostAllowance().toString());

		Cell cell29 = body.createCell(column++);
		cell29.setCellValue(bill.getPerformanceAppraisalCash() == null ? StringUtils.EMPTY : bill.getPerformanceAppraisalCash().toString());

		Cell cell30 = body.createCell(column++);
		cell30.setCellValue(bill.getOtherSubsidyCash() == null ? StringUtils.EMPTY : bill.getOtherSubsidyCash().toString());

		Cell cell31 = body.createCell(column++);
		cell31.setCellValue(bill.getBonusCash() == null ? StringUtils.EMPTY : bill.getBonusCash().toString());

		// TODO 翻新保护费
		Cell cell32 = body.createCell(column++);
		cell32.setCellValue(StringUtils.EMPTY);

		Cell cell33 = body.createCell(column++);
		cell33.setCellValue(bill.getOtherCharge() == null ? StringUtils.EMPTY : bill.getOtherCharge().toString());

		// TODO 展会最后一名扣款
		Cell cell34 = body.createCell(column++);
		cell34.setCellValue(StringUtils.EMPTY);

		Cell cell35 = body.createCell(column++);
		cell35.setCellValue(bill.getSalaryCash() == null ? StringUtils.EMPTY : bill.getSalaryCash().toString());
	}

}
