package com.dms.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class UserInterceptor extends AbstractPhaseInterceptor<Message> {

	public UserInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		// Map<String, Object> headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
		// HttpServletRequest request = (HttpServletRequest) message.get("HTTP.REQUEST");
		// HttpSession session = request.getSession();
	}
}
