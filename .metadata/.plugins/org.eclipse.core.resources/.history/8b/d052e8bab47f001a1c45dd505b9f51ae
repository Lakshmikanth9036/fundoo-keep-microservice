package com.bridgelabz.fundookeep.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.bridgelabz.fundookeep.constants.Constants;
import com.bridgelabz.fundookeep.interceptor.RequestHeaderInterceptor;
import com.bridgelabz.fundookeep.repository.UserRepositoryService;

@Configuration
//@EnableWebMvc
public class FundooKeepConfig 
//extends WebMvcConfigurationSupport
{
	
	@Autowired
	private RequestHeaderInterceptor requestHeaderInterceptor;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserRepositoryService getUserRepository() {
		return new UserRepositoryService();
	}
	
	@Bean
	public Exchange mailExchange() {
		return new DirectExchange(Constants.EXCHANGE_NAME);
	}
	
	@Bean
	public Queue mailQueue() {
		return new Queue(Constants.QUEUE_NAME);
	}
	
	@Bean
	public Binding declareBinding(Queue mailQueue, DirectExchange mailExchange) {
		return BindingBuilder.bind(mailQueue).to(mailExchange).with(Constants.ROUTING_KEY);
	}
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, Long> redisTemplet() {
		RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	
	@Bean(destroyMethod = "close")
	public RestHighLevelClient elasticsearchClient() {

		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

		return client;
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registery) {
//		registery.addInterceptor(requestHeaderInterceptor)
//		.addPathPatterns("/note/**","/label/**")
//		.excludePathPatterns("/note/getNoteByTitleAndDescription/**","/user/**");
//	}
	
}

