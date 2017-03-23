package com.imperialm.imiservices.dao;

import java.util.List;

import com.imperialm.imiservices.dto.TTTAEnrolledGraphDTO;

public interface TTTAEnrolledGraphDAO {
	
	public static String SELECT_BY_PARENT = "SELECT [Parent] 'parent', [Child] 'child', [Enrolled] 'enrolled' ,[Total Group A] 'totalGroupA', [Total Group B] 'totalGroupB', [Total Group C] 'totalGroupC', [Total Group D] 'totalGroupD' ,[Total Group E] 'totalGroupE' FROM [dbo].[TTTAEnrolledGraph] where Parent IN (?0)";
	public static String SELECT_BY_TERRITORY = "SELECT [Parent] 'parent', [Child] 'child', [Enrolled] 'enrolled' ,[Total Group A] 'totalGroupA', [Total Group B] 'totalGroupB', [Total Group C] 'totalGroupC', [Total Group D] 'totalGroupD' ,[Total Group E] 'totalGroupE' FROM [dbo].[TTTAEnrolledGraph] where Child IN (?0)";
	public static String SELECT_DEALERCOUNT_NAT = "SUM([ENROLLED]) where Parent = 'NAT'";
	public static String SELECT_DEALERCOUNT_BY_CHILD = "SUM([ENROLLED]) where Child = ?0";
	public List<TTTAEnrolledGraphDTO> getTTTAEnrolledByParentTerritory(List<String> territory);
	public List<TTTAEnrolledGraphDTO>  getTTTAEnrolledByChildTerritory(List<String> list);
	public int getTTTAEnrolledDealersNAT();
	public int getTTTAEnrolledBCORDistrict(String territory);

}
