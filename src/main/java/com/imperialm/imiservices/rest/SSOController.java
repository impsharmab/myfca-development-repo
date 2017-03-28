/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imperialm.imiservices.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.DOMException;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.saml2.binding.decoding.HTTPPostDecoder;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.ws.message.MessageContext;
import org.opensaml.ws.message.decoder.MessageDecodingException;
import org.opensaml.ws.security.SecurityPolicyException;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.saml2.core.Response;
import com.sun.jersey.core.util.Base64;

/**
 *
 * @author dheerajr
 */
@RestController
public class SSOController {

    private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

    /**
     * The GET method is not expected to be called, this is only for the testing
     * purpose
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sp/acs.saml", method = RequestMethod.GET)
    public String getSSODetails(HttpServletRequest request, HttpServletResponse response) {
        // show default page
        return "failure";
    }

    /**
     * This method will process the SAML response from the DealerCONNECT
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sp/acs.saml", method = RequestMethod.POST)
    public String processSingleSignOnToken(HttpServletRequest request, HttpServletResponse response) {

        Map userDetailMap = null;
        userDetailMap = processSAMLResponse(request);
        String userId = (String) userDetailMap.get("UID");
        String dealerCode = (String) userDetailMap.get("DealerCode");
        String positionCode = (String) userDetailMap.get("PositionCode");
        
        if ("NA".equals(dealerCode)) {
            dealerCode = "";
        }

        if (userDetailMap == null || (userDetailMap != null && userDetailMap.size() == 0)) {
            System.out.println("Failed in getting SAML Response");
            return "Failure";
        } else {
            System.out.println("UserID: " + userId + "Dealer Code: " + dealerCode + "Position Code: " + positionCode);
            return "Sucess";
        }
    }

    /**
     * Process the SAML Response received from IDP
     *
     * @param request
     * @return
     */
    private Map processSAMLResponse(HttpServletRequest request) {
        Map attributeMap = new LinkedHashMap();
        String responseMessage = request.getParameter("SAMLResponse");
        logger.info("SAMLResponse-" + responseMessage);
        System.out.println("SAMLResponse-" + responseMessage);
        try {
            byte[] base64DecodedResponse = Base64.decode(responseMessage);
            String decodedResponse = new String(base64DecodedResponse);
            logger.info("Decoded SAML Response - " + decodedResponse);
            System.out.println("Decoded SAML Response - " + decodedResponse);

            try {
                //bootstrap the opensaml stuff
                org.opensaml.DefaultBootstrap.bootstrap();
            } catch (Exception ex) {
                logger.error("Exception: ", ex);
                System.out.println("Exception: " + ex);
            }
            // get the message context
            MessageContext messageContext = new BasicSAMLMessageContext();
            messageContext.setInboundMessageTransport(new HttpServletRequestAdapter(request));
            HTTPPostDecoder samlMessageDecoder = new HTTPPostDecoder();
            samlMessageDecoder.decode(messageContext);

            Response samlResponse;
            if (!(messageContext.getInboundMessage() instanceof SignableSAMLObject)) {
                throw new SecurityPolicyException("Inbound Message is not a SignableSAMLObject");
            }
            samlResponse = (Response) messageContext.getInboundMessage();

            Assertion decryptedAssertion = null;
            try {
                decryptedAssertion = samlResponse.getAssertions().get(0);
            } catch (Exception de) {
                logger.error("Assertion decryption failed.");
                logger.error(de.getMessage());
                System.out.println("Assertion decryption failed: " + de.getMessage());
            }

            //loop through the nodes to get the Attributes
            //this is where you would do something with these elements
            //to tie this user with your environment
            if (decryptedAssertion != null) {
                List<AttributeStatement> attributeStatements = decryptedAssertion.getAttributeStatements();
                for (AttributeStatement attributeStatement : attributeStatements) {
                    List<Attribute> attributes = attributeStatement.getAttributes();
                    for (Attribute attribute : attributes) {
                        String strAttributeName = attribute.getName();
                        List<org.opensaml.xml.XMLObject> attributeValues = attribute.getAttributeValues();
                        for (XMLObject attributeValue : attributeValues) {
                            String strAttributeValue = attributeValue.getDOM().getTextContent();
                            logger.info(strAttributeName + ": " + strAttributeValue + " ");
                            System.out.println(strAttributeName + ": " + strAttributeValue + " ");
                            attributeMap.put(strAttributeName, strAttributeValue);
                        }
                    }
                }
            }

        } catch (MessageDecodingException ex) {
            logger.error("Message Decoding Exception: ", ex);
            System.out.println("Message Decoding Exception: " + ex);
        } catch (SecurityException ex) {
            logger.error("Security Exception: ", ex);
            System.out.println("Security Exception: " + ex);
        } catch (DOMException ex) {
            logger.error("DOM Exception: ", ex);
            System.out.println("DOM Exception: " + ex);
        }
        return attributeMap;
    }

}
