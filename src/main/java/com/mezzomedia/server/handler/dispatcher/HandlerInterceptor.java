package com.mezzomedia.server.handler.dispatcher;


/**
 * 
 * <pre>
 * Class Name  : HandlerInterceptor.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2018. 3. 30.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2018. 3. 30.
 * @version 
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
public interface HandlerInterceptor {
	
	/**
	 * 요청에 따른 업무 전처리 핸들러 
	 * @return
	 * @throws Exception
	 */
	boolean preHandle() 	throws Exception;
	
	/**
	 * 업무 요청에 따른 후처리 핸들러 
	 * @throws Exception
	 */
	void postHandle() 	throws Exception;
	
	/**
	 * 요청이 모두 완료된뒤 처리 
	 * @throws Exception
	 */
	void afterCompletion()  throws Exception;
}
