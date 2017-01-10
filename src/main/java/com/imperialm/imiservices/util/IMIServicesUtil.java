package com.imperialm.imiservices.util;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class IMIServicesUtil {

	private static Logger logger = LoggerFactory.getLogger(IMIServicesUtil.class);

	public static String prepareJson(final String key, final String value) {
		final ObjectMapper json = new ObjectMapper();
		String toReturn ="";
		final Properties props = new Properties();
		props.put(key, value);
		try {
			toReturn = json.writeValueAsString(props);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * from Clob to String
	 * @param result
	 * @return java.lang.String
	 */
	public static String buildString(final Object result) {
		String returnString = "";
		if (result != null) {
			try {
				returnString = IOUtils.toString(((Clob) result).getCharacterStream()).replaceAll("\t", "");
			} catch (final IOException e) {
				logger.error("error while building String from Clob Step 1", e);
			} catch (final SQLException e) {
				logger.error("error while building String from Clob Step 2", e);
			}
		}

		return returnString;
	}

}
