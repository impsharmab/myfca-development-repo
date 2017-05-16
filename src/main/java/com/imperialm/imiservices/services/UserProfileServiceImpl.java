package com.imperialm.imiservices.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.model.NoTile;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	//UR2.1
	private List<String> tile1 = new ArrayList<String>(Arrays.asList("01","02","07","08","09","13","14","16","17","19","20","22","23","27","2S","32","33","34","35","36","37","39","3S","40","46","47","49","4S","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ES","ET","LV","N1","N2","SC","SD","UB","VT"));
	//UR3.1
	private List<String> tile2 = new ArrayList<String>(Arrays.asList("46","47","49","4S","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB4.1
	private List<String> tile3 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","10","12","13","14","15","16","17","18","19","1F","20","22","23","24","25","26","27","28","29","2A","2B","2C","2D","2S","2T","30","31","32","33","34","35","36","37","38","39","3T","40","41","42","48","7M","7N","7P","7Q","ES","ET","IM"));
	//UB5.1
	private List<String> tile4 = new ArrayList<String>(Arrays.asList("46","47","49","4N","4S","4T","50","52","56","60","64","65","6W","70","71","74","75","79","7A","7L","80","81","82","83","84","85","87","88","89","8D","8E","8U","8V","90","94","97","98","99","CI","EA","EN","FL","LV","N1","N2","N3","N4","N5","N6","N7","N8","N9","SA","SB","SC","SD","SE","SF","U5","U6","UB","VT","WF","WP","01","02","03","04","05","06","07","10","17","19","1F","22","25","27","2C","2D","2S","2T","30","31","32","33","35","36","37","38","39","3T","40","41","7M","7N","7P","7Q"));
	//UB6.1
	private List<String> tile5 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","10","12","13","14","15","16","18","17","19","1F","20","22","23","24","25","26","27","28","29","2A","2B","2C","2D","2S","2T","30","31","32","33","34","35","36","37","38","39","3T","40","41","42","48","7M","7N","7P","7Q","EN","ES","ET","IM"));
	//UB7.1
	private List<String> tile6 = new ArrayList<String>(Arrays.asList("46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT","01","02","03","04","05","06","07","10","17","19","1F","22","25","27","2C","2D","2S","2T","30","31","32","33","35","36","37","38","39","3T","40","41","7M","7N","7P","7Q"));
	//UB 8.1
	private List<String> tile7 = new ArrayList<String>(Arrays.asList("46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT","01","02","03","04","05","06","07","10","17","19","1F","22","25","27","2C","2D","2S","2T","30","31","32","33","35","36","37","38","39","3T","40","41","7M","7N","7P","7Q"));
	//UB 9.1
	private List<String> tile8 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","10","12","13","14","15","16","18","17","19","1F","20","22","23","24","25","26","27","28","29","2A","2B","2C","2D","2S","2T","30","31","32","33","34","35","36","37","38","39","3T","40","41","42","48","7M","7N","7P","7Q","ES","ET","IM"));
	//UB 10.1
	private List<String> tile9 = new ArrayList<String>(Arrays.asList("46","47","49","4N","4S","4T","50","52","56","60","64","65","6W","70","71","74","75","79","7A","7L","80","81","82","83","84","85","87","88","89","8D","8E","8U","8V","90","94","97","98","99","CI","EA","EN","FL","LV","N1","N2","N3","N4","N5","N6","N7","N8","N9","SA","SB","SC","SD","SE","SF","U5","U6","UB","VT","WF","WP","01","02","03","04","05","06","07","10","17","19","1F","22","25","27","2C","2D","2S","2T","30","31","32","33","35","36","37","38","39","3T","40","41","7M","7N","7P","7Q"));
	//UB 11.1
	private List<String> tile10 = new ArrayList<String>(Arrays.asList("46","47","49","4N","4S","4T","50","52","56","60","64","65","6W","70","71","74","75","79","7A","7L","80","81","82","83","84","85","87","88","89","8D","8E","8U","8V","90","94","97","98","99","CI","EA","EN","FL","LV","N1","N2","N3","N4","N5","N6","N7","N8","N9","SA","SB","SC","SD","SE","SF","U5","U6","UB","VT","WF","WP","01","02","03","04","05","06","07","10","17","19","1F","22","25","27","2C","2D","2S","2T","30","31","32","33","35","36","37","38","39","3T","40","41","7M","7N","7P","7Q"));
	//UB 12.1
	private List<String> tile11 = new ArrayList<String>(Arrays.asList("01","02","13","14","17","19","22","27","2S","32","33","35","36","37","39","40","46","47","49","4S","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ES","LV","N1","N2","SC","SD","UB","VT"));
	//UB 13.1
	private List<String> tile12 = new ArrayList<String>(Arrays.asList("01","02","14","17","19","22","23","27","2S","32","33","35","36","37","39","40","46","47","49","4S","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ET","LV","N1","N2","SC","SD","UB","VT"));
	//UB 14.1
	private List<String> tile13 = new ArrayList<String>(Arrays.asList("46","47","49","4S","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 15.1
	private List<String> tile14 = new ArrayList<String>(Arrays.asList("46","47","49","4S","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 16.1
	private List<String> tile15 = new ArrayList<String>(Arrays.asList("01","02","13","14","17","19","22","27","2S","32","33","35","36","37","39","40","46","47","49","4S","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ES","LV","N1","N2","SC","SD","UB","VT"));
	//UB 17.1
	private List<String> tile16 = new ArrayList<String>(Arrays.asList("46","47","49","4S","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 18.1
	private List<String> tile17 = new ArrayList<String>(Arrays.asList("46","47","49","4S","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 19.1
	//private List<String> tile18 = new ArrayList<String>(Arrays.asList("01","02","13","14","19","22","27","2S","32","33","35","36","37","39","40","46","47","49","4S","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ES","LV","N1","N2","SC","SD","UB","VT"));
	//UB 20.1
	private List<String> tile19 = new ArrayList<String>(Arrays.asList("01","02","13","14","19","2S","33","35","36","37","39","40","7M","7N","7P","7Q","ES","ET","17","22","27","32"));
	//21.1
	private List<String> tile20 = new ArrayList<String>(Arrays.asList("46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 22.1 SUMMARY table
	private List<String> tile21 = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","10","11","12","13","14","16","17","19","20","1F","21","22","23","24","25","26","27","28","2C","2D","2S","2T","30","31","32","33","34","35","35","36","37","38","39","3S","3T","40","41","46","47","49","4S","50","56","60","65","6W","70","71","74","7L","7M","7N","7P","7Q","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","ES","ET","LV","N1","N2","SC","SD","UB","VT"));
	//UB 23.1
	private List<String> tile22 = new ArrayList<String>(Arrays.asList("01","02","22","36","37","39","46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 24.1
	private List<String> tile23 = new ArrayList<String>(Arrays.asList("01","02","22","36","37","39","46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 25.1
	private List<String> tile24 = new ArrayList<String>();
	//UB 26.1
	private List<String> tile25 = new ArrayList<String>(Arrays.asList("01","02","22","36","37","39","46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT","03","04","05","06","07","08","09","10","11","17","19","1F","26","28","2C","2D","2S","2T","31","32","33","34","35","38","3T","40","41","7M","7N","7P","7Q"));
	//UB 27.1
	private List<String> tile26 = new ArrayList<String>(Arrays.asList("46","47","49","4S","50","56","60","65","6W","70","71","74","7L","82","83","84","87","88","89","8D","8E","8U","90","94","97","98","99","CI","EA","EN","LV","N1","N2","SC","SD","UB","VT"));
	//UB 28.1
	private List<String> tile27 = new ArrayList<String>();
	//UB 29.1
	private List<String> tile28 = new ArrayList<String>();
	//UB 30.1
	private List<String> tile29 = new ArrayList<String>();
	
	private List<NoTile> noTileList = new ArrayList<NoTile>();
	
	private List<NoTile> userTiles = new ArrayList<NoTile>();
	
	private final String MSER_SITE = "http://uat.imperialmarketing.com/mser/dashSSO.do?token=";
	private final String MSER_RULES = "assets/pdf/MSERProgramRulesv15_9_17.pdf";
	private final String BRAINBOOST_SITE = "https://brainboost.meap.me/";
	private final String BRAINBOOST_RULES = "https://brainboost.meap.me/";
	private final String RETENTION_RULES = "assets/pdf/RetentionPartsManagerUSA.pdf";
	
	
	
	public List<NoTile> getuserTiles(String positionCode, String roleId, UserDetailsImpl user, String token, String pc, String dc) {
		this.userTiles = new ArrayList<NoTile>();
		if(tile1.contains(positionCode)){
		this.userTiles.add(new NoTile(2,"","tile", "","mser-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc, MSER_RULES));
		}
		
		if(tile2.contains(positionCode) || user.getUserId().toLowerCase().equals("dave") || user.getUserId().toLowerCase().equals("T1894DS".toLowerCase()) || user.getUserId().toLowerCase().equals("T0725BH".toLowerCase())){
		this.userTiles.add(new NoTile(19,"","chart", "","mser-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc, MSER_RULES));
		}
		
		if(user.getUserId().toLowerCase().equals("dave") || user.getUserId().toLowerCase().equals("T1894DS".toLowerCase()) || user.getUserId().toLowerCase().equals("T0725BH".toLowerCase())){
			this.userTiles.add(new NoTile(3,"","tile", "","mser-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(4,"","tile", "","VehicleProtection.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(5,"","tile", "","Magenti.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(6,"","tile", "","parts-counter.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(7,"","tile", "","Expresslane.jpg","hhttp://uat.imperialmarketing.com/mser/dashSSO.do?token=" + token + "&positioncode=" + pc + "&dealercode=",MSER_RULES));
			this.userTiles.add(new NoTile(18,"","tile", "","wiadvisor-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(8,"","tile", "","uconnect-logo.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
			this.userTiles.add(new NoTile(37,"","tile", "","MSEREnrollment.jpg",MSER_SITE + token + "&positioncode=" + pc + "&dealercode=" + dc,MSER_RULES));
		}
		
		
		if(tile3.contains(positionCode)){
			this.userTiles.add(new NoTile(25,"","tile", "","cert-pro-banner.jpg","","assets/pdf/2017FCACPRulesandProgramOverview.pdf"));
			}
		if(tile4.contains(positionCode)){
			this.userTiles.add(new NoTile(13,"","chart", "","cert-pro-banner.jpg","","assets/pdf/2017FCACPRulesandProgramOverview.pdf"));
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
			this.userTiles.add(new NoTile(28,"","tile", "","cert-pro-experts.jpg","",""));
			}
		
		if(tile9.contains(positionCode)){
			this.userTiles.add(new NoTile(11,"","chart", "","cert-pro-experts-ram.jpg","","assets/pdf/RAMExpert.pdf"));
			}
		
		if(tile10.contains(positionCode)){
			this.userTiles.add(new NoTile(10,"","chart", "","cert-pro-experts-ram.jpg","","assets/pdf/RAMExpert.pdf"));
			}
		
		if(tile11.contains(positionCode)){
			this.userTiles.add(new NoTile(14,"","tile", "","topadvisor.jpg","https://www.mopartopadvisor.com/topAdvisor/index.htm","assets/pdf/2017MoparTTTAProgramRulesFINAL.pdf"));
			}
		
		
		if(tile12.contains(positionCode)){
			this.userTiles.add(new NoTile(15,"","tile", "","toptech.jpg","https://www.mopartoptech.com/toptech/index.htm","assets/pdf/2017MoparTTTAProgramRulesFINAL.pdf"));
			}
		
		/*if(tile13.contains(positionCode)){
			this.userTiles.add(new NoTile(16,"","chart", "","toptech-topadv.jpg","www.mopar.com"));
			}*/
		//enrolled not enrolled 2 tiles
		if(tile14.contains(positionCode)){
			this.userTiles.add(new NoTile(34,"","chart", "","toptech-topadv.jpg","https://www.mopartopadvisor.com","assets/pdf/2017MoparTTTAProgramRulesFINAL.pdf"));
			this.userTiles.add(new NoTile(35,"","chart", "","toptech-topadv.jpg","https://www.mopartopadvisor.com","assets/pdf/2017MoparTTTAProgramRulesFINAL.pdf"));
			//this.userTiles.add(new NoTile(16,"","chart", "","toptech-topadv.jpg",""));
			//this.userTiles.add(new NoTile(17,"","chart", "","toptech-topadv.jpg",""));
			}
		
		if(tile15.contains(positionCode)){
			this.userTiles.add(new NoTile(29,"","tile", "","service-incentive.jpg","","assets/pdf/2017Q2-Q4ServiceIncentiveRewardsProgramDetailsv5.pdf")); //SERVICE INCENTIVE
			}
		
		
		if(tile16.contains(positionCode)){
			this.userTiles.add(new NoTile(20,"","chart", "","service-incentive.jpg","","assets/pdf/2017Q2-Q4ServiceIncentiveRewardsProgramDetailsv5.pdf")); // SERVICE INCENTIVE
			}
		
		if(tile17.contains(positionCode)){
			this.userTiles.add(new NoTile(22,"","chart", "","service-incentive.jpg","","assets/pdf/2017Q2-Q4ServiceIncentiveRewardsProgramDetailsv5.pdf")); // SERVICE INCENTIVE
			}
		
		/*if(tile18.contains(positionCode)){ 
			this.userTiles.add(new NoTile(21,"","chart", "","service-incentive.jpg","")); // SERVICE INCENTIVE
			}*/
		
		if(tile19.contains(positionCode)){ 
			NoTile rr = new NoTile(24,"","tile", "","rewards.jpg","http://sand.myfcarewards.m2.humanelementdev.com/he_singlesignon?token=" + token,"");
			rr.setProgramSite("Shop Now");
			this.userTiles.add(rr); // REWARDRED
			}
		
		if(tile20.contains(positionCode) || user.getUserId().toLowerCase().equals("T0725BH".toLowerCase())){ 
			NoTile rr = new NoTile(23,"","chart", "","rewards.jpg","http://sand.myfcarewards.m2.humanelementdev.com/he_singlesignon?token=" + token,"");
			rr.setProgramSite("Shop Now");
			//this.userTiles.add(new NoTile(23,"","chart", "","rewards.jpg","https://fcarewardredemption.com","#")); // REWARDRED
			this.userTiles.add(rr);
			}
		
		// SUMMARY
		if(tile21.contains(positionCode)){ 
			this.userTiles.add(new NoTile(36,"","chart", "","FCASummary.jpg","","")); // SUMMARY
			}
			
			//Customer first
			if(tile22.contains(positionCode)){ 
			this.userTiles.add(new NoTile(32,"","chart", "","customer-first.png","https://fca.track360.com/landingpage/","assets/pdf/CFAFEDealerAnnouncementProgramRules.pdf")); // //customer first
			}
			
			//customer first
			if(tile23.contains(positionCode)){ 
			this.userTiles.add(new NoTile(33,"","chart", "","customer-first.png","https://fca.track360.com/landingpage/","assets/pdf/CFAFEDealerAnnouncementProgramRules.pdf")); // //customer first
			}
			/*
			//NOTHING
			if(tile24.contains(positionCode)){ 
			this.userTiles.add(new NoTile(23,"","chart", "","mser-logo.jpg","www.mopar.com")); // MVP
			}
			
		*/
		if(tile25.contains(positionCode) || user.getUserId().toLowerCase().equals("dave") || user.getUserId().toLowerCase().equals("T1894DS".toLowerCase()) || user.getUserId().toLowerCase().equals("T0725BH".toLowerCase())){ 
			this.userTiles.add(new NoTile(30,"","tile", "","Retention.jpg","",RETENTION_RULES)); // RETENTION
			}
			
			if(tile26.contains(positionCode) || user.getUserId().toLowerCase().equals("dave") || user.getUserId().toLowerCase().equals("T1894DS".toLowerCase()) || user.getUserId().toLowerCase().equals("T0725BH".toLowerCase())){ 
			this.userTiles.add(new NoTile(31,"","chart", "","Retention.jpg","",RETENTION_RULES)); // RETENTION
			}
		
		return userTiles;
	}

}
