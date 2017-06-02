package com.imperialm.imiservices.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.util.CmsToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class CMSService {

	private static Logger logger = LoggerFactory.getLogger(CMSService.class);

    String cmsUrl1 = "http://850627-web2.imperialm.com/imi-cms/content";
    String cmsUrl2 = "http://850627-web2.imperialm.com/imi-cms/content";
    
    @Value("${cms.mode}")
    String cmsContentType;
    
    ReloadableResourceBundleMessageSource cmsMessageSource;

    
    public void setCmsUrl1(String cmsUrl1) {
        this.cmsUrl1 = cmsUrl1;
    }

    public void setCmsUrl2(String cmsUrl2) {
        this.cmsUrl2 = cmsUrl2;
    }

    public void setCmsContentType(String cmsContentType) {
        this.cmsContentType = cmsContentType;
    }

    public void setCmsMessageSource(ReloadableResourceBundleMessageSource cmsMessageSource) {
        this.cmsMessageSource = cmsMessageSource;
    }

    /**
     * Get the IMI CMS content
     * 
     * The primary server is called first. If the request fails the secondary
     * server is attempted. If the secondary server fails also a default
     * message is returned.
     *
     * @param pathName
     * @param pageName
     * @return String (page content)
     */
    
    public String getContent(String pathName, String pageName) {
        final String URI = pathName + "/" + pageName;

        // get content from the primary CMS
        String url = this.getCmsUrl(true) + URI + "/" + this.cmsContentType;
        String content = this.requestContent(url);
        if (content == null) {
            // no content, get the content from the secondary CMS
            logger.error("***Failed getting IMI CMS content form primary: " + url);
            url = this.getCmsUrl(false) + URI + "/" + this.cmsContentType;
            content = this.requestContent(url);
        }
        if (content == null) {
            // again no content, return a default message
            logger.error("***Failed getting IMI CMS content form secondary: " + url);
            content = "IMI CMS content unavailable";
        }

        return content;
    }

    /**
     * Called the IMI CMS Restful service
     *
     * The Restful service requests a post method with an encrypted token.
     * The Restful server is called using the injected IMI CMS URL.
     *
     * @param url
     * @return
     */
    private String requestContent(String url) {
        String content = null;
        try {
            Client client = Client.create();
            client.setConnectTimeout(5000);    // 5 second timeout
            MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
            formData.add("token", this.getToken());

            logger.info("Requesting IMI CMS content: " + url);
            WebResource webResource = client.resource(url);
            Builder builder = webResource.type(MediaType.APPLICATION_FORM_URLENCODED);
            content = builder.post(String.class, formData);
        } catch (Exception e) {
        }

        return content;
    }

    /**
     * Read the imi-cms.properties file to determine which IMI CMS is the primary

     * @param primary (true for primary - false for secondary)
     * @return String (IMI CMS server URL)
     */
    private String getCmsUrl(boolean primary) {
        String cmsPrimary = "1";
        logger.debug("IMI CMS Primary set to: " + cmsPrimary);

        if (!cmsPrimary.equals("1") && !cmsPrimary.equals("2")) {
            cmsPrimary = "1";
        }

        if (primary == true) {
            if (cmsPrimary.equals("1")) {
                return this.cmsUrl1;
            } else {
                return this.cmsUrl2;
            }
        } else {
            if (cmsPrimary.equals("1")) {
                return this.cmsUrl2;
            } else {
                return this.cmsUrl1;
            }
        }
    }

    /**
     * Obtain an encrypted token to pass to the Restful IMI CMS service
     *
     * @return String (encrypted string)
     * @throws Exception
     */
    private String getToken() throws Exception {
        CmsToken cmsToken = new CmsToken();
        String token = cmsToken.create();
        if (logger.isDebugEnabled()) {
            logger.debug("Token: " + token);
            logger.debug("Is token valid: " + cmsToken.isValid(token));
        }

        return token;
    }

    
    public void reload() {
        logger.info("Beginning to clear ReloadableResourceBundleMessageSource cache");
        try {
            cmsMessageSource.clearCache();
        } catch (Exception e) {
            logger.info("***Failed clearing ReloadableResourceBundleMessageSource cache: " + e.toString());
            return;
        }
        logger.info("Finished clearing ReloadableResourceBundleMessageSource cache");
    }
	
}
