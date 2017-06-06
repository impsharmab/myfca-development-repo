package com.imperialm.imiservices.dao;

import com.imperialm.imiservices.dto.TTTAEnrolledDTO;

import java.util.List;

public interface TTTAEnrolledDAO {
	
	public static final String BC_ENROLLED_DATA = "SELECT [Territory] 'territory' ,[Enrollment Status] 'enrollmentStatus' ,[Group a] 'groupA' ,[Group b] 'groupB' ,[Group c] 'groupC' ,[Group d] 'groupD' ,[Group e] 'groupE' , '' as error FROM [TTTA Enrolled] where [Territory] IN ('CA', 'SE', 'NE', 'WE', 'GL', 'MA', 'SW', 'MW', 'DN') AND [Enrollment Status] = 'E'";
	public static final String BC_NOT_ENROLLED_DATA = "SELECT [Territory] 'territory' ,[Enrollment Status] 'enrollmentStatus' ,[Group a] 'groupA' ,[Group b] 'groupB' ,[Group c] 'groupC' ,[Group d] 'groupD' ,[Group e] 'groupE' , '' as error FROM [TTTA Enrolled] where [Territory] IN ('CA', 'SE', 'NE', 'WE', 'GL', 'MA', 'SW', 'MW', 'DN') AND [Enrollment Status] = 'N'";
	
	public List<TTTAEnrolledDTO> getTTTAEnrollmentsBC(boolean enrolled);
}
