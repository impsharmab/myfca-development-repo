package com.imperialm.imiservices.rest;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imperialm.imiservices.util.IMIServicesUtil;

/**
 *
 * @author Dheerajr
 *
 */
@RestController
public class ResourceLoadController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceLoadController.class);

	@Value("${images.shared.folder}")
	private String imagesSharedFolder;

	/**
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/services/loadrsc", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@RequestParam("id") final String fileName) throws IOException {
		RandomAccessFile randonAccFile = null;
		final HttpHeaders headers = new HttpHeaders();
		byte[] byteArray = null;
		try {
			randonAccFile = new RandomAccessFile(imagesSharedFolder + fileName, "r");
			byteArray = new byte[(int) randonAccFile.length()];
			randonAccFile.readFully(byteArray);
			headers.setContentType(getMediaType(fileName));
			randonAccFile.close();
			return new ResponseEntity<>(byteArray, headers, HttpStatus.CREATED);
		} catch (final Exception ex) {
			logger.error("error while retriving the file", ex);
			byteArray = IMIServicesUtil.prepareJson("error",
					"unable to load requested resource... Please check the resource name and try agian..."
							+ FilenameUtils.getName(ex.getMessage().split("\\(")[0].trim()))
					.getBytes();
			randonAccFile = null;
		}
		return new ResponseEntity<>(byteArray, headers, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private MediaType getMediaType(final String fileName) {
		final String fileExtention = FilenameUtils.getExtension(fileName).toUpperCase();
		MediaType toRetrun = null;
		if ("JPG".equals(fileExtention)) {
			toRetrun = MediaType.IMAGE_JPEG;
		} else if ("PNG".equals(fileExtention)) {
			toRetrun = MediaType.IMAGE_PNG;
		} else if ("GIF".equals(fileExtention)) {
			toRetrun = MediaType.IMAGE_GIF;
		} else if ("PDF".equals(fileExtention)) {
			toRetrun = MediaType.APPLICATION_PDF;
		}
		return toRetrun;
	}
}
