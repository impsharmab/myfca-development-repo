package com.imperialm.imiservices.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

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
	
	
	

	public String formatCurrency(int number){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format((int)number);
		if (moneyString.endsWith(".00")) {
			int centsIndex = moneyString.lastIndexOf(".00");
			if (centsIndex != -1) {
				moneyString = moneyString.substring(1, centsIndex);
			}
		}

		return moneyString;
	}

	public String formatCurrency(double number){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String moneyString = formatter.format((int)number);
		if (moneyString.endsWith(".00")) {
			int centsIndex = moneyString.lastIndexOf(".00");
			if (centsIndex != -1) {
				moneyString = moneyString.substring(1, centsIndex);
			}
		}

		return moneyString;
	}

	public String formatNumbers(double number){
		DecimalFormat formatter = new DecimalFormat("#,###");

		return formatter.format(number);
	}

	public String formatNumbers(int number){
		DecimalFormat formatter = new DecimalFormat("#,###");

		return formatter.format(number);
	}
	
	public String getCurrentQuarter(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR)) + "Q" + ((cal.get(Calendar.MONTH) / 3) + 1);
	}

	public String getCurrentYear(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR))+"";
	}	
	
	
	

}
