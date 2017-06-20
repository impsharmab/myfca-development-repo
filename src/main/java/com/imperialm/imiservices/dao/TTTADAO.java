package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.TTTADTO;
import com.imperialm.imiservices.dto.request.InputRequest;

import java.util.List;

public interface TTTADAO {
	public static String TTTA_BY_ROLE = "select TOP 5 ttta.Territory, [Group a] 'groupA', [Group b] 'groupB', [Group c] 'groupC', [Group d] 'groupD', [Group e] 'groupE', [Enrollment Status] 'enrollmentStatus' from [TTTA Enrolled] TTTA";
	
	//public static String EARNING_BY_ROLE = "select * from [MSER Earnings]";

	public List<TTTADTO> getTTTAByRole(InputRequest userRoleReq);

}
