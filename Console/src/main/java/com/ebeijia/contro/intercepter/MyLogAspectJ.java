package com.ebeijia.contro.intercepter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.ebeijia.tools.DateTime4J;
import org.ebeijia.tools.String4J;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebeijia.annotation.MyLog;
import com.ebeijia.dao.txnLog.TxnLogDao;
import com.ebeijia.entity.TblAdminInf;
import com.ebeijia.entity.TblTxnLog;
import com.ebeijia.service.txnLog.TxnLogService;

@Component
@Aspect
public class MyLogAspectJ {
	
//	private static Logger log = LoggerFactory.getLogger(MyLogAspectJ.class);

	
	@Autowired  
	private  HttpServletRequest request;  
	@Autowired  
	private  HttpSession session;  
	@Autowired
	private TxnLogDao txnLogDao;
	@Autowired
	private TxnLogService txnLogService;
	
	@Pointcut("@annotation(com.ebeijia.annotation.MyLog)")
	public void methodCachePointcut() {
	}

	@Around("methodCachePointcut()")
	public Object methodCacheHold(ProceedingJoinPoint joinPoint)throws Throwable {
		long start = System.currentTimeMillis();
		String methodRemark = getMthodRemark(joinPoint);
		Object result = null;
		// 记录操作日志...调用日志记录方法
		try {
			result = joinPoint.proceed();
			this.save(start, methodRemark, null);
//			log.info(methodRemark+"操作成功");
		} catch (Exception e) {
			this.save(start, methodRemark, e);
			result = joinPoint.proceed();
		}
		return result;
	}

	private static String getMthodRemark(ProceedingJoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		String methode = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					MyLog methodCache = m.getAnnotation(MyLog.class);
					methode = methodCache.remark();
					break;
				}
			}
		}
		return methode;
	}
	
//	//自动生成流水号
//	private String getTxnNo() {
//		String sql = "SELECT SEQ_TXN_NO.NEXTVAL FROM DUAL";
//		String txnNo = txnLogDao.getListSQL(sql).get(0).toString();
//		return String4J.beforFillValue(txnNo, 15, '0');
//	}
	
	//获取客户端ip
	private String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	//保存日志
	public void save(long start, String methodRemark, Exception e){
//		String txnNo = getTxnNo();
		String nowTime = DateTime4J.getCurrentDateTime();
		TblTxnLog tblTxnLog = new TblTxnLog();
		if(e == null){
			tblTxnLog.setTxnStatus("0");
		}else{
			tblTxnLog.setTxnStatus("1");
			String error = e.toString().length()>128?e.toString().substring(0, 128):e.toString();
			tblTxnLog.setTxnError(error);
		}
//		tblTxnLog.setTxnNo(txnNo);
		if(session.getAttribute("operator") == null){
		}else{
			TblAdminInf data =(TblAdminInf) session.getAttribute("operator");
			tblTxnLog.setOperator(data.getAdminName());
		}
		tblTxnLog.setRemoteAddr(this.getRemoteHost(request));
		tblTxnLog.setTxnDate(nowTime.substring(0, 8));
		tblTxnLog.setTxnTime(nowTime.substring(8, 14));
		tblTxnLog.setOperaTime(Long.toString(System.currentTimeMillis() - start));
		String chl = (methodRemark.indexOf("接口") == -1)? "1":"0";
		tblTxnLog.setTxnChl(chl);
		tblTxnLog.setTxnName(methodRemark);
		txnLogService.addTxnLog(tblTxnLog);
	}
}