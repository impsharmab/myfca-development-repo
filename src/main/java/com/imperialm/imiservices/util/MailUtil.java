package com.imperialm.imiservices.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.mail.DefaultAuthenticator;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class MailUtil
{
  public Properties props = new Properties();
  public boolean PROPERTIES_SET = false;
  private String smtpHost =  "smtp.office365.com";
  private String smtpPort =  "587";
  private String exchangeUser =  "smtpuser@imperialm.com";
  private String exchangePassword =  "imi.smtp";
  private String emailSubjectPrefix = "";

    /** Logger for this class and subclasses */
  protected final Log logger = LogFactory.getLog(getClass());  
  
  public MailUtil() 
  {
  }

  public MailUtil(String smtpHost, String smtpPort, String exchangeUser, String exchangePassword)
  {
    this.setSmtpHost(smtpHost);
    this.setSmtpPort(smtpPort);
    this.setExchangeUser(exchangeUser);
    this.setExchangePassword(exchangePassword);
  }

  // from to cc bcc subject message
  public void sendMail(String from,
                              String to,
                              String cc,
                              String bcc,
                              String subjectLine,
                              String message) throws Exception
  {
    String subject = subjectLine;
     // this will also check for email error
    checkGoodEmail(from);
    InternetAddress fromAddress = new InternetAddress(from);
    InternetAddress[] toAddress = getInternetAddressEmails(to);
    InternetAddress[] ccAddress = getInternetAddressEmails(cc);
    InternetAddress[] bccAddress= getInternetAddressEmails(bcc);
    if ((toAddress == null) && (ccAddress == null) && (bccAddress == null))
    {
        throw new Exception("Cannot send mail since all To, Cc, Bcc addresses are empty.");
    }

   /* if(!PROPERTIES_SET)
    {*/
      props = new Properties();
      props.put("mail.smtp.timeout","30000");
      props.put("mail.smtp.host", smtpHost);

      props.put("mail.smtp.port", smtpPort);
      
      props.put("mail.smtp.starttls.enable","true");
     
      if ( (exchangeUser != null) && (exchangeUser.length() > 0))
      {
        props.put("mail.smtp.auth", "true");
        props.put("mail.user", exchangeUser);
        props.put("mail.password", exchangePassword);
      }
      /*PROPERTIES_SET = true;
    }*/

    logger.info("SMTP HOST: " + smtpHost);    
    logger.info("SMTP PORT: " + smtpPort); 
    logger.info("Exchange User: " + exchangeUser);
        
    Transport transport = null;
    try
    {
        Authenticator authenticator = new DefaultAuthenticator(exchangeUser, exchangePassword);       
       
        Session session = Session.getInstance(props, authenticator);
        transport = session.getTransport("smtp");
        transport.connect();
        // create a message
        Message msg = new MimeMessage(session);
        msg.setSentDate(new Date());
        msg.setFrom(fromAddress);
        if (toAddress != null) msg.setRecipients(Message.RecipientType.TO, toAddress);
        if (ccAddress != null) msg.setRecipients(Message.RecipientType.CC, ccAddress);
        if (bccAddress!= null) msg.setRecipients(Message.RecipientType.BCC, bccAddress);
        msg.setSubject(getEmailSubjectPrefix() + subject);
        msg.setContent(message,"text/html");
        //msg.setText(message);
        msg.saveChanges();
        transport.sendMessage(msg, msg.getAllRecipients());
        //System.out.print("Sending to: " + to);
    }
    catch (MessagingException mex) {throw mex;}// this may look redundant
    finally
    {
      try
      {
        if (transport != null) transport.close();
      }
      catch (MessagingException ex)
      {
      }
    }
  }


  private static String[] getEmails(String email) throws Exception
  {
      if (email == null) email = "";
      email = email.trim();// very important
      StringTokenizer t = new StringTokenizer(email, ";");
      String[] ret = new String[t.countTokens()];
      int index = 0;
      while(t.hasMoreTokens())
      {
          String mail = t.nextToken().trim();
          checkGoodEmail(mail);
          ret[index] = mail;
          index++;
      }
      return ret;
  }

  private static InternetAddress[] getInternetAddressEmails(String email) throws Exception
  {
      String[] mails = getEmails(email);
      if (mails.length == 0) return null;// must return null, not empty array
      InternetAddress[] address = new InternetAddress[mails.length];
      for (int i = 0; i < mails.length; i++)
      {
          address[i] = new InternetAddress(mails[i]);
      }
      return address;
  }

  public static void checkGoodEmail(String input) throws Exception
  {
      //if (input == null) throw new BadInputException("Sorry, null string is not a good email.");
      int atIndex = input.indexOf('@');
      int dotIndex = input.lastIndexOf('.');
      //if ((atIndex == -1) || (dotIndex == -1) || (atIndex >= dotIndex))
      //   throw new BadInputException("Error: '" + input + "' is not a valid email value. Please try again.");
      // now check for content of the string
      byte[] s = input.getBytes();
      int length = s.length;
      byte b = 0;

      for (int i = 0; i < length; i++)
      {
          b = s[i];
          if ((b >= 'a') && (b <= 'z')) {}  // lower char
          else if ((b >= 'A') && (b <= 'Z')) {}  // upper char
          else if ((b >= '0') && (b <= '9') && (i != 0)) {} // numeric char
          else if ( ( (b=='_') || (b=='-') || (b=='.') || (b=='@') ) && (i != 0) ) {}// _ char
          else 
          {
              //throw new BadInputException(input + " is not a valid email. Reason: character '" + (char)(b) + "' is not accepted in an email.");
          }// not good char, throw an BadInputException
      }// for

      // last check
      try 
      {
          new javax.mail.internet.InternetAddress(input);
      } 
      catch (Exception ex) 
      {
          throw new Exception("Assertion: MailUtil.checkGoodEmail failed for " + input);
      }
  }

    public String getExchangeUser()
    {
        return exchangeUser;
    }

    public void setExchangeUser(String exchangeUser)
    {
        this.exchangeUser = exchangeUser;
    }

    public String getExchangePassword()
    {
        return exchangePassword;
    }

    public void setExchangePassword(String exchangePassword)
    {
        this.exchangePassword = exchangePassword;
    }

    public String getSmtpHost()
    {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost)
    {
        logger.info("SMTP HOST: " + smtpHost);
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort()
    {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort)
    {
        logger.info("SMTP PORT: " + smtpPort);
        this.smtpPort = smtpPort;
    }
    
  /*  public static void main(String[] args)
    {
        
        MailUtil util = new MailUtil("imail.imperialm.com", "25", "cart", "shopper");
        String from = "apteralifestyle@imperialm.com";
        String to = "ctrevarthen@wowway.com";
        String cc = " ";
        String bcc = " ";
        String subject = "Test";
        String message = "Test Message";
        try
        {
            util.sendMail(from, to, cc, bcc, subject, message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }*/

    /**
     * @return the emailSubjectPrefix
     */
    public String getEmailSubjectPrefix() {
        return emailSubjectPrefix;
    }

    /**
     * @param emailSubjectPrefix the emailSubjectPrefix to set
     */
    public void setEmailSubjectPrefix(String emailSubjectPrefix) {
        this.emailSubjectPrefix = emailSubjectPrefix;
    }

}
