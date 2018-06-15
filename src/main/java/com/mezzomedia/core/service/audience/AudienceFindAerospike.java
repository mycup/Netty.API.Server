package com.mezzomedia.core.service.audience;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mezzomedia.core.code.CommonCode;
import com.mezzomedia.core.model.dto.audience.Audience;
import com.mezzomedia.core.repository.aerospike.AudienceAerospikeRepository;
import com.mezzomedia.server.template.AbstractApiTemplate;

/**
 * <pre>
 * Description : 고객 정보 조회
 * @author skan
 * @since 2018.06.14
 * @version
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
@Service
public class AudienceFindAerospike extends AbstractApiTemplate<Audience> {


	public AudienceFindAerospike(Map<String, String> reqData) {
		super(reqData);
		// TODO Auto-generated constructor stub
	}

	@Autowired  private AudienceAerospikeRepository audienceAerospikeRepository;

	@Override
	public void requestParamValidation() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service() throws Exception {
		
		String adid = reqData.get("adid");
		Audience audience =  audienceAerospikeRepository.findOne(adid);
		this.responseResult.setObject(audience);
		this.responseResult.setStateCode(CommonCode.SUCCESS);
		
		
	}

}