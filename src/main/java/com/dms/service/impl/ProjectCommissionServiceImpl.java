package com.dms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dms.dao.EmployeeDao;
import com.dms.dao.FinanceDao;
import com.dms.dao.ProjectCommissionDao;
import com.dms.dto.DesignAssistantDto;
import com.dms.dto.EmployeeDto;
import com.dms.dto.FinanceDto;
import com.dms.dto.ProjectCommissionDto;
import com.dms.enums.CommissionStateEnum;
import com.dms.request.ProjectCommissionFilterRequest;
import com.dms.service.ProjectCommissionService;
import com.dms.utils.DateUtils;
import com.dms.utils.DmsConstants;

@Service
public class ProjectCommissionServiceImpl implements ProjectCommissionService {

	@Autowired
	private ProjectCommissionDao projectCommissionDao;

	@Autowired
	private FinanceDao financeDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public ProjectCommissionDto getProject(String acNumber) {
		return projectCommissionDao.getProject(acNumber);
	}

	@Override
	public List<ProjectCommissionDto> getProjects(Date startDate, Date endDate) {
		return projectCommissionDao.getProjects(startDate, endDate);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommissionServiceImpl.class);

	public ProjectCommissionServiceImpl(ProjectCommissionDao projectCommissionDao) {
		this.projectCommissionDao = projectCommissionDao;
	}

	@Override
	public void calculateFirstCommission(List<ProjectCommissionDto> projectCommissionDtos) {
		for (ProjectCommissionDto commission : projectCommissionDtos) {

			if (commission.getPurchasingCost() == null) {
				commission.setPurchasingCost(BigDecimal.ZERO);
			}
			commission.setCommissionBase(commission.getContractTotal().subtract(commission.getPurchasingCost()));

			boolean isExistedDesigner = isExistedDesignerId(commission);
			if (isExistedDesigner) {
				LOGGER.info(" ====designer exist ======" + commission.getDesigner() + commission.getEmployeeId());
				CommissionStateEnum commissionState = CommissionStateEnum.COMMISSION_STATE_START;
				if (CommissionStateEnum.COMMISSION_STATE_START == commission.getCommissionState()) {
					BigDecimal firstCommission = BigDecimal.ZERO;
					// 小于1万 提成250
					if (commission.getContractTotal() != null && commission.getContractTotal().compareTo(DmsConstants.MIN_CONTRACT_COMMISSION) <= 0) {
						firstCommission = DmsConstants.MIN_COMMISSION;
						commissionState = CommissionStateEnum.COMMISSION_STATE_FINISH;
						LOGGER.info(" === 小于1万 提成250======" + firstCommission);
					} else {
						// 计算设计师助理提成
						if (commission.getDesignerAssistant() != null && !commission.getDesignerAssistant().equals("")
								&& commission.getDesignerAssistantCommissionRate() != null && commission.getDesignerAssistantCommission() == null) {
							commission.setDesignerAssistantCommission(commission.getCommissionBase().multiply(commission.getDesignerAssistantCommissionRate())
									.setScale(2, BigDecimal.ROUND_HALF_UP));
							commission.setDesignerAssistantCommissionDate(new LocalDateTime(DateUtils.getPreviousMonth()));
						}
						LOGGER.info(" === 小于1万 getDesignCommissionRate ======" + commission.getDesignCommissionRate() + "fffff"
								+ commission.getFirstCommissionRate());

						if (commission.getDesignCommissionRate() != null && commission.getFirstCommissionRate() != null) {
							commissionState = CommissionStateEnum.COMMISSION_STATE_FIRST;
							firstCommission = commission.getCommissionBase().multiply(commission.getDesignCommissionRate())
									.multiply(commission.getFirstCommissionRate());
						}

					}
					if (BigDecimal.ZERO != firstCommission && firstCommission != null) {
						commission.setFirstCommission(firstCommission.setScale(2, BigDecimal.ROUND_HALF_UP));
						commission.setFirstCommissionDate(new LocalDateTime(DateUtils.getPreviousMonth()));
						commission.setCommissionState(commissionState);
						commission.setUpdatedTime(LocalDateTime.now());
						updateCommission(commission.getEmployeeId(), commission.getFirstCommission(), commission.getFirstCommissionDate());
						projectCommissionDao.updateProjectCommission(commission);
					}

					// 设计助理提成保存
					if (commission.getFirstCommission() != null && commission.getFirstCommission() != BigDecimal.ZERO && !StringUtils.isEmpty(commission.getDesignerAssistant())) {
						EmployeeDto employee = employeeDao.getEmployeeByName(commission.getDesignerAssistant().trim());
						if (!StringUtils.isEmpty(employee.getId())) {
							updateCommission(Long.valueOf(employee.getId()), commission.getDesignerAssistantCommission(), commission.getFirstCommissionDate());
						}
					}
				}
			}
		}

	}

	private boolean isExistedDesignerId(ProjectCommissionDto commission) {
		if (StringUtils.isEmpty(commission.getEmployeeId()) || commission.getDesignCommissionRate() == null) {
			EmployeeDto employee = employeeDao.getEmployeeByName(commission.getDesigner().trim());
			if (employee != null) {
				commission.setEmployeeId(Long.valueOf(employee.getId()));
				if (commission.getDesignCommissionRate() == null || BigDecimal.ZERO.equals(commission.getDesignCommissionRate())) {
					if (commission.getContractId().startsWith(DmsConstants.JYW_PROJECT)) {
						commission.setDesignCommissionRate(employee.getRenovateCommissionRatio());
						// 佳园屋
					} else if (commission.getContractId().startsWith(DmsConstants.FX_PROJECT)) {
						commission.setDesignCommissionRate(employee.getJywCommissionRatio());
					}
					if (employee.getCommencementRatio() == null || employee.getCommencementRatio() == BigDecimal.ZERO) {
						commission.setFirstCommissionRate(DmsConstants.DESIGN_COMMISSION_FIRST_RATE.setScale(2, BigDecimal.ROUND_HALF_UP));
					}
				}
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	private void updateCommission(Long employeeId, BigDecimal commission, LocalDateTime commissionDate) {

		FinanceDto financeDto = new FinanceDto();
		financeDto.setEmployeeId(Long.valueOf(employeeId));
		String monthOfYear = String.valueOf(commissionDate.getMonthOfYear());
		if (!StringUtils.isEmpty(monthOfYear) && monthOfYear.length() == 1) {
			monthOfYear = "0".concat(monthOfYear);
		}
		String month = String.valueOf(commissionDate.getYear()).concat(monthOfYear);
		financeDto.setMonth(month);

		List<FinanceDto> existFinanceDtos = financeDao.getFinance(financeDto);

		if (existFinanceDtos != null && existFinanceDtos.size() > 0) {
			FinanceDto existFinanceDto = existFinanceDtos.get(0);
			existFinanceDto.setBonusCard(commission);
			financeDao.updateCommission(existFinanceDto);
		} else {
			financeDto.setBonusCard(commission);
			financeDao.saveFinance(financeDto);
		}
	}

	@Override
	public void calculateBalanceCommission(List<ProjectCommissionDto> projectCommissionDtos) {
		for (ProjectCommissionDto commission : projectCommissionDtos) {
			CommissionStateEnum commissionState;
			if (CommissionStateEnum.COMMISSION_STATE_FIRST == commission.getCommissionState()) {
				BigDecimal balanceCommission = BigDecimal.ZERO;
				commissionState = CommissionStateEnum.COMMISSION_STATE_FINISH;
				boolean isExistedDesigner = isExistedDesignerId(commission);
				if (isExistedDesigner) {
					if (commission.getProjectChangeTotal() == null) {
						commission.setProjectChangeTotal(BigDecimal.ZERO);
					}
					balanceCommission = commission.getCommissionBase().add(commission.getProjectChangeTotal());
					balanceCommission = balanceCommission.multiply(commission.getDesignCommissionRate()).subtract(commission.getFirstCommission());
					commission.setBalanceCommission(balanceCommission.setScale(2, BigDecimal.ROUND_HALF_UP));
					commission.setBalanceCommissionDate(new LocalDateTime(DateUtils.getPreviousMonth()));
					commission.setCommissionState(commissionState);
					commission.setUpdatedTime(LocalDateTime.now());
					// 设计师提成
					updateCommission(commission.getEmployeeId(), commission.getBalanceCommission(), commission.getBalanceCommissionDate());
					// 设计助理提成
					if (!StringUtils.isEmpty(commission.getDesignerAssistant())
							&& (commission.getDesignerAssistantCommission() != null && BigDecimal.ZERO != commission.getDesignerAssistantCommission())) {
						EmployeeDto employee = employeeDao.getEmployeeByName(commission.getDesignerAssistant().trim());
						if (!StringUtils.isEmpty(employee.getId())) {
							updateCommission(Long.valueOf(employee.getId()), commission.getDesignerAssistantCommission(),
									commission.getBalanceCommissionDate());
						}
					}
					projectCommissionDao.updateProjectCommission(commission);
				}
			}
		}

	}

	@Override
	public void saveProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) throws IllegalAccessException {
		projectCommissionDao.saveProjectCommission(projectCommissionDtos);
	}

	@Override
	public int saveProjectCommission(ProjectCommissionDto projectCommission) throws IllegalAccessException {
		throw new IllegalAccessException("unsupport operation");
		// projectCommissionDao.saveProjectCommission(projectCommission);
	}

	@Override
	public void updateProjectCommission(ProjectCommissionDto projectCommission) {
		if (projectCommission.getCommissionBase() == null && projectCommission.getPurchasingCost() != null) {
			projectCommission.getContractTotal().subtract(projectCommission.getPurchasingCost());
		}
		projectCommissionDao.updateProjectCommission(projectCommission);
	}

	@Override
	public void sychronzieProejcts() {
		LOGGER.info("======= sychronzieProejcts Start");
		List<ProjectCommissionDto> projects = getProjects(DateUtils.getCurrentDayStart(), DateUtils.getCurrentDayEnd());
		for (ProjectCommissionDto project : projects) {
			LOGGER.info("======= sychronzieProejcts Start project " + project.getAcNumber());
			ProjectCommissionDto extProjectCommissionDto = projectCommissionDao.getProject(project.getAcNumber());
			if (extProjectCommissionDto == null) {
				LOGGER.info("======= sychronzieProejcts Start: new  project " + project.getAcNumber());
				project.setCreatedTime(LocalDateTime.now());
				if (project.getDesigner() != null) {
					// 取得设计师的提成系数
					EmployeeDto employee = employeeDao.getEmployeeByName(project.getDesigner());
					if (employee != null) {
						// 翻新
						if (project.getContractId().startsWith(DmsConstants.JYW_PROJECT)) {
							project.setDesignCommissionRate(employee.getRenovateCommissionRatio());
							project.setFirstCommissionRate(employee.getCommencementRatio());
							// 佳园屋
						} else if (project.getContractId().startsWith(DmsConstants.FX_PROJECT)) {
							project.setDesignCommissionRate(employee.getJywCommissionRatio());
							project.setFirstCommissionRate(employee.getCommencementRatio());
						}
						if (employee.getCommencementRatio() == null || employee.getCommencementRatio() == BigDecimal.ZERO) {
							project.setFirstCommissionRate(DmsConstants.DESIGN_COMMISSION_FIRST_RATE.setScale(2, BigDecimal.ROUND_HALF_UP));
						}
						project.setEmployeeId(Long.valueOf(employee.getId()));
					}
					// 郊区设计师助理
					if (!StringUtils.isEmpty(project.getBranch()) && (project.getBranch().indexOf(DmsConstants.SUBURBS_QINGPU) > 0
							|| (project.getBranch().indexOf(DmsConstants.SUBURBS_SONGJIANG) > 0))) {
						project.setDesignerAssistantCommissionRate(DmsConstants.DESIGN_ASSISTANT_SUBURBS_COMMISSION_RATE);
					} else {
						project.setDesignerAssistantCommissionRate(DmsConstants.DESIGN_ASSISTANT_COMMISSION_RATE.setScale(4, BigDecimal.ROUND_HALF_UP));
					}
				}
				projectCommissionDao.saveProjectCommission(project);
			} else {
				LOGGER.info("======= sychronzieProejcts Start: existed project " + project.getAcNumber());
				extProjectCommissionDto.setUpdatedTime(LocalDateTime.now());
				extProjectCommissionDto.setPurchaseAgentFee(project.getPurchaseAgentFee());
				extProjectCommissionDto.setActualStartTime(project.getActualStartTime());
				extProjectCommissionDto.setActualEndTime(project.getActualEndTime());
				extProjectCommissionDto.setCustomerPay(project.getCustomerPay());
				extProjectCommissionDto.setBalanceTime(project.getBalanceTime());
				extProjectCommissionDto.setPayContractRatio(project.getPayContractRatio());
				extProjectCommissionDto.setPayProjectRatio(project.getPayProjectRatio());
				extProjectCommissionDto.setProjectChangeTotal(project.getProjectChangeTotal());
				extProjectCommissionDto.setAcNumber(project.getAcNumber());
				projectCommissionDao.updateProject(extProjectCommissionDto);
			}
		}
		LOGGER.info("======= sychronzieProejcts End");
	}

	@Override
	public List<ProjectCommissionDto> getProjectCommissions(ProjectCommissionFilterRequest request) {
		return projectCommissionDao.getProjectCommissions(request);
	}

	@Override
	public int getProjectCommissionCount(ProjectCommissionFilterRequest request) {
		return projectCommissionDao.getProjectCommissionCount(request);
	}

	@Override
	public void updateProjectCommissions(List<ProjectCommissionDto> projectCommissionDtos) {
		for (ProjectCommissionDto projectCommissionDto : projectCommissionDtos) {
			projectCommissionDao.updateProjectCommission(projectCommissionDto);
		}
	}

	@Override
	public void updateDesignAssistants(List<DesignAssistantDto> designAssistantDtos) {
		for (DesignAssistantDto designAssistantDto : designAssistantDtos) {
			// ProjectCommissionDto projectCommissionDto = projectCommissionDao.getProjectCommission()
			EmployeeDto employee = employeeDao.getEmployeeByName(designAssistantDto.getDesignAssistant());
			if (employee != null && !StringUtils.isEmpty(employee.getId())) {
				designAssistantDto.setDesignAssistantId(Long.valueOf(employee.getId()));
			} else {
				LOGGER.error( designAssistantDto.getDesignAssistant()   + "doesn't exist ");
			}
			projectCommissionDao.updateDesignAssistant(designAssistantDto);
		}
	}
}
