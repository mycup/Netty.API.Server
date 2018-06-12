package com.mezzomedia.server.web.servlet;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mezzomedia.core.code.CommonCode;
import com.mezzomedia.core.model.common.AbstractResponseObject;
import com.mezzomedia.core.model.common.ResponseResult;
import com.mezzomedia.core.model.domain.object.User;
import com.mezzomedia.core.model.dto.audience.Audience;
import com.mezzomedia.core.service.MybatisService;
import com.mezzomedia.core.service.audience.AudienceAerospikeService;
import com.mezzomedia.server.config.ApplicationContextProvider;
import com.mezzomedia.server.function.ServerResponse;
import com.mezzomedia.server.handler.channel.ApiRequestParser;
import com.mezzomedia.util.utils.json.JsonUtils;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;

/**

 * <pre>
 * 	
 * 	클라이언트로 부터 받은 HTTP / HTTPS 요청에 대한  Handler Mapping 및 분기 처리 
 * 
 * </pre>
 *
 * @author skan
 * @since 2018. 2. 27.
 * @version 
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
@Component
public class Dispatcher {
	
	private static Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	
	@Autowired private ApplicationContextProvider applicationContextProvider;
	
	
	/**
	 * Spring Context를 주입 받아 사용하기 위함.
	 * {@link ApplicationContextProvider} 사용
	 */
	@Deprecated
	private static ApplicationContext APPLICATION_SPRING_CONTEXT;


	@Autowired
	public void initializer(ApplicationContext springContext) {
		// APPLICATION_SPRING_CONTEXT = springContext;
		// applicationContextProvider.setApplicationContext(springContext);
		// logger.info("springContext = {} " ,  APPLICATION_SPRING_CONTEXT);
		logger.info("applicationContextProvider = {} " ,  applicationContextProvider);
	}
	
	/**
	 * 
	 * TODO : 요청 URI , URL PATH  확인후 분기 처리
	 * @param <T>
     *
	 * @param httpMethod
     * @param requestDate
     * @param urlPath
	 */
	public static void dispatch(HttpRequest httpRequest, LastHttpContent lastHttpContent, ChannelHandlerContext ctx) throws Exception {
			
		logger.debug("dispatch ........ handling....");
		
		// URI URL PATH 분리
		String urlPath = httpRequest.uri();
		URI uri = URI.create(urlPath);
		HttpMethod method = httpRequest.method();

			
		
		AbstractResponseObject<Object> response =  new ResponseResult<>();
		
		
		
		switch (uri.getPath()) {
		case "/audience":
			
			
			AudienceAerospikeService audienceAerospikeService = ApplicationContextProvider.getBean(AudienceAerospikeService.class);
			Audience audience = audienceAerospikeService.save(new Audience());
			
			response.setStateCode(CommonCode.SUCCESS);
			response.setResponseMessage("저장 성공 ");
			response.setObject(audience);
			
			
			break;
		case "/user/findAll" :
			
			MybatisService mybatisService = ApplicationContextProvider.getBean(MybatisService.class);
			List<User> users = mybatisService.findUserList();
			response.setStateCode(CommonCode.SUCCESS);
			response.setObject(users);
			
			break;
			
		default:
			
			response.setStateCode(CommonCode.FAIL_NODATA);
			
			
			break;
		}
		
		
		
		
		// Response 
		response(httpRequest, lastHttpContent, ctx, response);
		

//		logger.debug("///////////////////////////////////////////////////////////////////");
//		logger.debug("//  Redis TEST");
//		logger.debug("///////////////////////////////////////////////////////////////////");
//		RedisService redisService = ApplicationContextProvider.getBean(RedisService.class);
//		redisService.save(new Object());

//		logger.debug("///////////////////////////////////////////////////////////////////");
//		logger.debug("//  Mybatis  TEST");
//		logger.debug("///////////////////////////////////////////////////////////////////");
//		MybatisService mybatisService = ApplicationContextProvider.getBean(MybatisService.class);
//		mybatisService.findUserList();

	}
	
	/**
	 * 
	 * @param httpRequest
	 * @param lastHttpContent
	 * @param ctx
	 * @param response
	 * @throws Exception 
	 */
	public static <T> void response( HttpRequest httpRequest, LastHttpContent lastHttpContent, ChannelHandlerContext ctx, AbstractResponseObject<T> response) throws Exception {
		
		// Decide whether to close the connection or not.
        boolean keepAlive = HttpUtil.isKeepAlive(httpRequest);
		
        if (!keepAlive) {
        	ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
        
		FullHttpResponse fullHttpResponse = ServerResponse
			.status(HttpResponseStatus.INTERNAL_SERVER_ERROR)
			.contentType("", "")
			.body(lastHttpContent, ctx, JsonUtils.convertJson(response));
		
		
		// Write the response.
        ctx.write(fullHttpResponse);
		
	}

	


}
