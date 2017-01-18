package com.imperialm.imiservices.dao;

import java.util.List;
import com.imperialm.imiservices.dto.MSEREarningsDTO;
import com.imperialm.imiservices.dto.request.InputRequest;

public interface MSEREarningsDAO {
	public static String EARNING_BY_ROLE = "select TOP 5 ER.Territory, [Mopar Parts] 'moparParts', mvp, [Magneti Marelli] 'magnetiMarelli', [Parts Counter] 'partsCounter', [Express Lane] 'expressLane', wiAdvisor, uConnect  from [MSER Earnings] ER ORDER BY NEWID()";
	
	//public static String EARNING_BY_ROLE = "select * from [MSER Earnings]";

	public List<MSEREarningsDTO> getEarningsByRole(InputRequest userRoleReq);
}
