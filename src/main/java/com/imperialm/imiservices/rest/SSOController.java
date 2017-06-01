/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imperialm.imiservices.rest;

import com.imperialm.imiservices.dao.TIDUsersDAO;
import com.imperialm.imiservices.dao.UserPositionCodeRoleDAO;
import com.imperialm.imiservices.dto.TIDUsersDTO;
import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.dto.UserPositionCodeRoleDTO;
import com.imperialm.imiservices.security.JwtTokenUtil;
import com.imperialm.imiservices.services.UserServiceImpl;
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
import org.springframework.web.servlet.view.RedirectView;
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
import java.util.ArrayList;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author dheerajr
 */
@RestController
public class SSOController {

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private UserPositionCodeRoleDAO userPositionCodeRoleDAO;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserServiceImpl userDetailsService;

	@Autowired
	private TIDUsersDAO TIDUsersDAO;

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
		// show default page -- Change the return to show default page
		return "failure";
	}

	/**
	 * This method will process the SAML response from the DealerCONNECT
	 *
	 * @param authenticationRequest
	 * @param request
	 * @param response
	 * @return
	 * @throws javax.naming.AuthenticationException
	 */
	@RequestMapping(value = "/sp/acs.saml",  method = RequestMethod.POST)
	public Object processSingleSignOnToken(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! SAML REQUEST !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Map userDetailMap = null;
		userDetailMap = processSAMLResponse(request);
		String userId = (String) userDetailMap.get("UID");
		String dealerCode = (String) userDetailMap.get("Dealer Code");
		String positionCode = (String) userDetailMap.get("Position Code");
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! USERID FROM DEALERCONNECT" + userId);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DEALERCODE FROM DEALERCONNECT" + dealerCode);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! POSITIONCODE FROM DEALERCONNECT" + positionCode);
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//List<String> posCode = new ArrayList<String>();
		//List<String> dlrCode = new ArrayList<String>();

		if ("NA".equals(dealerCode)) {
			dealerCode = "";
		}

		if (userDetailMap == null || (userDetailMap != null && userDetailMap.size() == 0)) {            
			return ResponseEntity.badRequest().body("Failed in getting SAML Response");
		} else {
			System.out.println("UserID: " + userId + "Dealer Code: " + dealerCode + "Position Code: " + positionCode);

			UserDetailsImpl user = null;
			try{
				user = (UserDetailsImpl) userDetailsService.loadUserByUsername(userId);
			}
			catch(Exception e){
					RedirectView redirectView = new RedirectView("../loginerror.html", true);
					return redirectView;
			}

			final String token = jwtTokenUtil.generateToken(user);

			List<UserPositionCodeRoleDTO> userCodes = new ArrayList<UserPositionCodeRoleDTO>();

			if ((dealerCode != null && !dealerCode.isEmpty()) && (positionCode != null && !positionCode.isEmpty())) {
				return new RedirectView("/?token=" + token + "&dc=" + dealerCode + "&pc=" + positionCode, true);
			} else if ((dealerCode != null && !dealerCode.isEmpty()) && (positionCode == null || positionCode.isEmpty())) {
				userCodes = userPositionCodeRoleDAO.getDealerCodePCRoleBySid(user.getUserId());

				if(userCodes.size() > 0){
					return new RedirectView("/?token=" + token + "&dc=" + dealerCode + "&pc=" + userCodes.get(0).getPositionCode() , true);
				}else if(userCodes.size() == 0){
					List<String> territoryCheck = this.userPositionCodeRoleDAO.getUserTerritoyById(user.getUserId());
					if(territoryCheck.size() > 0){
						if(territoryCheck.get(0).equalsIgnoreCase("nat")){
							return new RedirectView("/?token=" + token + "&dc=" + dealerCode + "&pc=" + "90" , true);
						}else if(territoryCheck.get(0).contains("-")){
							return new RedirectView("/?token=" + token + "&dc=" + dealerCode + "&pc=" + "97" , true);
						}else if(territoryCheck.get(0).length() == 2){
							return new RedirectView("/?token=" + token + "&dc=" + dealerCode + "&pc=" + "8D" , true);
						}/*else{
                 			positionCode.add("01");
                 			dealerCode.add(user.getUserId());
                 		}*/
					}
				}

				return ResponseEntity.badRequest().body("Failed in getting position code");

			} else if ((dealerCode == null || dealerCode.isEmpty()) && (positionCode != null && !positionCode.isEmpty())) {

				userCodes = userPositionCodeRoleDAO.getDealerCodePCRoleBySid(user.getUserId());
				if(userCodes.size() > 0){
					return new RedirectView("/?token=" + token + "&dc=" + userCodes.get(0).getDealerCode() + "&pc=" + positionCode , true);
				}

				return new RedirectView("/?token=" + token + "&dc=0" + "&pc=" + positionCode, true);
			} else {
				userCodes = userPositionCodeRoleDAO.getDealerCodePCRoleBySid(user.getUserId());

				if(userCodes.size() == 0){
					List<TIDUsersDTO> tids = TIDUsersDAO.getTIDUsersByTID(user.getUserId());
					if(tids.size() > 0){
						return new RedirectView("/?token=" + token + "&dc=" + tids.get(0).getTerritory() + "&pc=" + tids.get(0).getPositionCode(), true);
					}
				}else if(userCodes.size() > 0){
					return new RedirectView("/?token=" + token + "&dc=" + userCodes.get(0).getDealerCode() + "&pc=" + userCodes.get(0).getPositionCode() , true);
				}
				return ResponseEntity.badRequest().body("Failed in getting position code or dealer code");
			}
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
