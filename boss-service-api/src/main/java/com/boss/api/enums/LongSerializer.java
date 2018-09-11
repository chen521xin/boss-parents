/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.enums;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @description
 * @data 2018年2月5日上午11:58:10
 * @author chenxinxin
 * @version v1.0
 * @since v1.0
 *
 **/
public enum LongSerializer implements RedisSerializer<Long>{

	INSTANCE;
	@Override
	public Long deserialize(byte[] bytes) throws SerializationException {
		if(bytes.length>0){
			return Long.parseLong(new String(bytes));
		}else{
			return null;
		}
	}

	@Override
	public byte[] serialize(Long along) throws SerializationException {
		if(null != along){
			return along.toString().getBytes();
		}else{
			return new byte[0];
		}
	}

}
