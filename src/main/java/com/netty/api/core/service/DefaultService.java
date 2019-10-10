package com.netty.api.core.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.netty.api.core.code.CommonCode;
import com.netty.api.server.template.AbstractApiTemplate;
import java.util.Map;

/**
 * <pre>
 * Description :
 * @author mezzomedia
 * @since 2018.06.15
 * @version
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultService extends AbstractApiTemplate {

    public DefaultService(Map reqData) {
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws Exception {

    }

    @Override
    public void service() throws Exception {

        this.responseResult.setResponseMessage("요청된 URL 페이지가 없습니다.  404");
        this.responseResult.setStateCode(CommonCode.FAIL);


    }
}