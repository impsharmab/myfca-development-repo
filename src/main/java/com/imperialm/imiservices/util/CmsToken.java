package com.imperialm.imiservices.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CmsToken {

    private final long ALLOWABLE_TIME_FRAME = 6 * 60 * 1000;   // minutes * seconds * millisconds
    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
    private final TimeZone timeZone = TimeZone.getTimeZone("America/Detroit");
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public String create() throws Exception {
        // Get the date to encrypt
        GregorianCalendar cal = new GregorianCalendar(timeZone);
        String dateString = dateFormat.format(cal.getTime());
        log.debug("Token string: " + dateString);

        String encodedEncryptedString = null;
        try {
            // Encrypt the data
            CipherUtil cipherUtil = new CipherUtil();
            encodedEncryptedString = cipherUtil.encrypt(dateString);
            log.debug("Encrypted string: " + encodedEncryptedString);
        } catch (Exception e) {
            throw new Exception(e);
        }

        return encodedEncryptedString;
    }

    public Boolean isValid(String token) throws Exception {
        try {
            // Decrypt the data
            CipherUtil cipherUtil = new CipherUtil();
            String decryptedString = cipherUtil.decrypt(token);
            log.debug("Client time: " + decryptedString);

            // Test the integrity of the data by parsing it as a date object
            Date date = null;
            try {
                date = dateFormat.parse(decryptedString);
            } catch (NullPointerException npe) {
                log.debug("***Token is invalid");
                return false;
            }

            long sent = date.getTime();

            GregorianCalendar cal = new GregorianCalendar(timeZone);
            long now = (cal.getTimeInMillis());

            long milliseconds = now - sent;
            if (log.isDebugEnabled()) {
                log.debug("Server time: " + dateFormat.format(cal.getTime()));
                log.debug("Client     milliseconds: " + sent);
                log.debug("Server     milliseconds: " + now);
                log.debug("Difference milliseconds: " + milliseconds);
            }

            // The decrypted date must be within the proper time range or the token is not valid
            if (milliseconds >= -ALLOWABLE_TIME_FRAME && milliseconds <= ALLOWABLE_TIME_FRAME) {
                log.debug("Token is valid");
                return true;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

        log.debug("***Token is invalid");
        return false;
    }
}
