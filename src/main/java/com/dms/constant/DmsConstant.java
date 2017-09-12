package com.dms.constant;

import java.util.List;

import com.google.common.collect.Lists;

public final class DmsConstant {

	public static List<String> projectCommissionHeader = Lists.newArrayList("设计师", "设计助理", "工程编号", "门店", "受理编号", "客户名字", "合同价", "代采购", "代采购折算后合同价","变更总价", "已付", "合同收款比", "总收款比",
			"状态", "提成状态", "首提", "二次提成", "提成系数",  "首提日期", "实际开工",  "结算日期", "二次提成日期", "设计助理提成比", "设计助理提");

	// 员工工资
	public static List<String> employeeWageHeader = Lists.newArrayList("公司", "员工姓名", "身份证号", "银行卡号", "税前工资", "个税", "税后工资", "实发工资（现金）");

	// 工资条
	public static List<String> salaryBillHeader = Lists.newArrayList("公司", "部门", "年月", "姓名", "职位", "入职日期", "基本工资", "其他补贴", "饭贴", "保密", "提成补贴", "工龄补贴", "考核奖励",
			"通讯费", "扣款", "说明", "会展扣款", "事假", "病假", "保管", "应发工资", "个人社保", "个人公积金", "税前工资", "个税", "实发工资", "", "岗位津贴", "绩效考核", "补贴", "提成", "翻新保护费", "其他扣款", "展会最后一名扣款",
			"实发", "参考值");
}
