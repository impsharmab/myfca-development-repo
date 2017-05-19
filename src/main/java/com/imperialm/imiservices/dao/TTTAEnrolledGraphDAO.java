package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;

public interface TTTAEnrolledGraphDAO {
	
	public static String SELECT_BY_PARENT = "SELECT [Parent] 'parent', [Child] 'child', [Total] 'enrolled' ,[Total Group A] 'totalGroupA', [Total Group B] 'totalGroupB', [Total Group C] 'totalGroupC', [Total Group D] 'totalGroupD' ,[Total Group E] 'totalGroupE' FROM [GroupTTTAEnrolledGraph] where Parent IN (?0) and toggle = 'Enrolled' order by [Parent], [Child]";
	public static String SELECT_BY_TERRITORY = "SELECT [Parent] 'parent', [Child] 'child', [Total] 'enrolled' ,[Total Group A] 'totalGroupA', [Total Group B] 'totalGroupB', [Total Group C] 'totalGroupC', [Total Group D] 'totalGroupD' ,[Total Group E] 'totalGroupE' FROM [GroupTTTAEnrolledGraph] where Child like ?0 and toggle = 'Enrolled' order by [Parent], [Child]";
	public static String SELECT_DEALERCOUNT_NAT = "SUM([Total]) where Parent = 'NAT' and toggle = 'Enrolled'";
	public static String SELECT_DEALERCOUNT_BY_CHILD = "SUM([Total]) where Child = ?0 and toggle = 'Enrolled'";
	
	
	
	public static String SELECT_BY_PARENT_NOT_ENROLLED = "SELECT [Parent] 'parent', [Child] 'child', [Total] 'enrolled' ,[Total Group A] 'totalGroupA', [Total Group B] 'totalGroupB', [Total Group C] 'totalGroupC', [Total Group D] 'totalGroupD' ,[Total Group E] 'totalGroupE' FROM [GroupTTTAEnrolledGraph] where Parent IN (?0) and toggle = 'Not Enrolled' order by [Parent], [Child]";
	public static String SELECT_BY_TERRITORY_NOT_ENROLLED = "SELECT [Parent] 'parent', [Child] 'child', [Total] 'enrolled' ,[Total Group A] 'totalGroupA', [Total Group B] 'totalGroupB', [Total Group C] 'totalGroupC', [Total Group D] 'totalGroupD' ,[Total Group E] 'totalGroupE' FROM [GroupTTTAEnrolledGraph] where Child like ?0 and toggle = 'Not Enrolled' order by [Parent], [Child]";
	public static String SELECT_DEALERCOUNT_NAT_NOT_ENROLLED = "SUM([Total]) where Parent = 'NAT' and toggle = 'Not Enrolled'";
	public static String SELECT_DEALERCOUNT_BY_CHILD_NOT_ENROLLED = "SUM([Total]) where Child = ?0 and toggle = 'Not Enrolled'";
	
	
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritory(List<String> territory);
	public List<TTTAEnrolledGraphDTO>  getTTTAEnrolledByChildTerritory(String list);
	public int getTTTAEnrolledDealersNAT();
	public int getTTTAEnrolledBCORDistrict(String territory);
	
	
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritoryNotEnrolled(List<String> territory);
	public List<TTTAEnrolledGraphDTO>  getTTTAEnrolledByChildTerritoryNotEnrolled(String list);
	public int getTTTAEnrolledDealersNATNotEnrolled();
	public int getTTTAEnrolledBCORDistrictNotEnrolled(String territory);
	

}
