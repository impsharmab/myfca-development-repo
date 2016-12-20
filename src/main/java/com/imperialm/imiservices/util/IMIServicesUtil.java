package com.imperialm.imiservices.util;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class IMIServicesUtil {

	private static final Logger logger = LoggerFactory.getLogger(IMIServicesUtil.class);

	public static String prepareJson(final String key, final String value) {
		final Gson gson = new Gson();
		final Properties props = new Properties();
		props.put(key, value);
		return gson.toJson(props);
	}

	/**
	 * from Clob to String
	 * 
	 * @param result
	 * @return java.lang.String
	 */
	public static String buildString(final Object result) {
		String returnString = "";
		if (result != null) {
			try {
				returnString = IOUtils.toString(((Clob) result).getCharacterStream()).replaceAll("\t", "")
						.replaceAll("\\s", "").replaceAll("\"", "'");
			} catch (final IOException e) {
				logger.error("error while building String from Clob Step 1", e);
			} catch (final SQLException e) {
				logger.error("error while building String from Clob Step 2", e);
			}
		}
		logger.info("returnString  " + returnString);

		return returnString;
	}

}
