package com.apiserver.core.log;

import com.apiserver.config.LogMaker;

/**
 * <pre>
 * Description :
 * @author skan
 * @since 2018.04.26
 * @version
 *
 * Copyright (C) 2018 by CJENM|Mezzomedia.Inc. All right reserved.
 */
public interface ILogCreation {
	
	/**
	 * 
	 * @param message
	 */
	public void write(String message);
	
	/**
	 * 
	 * @param pattern
	 * @param message
	 */
	public void write(String pattern , String message);
	
	
	/**
	 * 
	 * @param pattern
	 * @param message
	 */
	public void write(LogMaker logMaker ,String pattern , String message);
	
}
