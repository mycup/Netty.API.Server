package com.mezzomedia.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;

/**
 * 
 * <pre>
 * Description : Aerospike 환경 설정  
 * </pre>
 *
 * @author skan
 * @since 2018. 4. 3.
 * @version 
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
@Configuration
@EnableAerospikeRepositories(basePackages= {"com.mezzomedia.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class AerospikeConfigration {
	
	@Value("${aerospike.host:210.221.235.202}")
	private String aerospikeHost;
	
	@Bean
	public AerospikeClient aerospikeClient() {
		
		ClientPolicy policy = new ClientPolicy();
		policy.failIfNotConnected = true ;
		
		return new AerospikeClient(policy, aerospikeHost, 3000);
	}
	
	@Bean
	public AerospikeTemplate aerospikeTemplate() {
		return new AerospikeTemplate(this.aerospikeClient(),"test"); 
	}
	

}
