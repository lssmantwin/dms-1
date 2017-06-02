package com.dms.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.dms.dto.FinanceDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.dms.constant.DmsConstant;
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;

public class FinanceExportXls implements StreamSource {

	private Workbook wb;
	private Sheet sheet;
	private List<FinanceDto> financeDtos;

	public FinanceExportXls(List<FinanceDto> financeDtos) {
		this.financeDtos = financeDtos;
		wb = new HSSFWorkbook();
		sheet = wb.createSheet();
		wb.setSheetName(0, "财务管理");
	}

	@Override
	public InputStream getStream() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		createHeader();
		createBody();

		wb.write(out);
		out.close();

		return new ByteArrayInputStream(out.toByteArray());
	}

	private void createHeader() {
		int cellNumber = 0;
		Row header = sheet.createRow(0);
		for (String name : DmsConstant.financeHeader) {
			Cell cell = header.createCell(cellNumber++);
			cell.setCellValue(name);
		}
	}

	private void createBody() {
		int rowNumber = 1;
		for (FinanceDto dto : financeDtos) {
			Row row = sheet.createRow(rowNumber++);

			int cellNumber = 0;
			Cell cell1 = row.createCell(cellNumber++);
			cell1.setCellValue(dto.getEmployeeName());

			Cell cell2 = row.createCell(cellNumber++);
			cell2.setCellValue(dto.getIdentityCardNumber());

			Cell cell3 = row.createCell(cellNumber++);
			cell3.setCellValue(dto.getBeforeTaxSalary() == null ? StringUtils.EMPTY : dto.getBeforeTaxSalary().toString());

			Cell cell4 = row.createCell(cellNumber++);
			cell4.setCellValue(dto.getPersonalIncomeTax() == null ? StringUtils.EMPTY : dto.getPersonalIncomeTax().toString());

			Cell cell5 = row.createCell(cellNumber++);
			cell5.setCellValue(dto.getAfterTaxSalary() == null ? StringUtils.EMPTY : dto.getAfterTaxSalary().toString());

		}
	}

}
