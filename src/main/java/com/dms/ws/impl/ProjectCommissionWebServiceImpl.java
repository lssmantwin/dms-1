package com.dms.ws.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.aspect.CheckAuthority;
import com.dms.dto.DesignAssistantDto;
import com.dms.dto.EnumDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;
import com.dms.enums.ContractStateEnum;
import com.dms.enums.EnumType;
import com.dms.enums.ResponseEnum;
import com.dms.export.ProjectCommissionExportXls;
import com.dms.request.ProjectCommissionFilterRequest;
import com.dms.response.DmsResponse;
import com.dms.service.ProjectCommissionService;
import com.dms.utils.FileFactory;
import com.dms.ws.ProjectCommissionWebService;
import com.google.common.collect.Lists;

@Service("projectCommissionWebService")
public class ProjectCommissionWebServiceImpl implements ProjectCommissionWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommissionWebServiceImpl.class);

	@Autowired
	private ProjectCommissionService projectCommissionService;

	@Override
	public Response export(String designer, String designerAssistant, String contractState, String commissionState, //
			String acNumber, String branch, String contractId, //
			String payContractRatio, String payProjectRatio, //
			String actualStartTime, String actualEndTime, String contractDate, //
			String firstCommissionDate, String balanceTime, String balanceCommissionDate, //
			String designerAssistantCommissionDate) {

		LOGGER.info("export project commission");

		ProjectCommissionFilterRequest request = generateProjectCommissionFilterRequest(designer, designerAssistant, contractState, //
				commissionState, acNumber, branch, contractId, payContractRatio, payProjectRatio, actualStartTime, //
				actualEndTime, contractDate, firstCommissionDate, balanceTime, balanceCommissionDate, //
				designerAssistantCommissionDate, 0, 0, null, null);

		List<ProjectCommissionDto> projectCommissions = projectCommissionService.getProjectCommissions(request);

		InputStream in = null;

		try {
			in = new ProjectCommissionExportXls(projectCommissions).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return FileFactory.getResponse(in, LocalDateTime.now().toString("yyyyMMddHHmmSS"));
	}

	@Override
	@CheckAuthority
	public DmsResponse getStates(String type) {
		DmsResponse response = new DmsResponse();
		List<EnumDto> result = Lists.newArrayList();
		if (EnumType.CONTRACT.toString().equals(type)) {
			result = Lists.newArrayList(ContractStateEnum.values()).stream().map(m -> new EnumDto(m.getDbConstant(), m.getText())).collect(Collectors.toList());
		} else if (EnumType.COMMISSION.toString().equals(type)) {
			result = Lists.newArrayList(CommissionStateEnum.values()).stream().map(m -> new EnumDto(m.getDbConstant(), m.getText()))
					.collect(Collectors.toList());
		}
		response.setCode(ResponseEnum.SUCCESS);
		response.setData(result);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) {

		LOGGER.info("save project commissions, {}", projectCommissionDtos);

		projectCommissionService.updateProjectCommissions(projectCommissionDtos);

		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse calculateFirstCommissions(List<ProjectCommissionDto> projectCommissionDtos) {
		LOGGER.info("Calculate First project commissions, {}", projectCommissionDtos);
		projectCommissionService.calculateFirstCommission(projectCommissionDtos);
		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse calculateBalanceCommissions(List<ProjectCommissionDto> projectCommissionDtos) {
		LOGGER.info("Calculate balance project commissions, {}", projectCommissionDtos);
		projectCommissionService.calculateBalanceCommission(projectCommissionDtos);
		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse updateDesignAssistant(String designAssistant) {
		System.out.println("design A" + designAssistant);
		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	@Override
	@CheckAuthority
	public DmsResponse getProjectCommissions(String designer, String designerAssistant, String contractState, String commissionState, //
			String acNumber, String branch, String contractId, //
			String payContractRatio, String payProjectRatio, //
			String actualStartTime, String actualEndTime, String contractDate, //
			String firstCommissionDate, String balanceTime, String balanceCommissionDate, //
			String designerAssistantCommissionDate, int pageIndex, int pageSize, String sortField, String sortOrder) {

		LOGGER.info(
				"get project commissions, designer {}, designerAssistant {}, contractState {}, acNumber {}, contractId {}, payContractRatio {}," + //
						" payProjectRatio {},  commissionState {}, actualStartTime {}, actualEndTime {}, contractDate {}, firstCommissionDate {}, " + //
						"balanceTime {}, balanceCommissionDate {}, pageIndex {}, pageSize {}, sortField {}, sortOrder {}", //
				designer, designerAssistant, contractState, commissionState, acNumber, contractId, payContractRatio, payProjectRatio, actualStartTime,
				actualEndTime, //
				contractDate, firstCommissionDate, balanceTime, balanceCommissionDate, designerAssistantCommissionDate, //
				pageIndex, pageSize, sortField, sortOrder);

		ProjectCommissionFilterRequest request = generateProjectCommissionFilterRequest(designer, designerAssistant, contractState, commissionState, //
				acNumber, branch, contractId, payContractRatio, payProjectRatio, actualStartTime, actualEndTime, contractDate, firstCommissionDate, balanceTime,
				balanceCommissionDate, designerAssistantCommissionDate, pageIndex, pageSize, sortField, sortOrder);

		int count = projectCommissionService.getProjectCommissionCount(request);
		List<ProjectCommissionDto> projectCommissions = projectCommissionService.getProjectCommissions(request);

		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		response.setTotal(count);
		response.setData(projectCommissions);

		return response;
	}

	private ProjectCommissionFilterRequest generateProjectCommissionFilterRequest(String designer, String designerAssistant, String contractState,
			String commissionState, //
			String acNumber, String branch, String contractId, //
			String payContractRatio, String payProjectRatio, String actualStartTime, //
			String actualEndTime, String contractDate, String firstCommissionDate, //
			String balanceTime, String balanceCommissionDate, String designerAssistantCommissionDate, int pageIndex, int pageSize, String sortField,
			String sortOrder) {
		ProjectCommissionFilterRequest request = new ProjectCommissionFilterRequest();
		request.setStart(pageIndex * pageSize + 1);
		request.setEnd((pageIndex + 1) * pageSize);
		request.setDesigner(designer);
		request.setDesignerAssistantCommissionDate(designerAssistantCommissionDate);
		request.setDesignerAssistant(designerAssistant);
		request.setContractId(contractId);
		request.setAcNumber(acNumber);
		request.setBranch(branch);
		request.setContractState(contractState);
		if (payContractRatio != null && !payContractRatio.equals("")) {
			request.setPayContractRatio(new BigDecimal(payContractRatio));
		}
		if (payProjectRatio != null && !payProjectRatio.equals("")) {
			request.setPayProjectRatio(new BigDecimal(payProjectRatio));
		}
		request.setCommissionState(StringUtils.isBlank(commissionState) ? null : CommissionStateEnum.fromDbConstant(Integer.valueOf(commissionState)));
		request.setActualStartTime(actualStartTime);
		request.setActualEndTime(actualEndTime);
		request.setContractDate(contractDate);
		request.setFirstCommissionDate(firstCommissionDate);
		request.setBalanceTime(balanceTime);
		request.setBalanceCommissionDate(balanceCommissionDate);
		request.setSortField(sortField);
		request.setSortOrder(sortOrder);
		return request;
	}

	@Override
	@CheckAuthority
	public DmsResponse upload(HttpServletRequest request) throws FileUploadException, IOException {
		String savePath = request.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> list = upload.parseRequest(request);
		FileItem item = list.get(0);
		InputStream in = item.getInputStream();

		List<DesignAssistantDto> designAssistantDtos = readXLSXFile(in);
		projectCommissionService.updateDesignAssistants(designAssistantDtos);
		IOUtils.closeQuietly(in);
		DmsResponse response = new DmsResponse();
		response.setCode(ResponseEnum.SUCCESS);
		return response;
	}

	private List<DesignAssistantDto> readXLSXFile(InputStream inputStream) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;

		List<DesignAssistantDto> designAssistantDtos = new ArrayList<>();
		Iterator rows = sheet.rowIterator();
		if (rows.hasNext()) {
			rows.next();
		}
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			DesignAssistantDto designAssistantDto = new DesignAssistantDto();
			if (row.getCell(0) != null) {
				String acNumber = String.valueOf((int) row.getCell(0).getNumericCellValue());
				designAssistantDto.setAcNumber(acNumber);
			}

			if (row.getCell(1) != null) {
				String designAssistant = row.getCell(1).getStringCellValue();
				designAssistantDto.setDesignAssistant(designAssistant);
			}

			if (row.getCell(2) != null) {
				double purchaseCost = row.getCell(2).getNumericCellValue();
				designAssistantDto.setPurchaseCost(purchaseCost);
			}

			designAssistantDtos.add(designAssistantDto);
		}
		return designAssistantDtos;
	}
}
