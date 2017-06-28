package com.imperialm.imiservices.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.model.NoTile;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	//UR2.1
	private List<String> tile1 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","07","08","09","11","12","13","14","16","17","19","20","22","23","25","27","29","2S","30","32","33","34","35","36","37","39","3S","40","46","47","52","56","60","70","80","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","ES","ET","LV","SB","SC","IDS","IEX","IBC","IDT","IAD"));
	//UR3.1
	private List<String> tile2 = new ArrayList<String>(Arrays.asList("46","47","56","60","70","80","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IDS","IEX","IBC","IDT","IAD"));
	//UB4.1
	private List<String> tile3 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","12","13","14","17","18","20","22","23","24","25","27","29","32","33","35","36","37","40","41","42"));
	//UB5.1
	private List<String> tile4 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","17","1F","22","25","27","2T","32","33","35","36","37","3T","40","41","46","47","4T","50","56","60","70","80","81","82","83","84","89","8D","8E","8U","8V","90","94","97","99","EA","EN","FL","LV","SB","SC","U5","U6","IEX","IBC","IJM","IDT","IAD"));
	//UB6.1
	private List<String> tile5 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","12","13","14","17","18","20","22","23","24","25","27","29","32","33","35","36","37","40","41","42"));
	//UB7.1
	private List<String> tile6 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","17","1F","22","25","27","2T","32","33","35","36","37","3T","40","41","46","47","50","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","U5","U6","IEX","IBC","IJM","IDT","IAD"));
	//UB 8.1
	private List<String> tile7 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","17","1F","22","25","27","2T","32","33","35","36","37","3T","40","41","46","47","50","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","U5","U6","IEX","IBC","IJM","IDT","IAD"));
	//UB 9.1
	private List<String> tile8 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","12","22","25","36","37","41","42"));
	//UB 10.1
	private List<String> tile9 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","1F","22","25","2T","36","37","3T","41","46","47","4T","50","56","60","70","80","81","82","83","84","89","8D","8E","8U","8V","90","94","97","99","EA","EN","FL","LV","SB","SC","U5","U6","IEX","IBC","IJM","IDT","IAD"));
	//UB 11.1
	private List<String> tile10 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","1F","22","25","2T","36","37","3T","41","46","47","4T","50","56","60","70","80","81","82","83","84","89","8D","8E","8U","8V","90","94","97","99","EA","EN","FL","LV","SB","SC","U5","U6","IEX","IBC","IJM","IDT","IAD"));
	//UB 12.1
	private List<String> tile11 = new ArrayList<String>(Arrays.asList("01","02","09","13","14","17","19","22","27","2S","32","33","35","36","37","39","40","46","47","4T","52","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 13.1
	private List<String> tile12 = new ArrayList<String>(Arrays.asList("01","02","09","14","17","18","19","22","23","27","2S","32","33","35","36","37","39","40","46","47","4T","52","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 14.1
	private List<String> tile13 = new ArrayList<String>(Arrays.asList("46","47","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 15.1
	private List<String> tile14 = new ArrayList<String>(Arrays.asList("46","47","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 16.1
	private List<String> tile15 = new ArrayList<String>(Arrays.asList("01","02","09","13","17","22","27","2S","32","33","35","36","37","39","46","47","4T","52","56","60","70","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","ES","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 17.1
	private List<String> tile16 = new ArrayList<String>(Arrays.asList("46","47","4T","56","60","70","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 18.1
	private List<String> tile17 = new ArrayList<String>(Arrays.asList("46","47","4T","56","60","70","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IDT","IAD"));
	//UB 19.1
	//private List<String> tile18 = new ArrayList<String>(Arrays.asList("01","02","13","14","19","22","27","2S","32","33","35","36","37","39","40","46","47","49","4S","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ES","LV","N1","N2","SC","SD","UB","VT"));
	//UB 20.1
	private List<String> tile19 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","12","13","14","17","18","20","22","23","24","25","27","29","32","33","35","36","37","40","41","42"));
	//21.1
	private List<String> tile20 = new ArrayList<String>(Arrays.asList("46","47","4T","50","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IJM","IDT","IAD"));
	//UB 22.1 SUMMARY table
	private List<String> tile21 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","10","11","12","13","14","16","17","19","1F","20","22","23","24","25","26","27","28","2S","2T","30","31","32","33","34","35","36","37","38","39","3S","3T","40","41","46","47","4T","50","52","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","ES","ET","LV","SB","SC","IEX","IBC","IJM","IBS","IDT","IAD"));
	//UB 23.1
	private List<String> tile22 = new ArrayList<String>(Arrays.asList("01","02","22","36","37","39","46","47","4T","50","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IJM","IBS","IDT","IAD"));
	//UB 24.1
	private List<String> tile23 = new ArrayList<String>(Arrays.asList("01","02","22","36","37","39","46","47","4T","50","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IEX","IBC","IJM","IBS","IDT","IAD"));
	//UB 25.1
	//private List<String> tile24 = new ArrayList<String>(); // this was empty, is empty
	//UB 26.1
	private List<String> tile25 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","17","19","1F","22","25","2S","2T","31","32","33","35","36","37","38","39","3T","40","41","46","47","50","52","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IDS","IEX","IBC","IJM","IBS","IDT","IAD"));
	//UB 27.1
	private List<String> tile26 = new ArrayList<String>(Arrays.asList("46","47","50","56","60","70","82","83","84","89","8D","8E","8U","90","94","97","99","EA","EN","LV","SB","SC","IDS","IEX","IBC","IJM","IBS","IDT","IAD"));
	//UB 28.1
	//private List<String> tile27 = new ArrayList<String>(); // empty
	//UB 29.1
	//private List<String> tile28 = new ArrayList<String>();// empty
	//UB 30.1
	//private List<String> tile29 = new ArrayList<String>();// empty

	//ALL MSER SUMMARY DAVE SKIFF TILES
	private List<String> tile100 = new ArrayList<String>(Arrays.asList("IDS","IAD"));

	private List<NoTile> userTiles = new ArrayList<NoTile>();

	@Value("${cms.link.MSERSITE}")
	private String MSER_SITE;
	
	@Value("${cms.link.MSERRULES}")
	private String MSER_RULES;
	
	@Value("${cms.link.BRAINBOOSTSITE}")
	private String BRAINBOOST_SITE;
	
	@Value("${cms.link.BRAINBOOSTRULES}")
	private String BRAINBOOST_RULES;
	
	@Value("${cms.link.RETENTIONRULES}")
	private String RETENTION_RULES;
	
	@Value("${cms.link.SUMMARYRULES}")
	private String SUMMARY_RULES;
	
	@Value("${cms.link.TOPADVISORSITE}")
	private String TOPADVISOR_SSO;
	
	@Value("${cms.link.TOPTECHSITE}")
	private String TOPTECH_SSO;
	
	@Value("${cms.link.CERTIFIEDPRORULES}")
	private String CERTIFIEDPRORULES;
	
	@Value("${cms.link.SHOPNOW}")
	private String SHOPNOW;
	
	@Value("${cms.link.CERTIFIEDEXPERTS}")
	private String CERTEXPERT;
	
	@Value("${cms.link.TOPADVISORRULES}")
	private String TOPADVISORRULES;
	
	@Value("${cms.link.SIREWARDSRULES}")
	private String SIREWARDSRULES;
	
	@Value("${cms.link.CUSTOMERFIRSTRULES}")
	private String CUSTOMERFIRSTRULES;
	
	@Value("${cms.link.CUSTOMERFIRSTSITE}")
	private String CUSTOMERFIRSTSITE;


	public List<NoTile> getuserTiles(String positionCode, String roleId, UserDetailsImpl user, String token, String pc, String dc) {
		this.userTiles = new ArrayList<NoTile>();
		if(tile1.contains(positionCode)){
			this.userTiles.add(new NoTile(2,"","tile", "","mser-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc, MSER_RULES));
		}

		if(tile2.contains(positionCode)){
			this.userTiles.add(new NoTile(19,"","chart", "","mser-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc, MSER_RULES));
		}

		if(tile100.contains(positionCode)){
			this.userTiles.add(new NoTile(3,"","tile", "","mser-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(4,"","tile", "","VehicleProtection.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(5,"","tile", "","Magenti.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(6,"","tile", "","parts-counter.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(7,"","tile", "","Expresslane.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=",MSER_RULES));
			this.userTiles.add(new NoTile(18,"","tile", "","wiadvisor-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(8,"","tile", "","uconnect-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(37,"","tile", "","MSEREnrollment.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
		}

		//cert-pro-banners
		if(tile3.contains(positionCode)){
			this.userTiles.add(new NoTile(25,"","tile", "","cert-pro-experts.jpg","",CERTIFIEDPRORULES));
		}
		if(tile4.contains(positionCode)){
			this.userTiles.add(new NoTile(13,"","chart", "","cert-pro-experts.jpg","",CERTIFIEDPRORULES));
		}

		if(tile5.contains(positionCode)){
			this.userTiles.add(new NoTile(26,"","tile", "","brainboost.jpg",BRAINBOOST_SITE,BRAINBOOST_RULES));
		}
		if(tile6.contains(positionCode)){
			this.userTiles.add(new NoTile(9,"","chart", "","brainboost.jpg",BRAINBOOST_SITE,BRAINBOOST_RULES));
		}

		if(tile7.contains(positionCode)){
			this.userTiles.add(new NoTile(12,"","chart", "","brainboost.jpg",BRAINBOOST_SITE,BRAINBOOST_RULES));
		}

		if(tile8.contains(positionCode)){
			this.userTiles.add(new NoTile(28,"","tile", "","cert-pro-experts-ram.jpg","",CERTEXPERT));
		}

		if(tile9.contains(positionCode)){
			this.userTiles.add(new NoTile(11,"","chart", "","cert-pro-experts-ram.jpg","",CERTEXPERT));
		}

		if(tile10.contains(positionCode)){
			this.userTiles.add(new NoTile(10,"","chart", "","cert-pro-experts-ram.jpg","",CERTEXPERT));
		}

		if(tile11.contains(positionCode)){
			this.userTiles.add(new NoTile(14,"","tile", "","topadvisor.jpg",TOPADVISOR_SSO + token + "&positioncode=" + pc + "&dealercode=" + dc,"assets/pdf/2017MoparTTTAProgramRulesFINAL.pdf"));
		}


		if(tile12.contains(positionCode)){
			this.userTiles.add(new NoTile(15,"","tile", "","toptech.jpg",TOPTECH_SSO + token + "&positioncode=" + pc + "&dealercode=" + dc,"assets/pdf/2017MoparTTTAProgramRulesFINAL.pdf"));
		}

		if(tile13.contains(positionCode)){
			this.userTiles.add(new NoTile(34,"","chart", "","toptech-topadv.jpg","https://www.mopartopadvisor.com",TOPADVISORRULES));
		}
		
		if(tile14.contains(positionCode)){
			this.userTiles.add(new NoTile(35,"","chart", "","toptech-topadv.jpg","https://www.mopartopadvisor.com",TOPADVISORRULES));
		}

		if(tile15.contains(positionCode)){
			this.userTiles.add(new NoTile(29,"","tile", "","service-incentive.jpg","",SIREWARDSRULES)); //SERVICE INCENTIVE
		}


		if(tile16.contains(positionCode)){
			this.userTiles.add(new NoTile(20,"","chart", "","service-incentive.jpg","",SIREWARDSRULES)); // SERVICE INCENTIVE
		}

		if(tile17.contains(positionCode)){
			this.userTiles.add(new NoTile(22,"","chart", "","service-incentive.jpg","",SIREWARDSRULES)); // SERVICE INCENTIVE
		}

		/*if(tile18.contains(positionCode)){ 
			this.userTiles.add(new NoTile(21,"","chart", "","service-incentive.jpg","")); // SERVICE INCENTIVE
			}*/

		if(tile19.contains(positionCode)){ 
			NoTile rr = new NoTile(24,"","tile", "","rewards.jpg",SHOPNOW + token,"");
			rr.setProgramSite("Shop Now");
			this.userTiles.add(rr); // REWARDRED
		}

		if(tile20.contains(positionCode)){ 
			NoTile rr = new NoTile(23,"","chart", "","rewards.jpg",SHOPNOW + token,"");
			rr.setProgramSite("Shop Now");
			// REWARDRED
			this.userTiles.add(rr);
		}

		// SUMMARY
		if(tile21.contains(positionCode)){ 
			NoTile rr = new NoTile(36,"","chart", "","FCASummary.jpg","",SUMMARY_RULES);
			rr.setProgramRules("Tile Information");
			this.userTiles.add(rr);
		}

		//Customer first
		if(tile22.contains(positionCode)){ 
			this.userTiles.add(new NoTile(32,"","chart", "","customer-first.png",CUSTOMERFIRSTSITE,CUSTOMERFIRSTRULES)); // //customer first
		}

		//customer first
		if(tile23.contains(positionCode)){ 
			this.userTiles.add(new NoTile(33,"","chart", "","customer-first.png",CUSTOMERFIRSTSITE,CUSTOMERFIRSTRULES)); // //customer first
		}
		/*
			//NOTHING
			if(tile24.contains(positionCode)){ 
			this.userTiles.add(new NoTile(23,"","chart", "","mser-logo.jpg","www.mopar.com")); // MVP
			}

		 */
		if(tile25.contains(positionCode)){ 
			// RETENTION
			NoTile rr = new NoTile(30,"","tile", "","Retention.jpg","",RETENTION_RULES);
			rr.setProgramRules("Tile Information");
			this.userTiles.add(rr);
		}

		if(tile26.contains(positionCode)){ 
			// RETENTION
			NoTile rr = new NoTile(31,"","chart", "","Retention.jpg","",RETENTION_RULES);
			rr.setProgramRules("Tile Information");
			this.userTiles.add(rr);
		}

		return userTiles;
	}

}
