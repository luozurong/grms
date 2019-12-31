package com.hori.hibernate;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class UUIDGenerator implements IdentifierGenerator ,Configurable{
	
	 private String sep = "";
	
	 public Serializable generate(SessionImplementor session, Object obj)
	    {
		 	String uuidStr =  UUID.randomUUID().toString();
			
			long time = System.currentTimeMillis();
			String timeStr = time+"";
			
			String timeTemp = timeStr.substring(0, 12);	
			
			StringBuffer sb = new StringBuffer(timeTemp);
			String[] uuidArray = uuidStr.split("-");
			
			for(String uuidStrTemp:uuidArray){
				sb.append(uuidStrTemp);
			}
			
			return sb.substring(0, 32);
	    }

	@Override
	public void configure(Type arg0, Properties arg1, ServiceRegistry arg2) throws MappingException {
		// TODO Auto-generated method stub
		
	}

	 /*public void configure(Type type, Properties params, Dialect d) {
			sep = PropertiesHelper.getString("separator", params, "");
	 }*/


	
}
