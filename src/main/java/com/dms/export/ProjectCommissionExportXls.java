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
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;
import com.dms.enums.ContractStateEnum;

public class ProjectCommissionExportXls implements StreamSource {

	private Workbook wb;
	private Sheet sheet;
	private List<ProjectCommissionDto> projectCommissionDtos;

	public ProjectCommissionExportXls(List<ProjectCommissionDto> projectCommissionDtos) {
		this.projectCommissionDtos = projectCommissionDtos;
		wb = new HSSFWorkbook();
		sheet = wb.createSheet();
		wb.setSheetName(0, "工程佣金");
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
		for (String name : DmsConstant.projectCommissionHeader) {
			Cell cell = header.createCell(cellNumber++);
			cell.setCellValue(name);
		}
	}

	private void createBody() {
		int rowNumber = 1;
		for (ProjectCommissionDto dto : projectCommissionDtos) {
			Row row = sheet.createRow(rowNumber++);

			int cellNumber = 0;
			Cell cell1 = row.createCell(cellNumber++);
			cell1.setCellValue(dto.getDesigner());

			Cell cell2 = row.createCell(cellNumber++);
			cell2.setCellValue(dto.getDesignerAssistant());

			Cell cell3 = row.createCell(cellNumber++);
			cell3.setCellValue(dto.getAcNumber());

			Cell cell4 = row.createCell(cellNumber++);
			cell4.setCellValue(dto.getCustomerName());

			Cell cell5 = row.createCell(cellNumber++);
			cell5.setCellValue(dto.getContractTotal() == null ? StringUtils.EMPTY : dto.getContractTotal().toString());

			Cell cell6 = row.createCell(cellNumber++);
			cell6.setCellValue(dto.getPurchaseAgentFee() == null ? StringUtils.EMPTY : dto.getPurchaseAgentFee().toString());

			Cell cell7 = row.createCell(cellNumber++);
			cell7.setCellValue(dto.getProjectChangeTotal() == null ? StringUtils.EMPTY : dto.getProjectChangeTotal().toString());

			Cell cell8 = row.createCell(cellNumber++);
			cell8.setCellValue(dto.getCustomerPay() == null ? StringUtils.EMPTY : dto.getCustomerPay().toString());

			Cell cell9 = row.createCell(cellNumber++);
			cell9.setCellValue(dto.getPayContractRatio() == null ? StringUtils.EMPTY : dto.getPayContractRatio().toString());

			Cell cell10 = row.createCell(cellNumber++);
			cell10.setCellValue(dto.getPayProjectRatio() == null ? StringUtils.EMPTY : dto.getPayProjectRatio().toString());

			Cell cell11 = row.createCell(cellNumber++);
			cell11.setCellValue(StringUtils.isBlank(dto.getContractState()) ? StringUtils.EMPTY
					: ContractStateEnum.fromDbConstant(Integer.parseInt(dto.getContractState())).getText());

			Cell cell12 = row.createCell(cellNumber++);
			cell12.setCellValue(dto.getCommissionState() == 0 ? StringUtils.EMPTY : CommissionStateEnum.fromDbConstant(dto.getCommissionState()).getText());

			Cell cell13 = row.createCell(cellNumber++);
			cell13.setCellValue(dto.getFirstCommission() == null ? StringUtils.EMPTY : dto.getFirstCommission().toString());

			Cell cell14 = row.createCell(cellNumber++);
			cell14.setCellValue(dto.getBalanceCommission() == null ? StringUtils.EMPTY : dto.getBalanceCommission().toString());

			Cell cell15 = row.createCell(cellNumber++);
			cell15.setCellValue(dto.getDesignCommissionRate() == null ? StringUtils.EMPTY : dto.getDesignCommissionRate().toString());

			Cell cell16 = row.createCell(cellNumber++);
			cell16.setCellValue(dto.getContractDate() == null ? StringUtils.EMPTY : dto.getContractDate().toString("yyyy-MM-dd"));
			Cell cell17 = row.createCell(cellNumber++);
			cell17.setCellValue(dto.getFirstCommissionDate() == null ? StringUtils.EMPTY : dto.getFirstCommissionDate().toString("yyyy-MM-dd"));

			Cell cell18 = row.createCell(cellNumber++);
			cell18.setCellValue(dto.getActualStartTime() == null ? StringUtils.EMPTY : dto.getActualStartTime().toString("yyyy-MM-dd"));

			Cell cell19 = row.createCell(cellNumber++);
			cell19.setCellValue(dto.getActualEndTime() == null ? StringUtils.EMPTY : dto.getActualEndTime().toString("yyyy-MM-dd"));
			Cell cell20 = row.createCell(cellNumber++);
			cell20.setCellValue(dto.getBalanceTime() == null ? StringUtils.EMPTY : dto.getBalanceTime().toString("yyyy-MM-dd"));

			Cell cell21 = row.createCell(cellNumber++);
			cell21.setCellValue(dto.getBalanceCommissionDate() == null ? StringUtils.EMPTY : dto.getBalanceCommissionDate().toString("yyyy-MM-dd"));

			Cell cell22 = row.createCell(cellNumber++);
			cell22.setCellValue(dto.getDesignerAssistantCommissionRate() == null ? StringUtils.EMPTY : dto.getDesignerAssistantCommissionRate().toString());

		}
	}

}
