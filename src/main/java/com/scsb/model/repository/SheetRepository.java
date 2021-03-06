package com.scsb.model.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scsb.model.Sheet;

public interface SheetRepository extends JpaRepository<Sheet, String>
{
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ON_TIME < ?1 AND OFF_TIME > ?1 AND TYPE = ?2 AND (STATUS = ?3 OR STATUS = ?4)", nativeQuery = true)
	List<Sheet> getIndexBanners(LocalDateTime time, String type, String processing, String passed);
	
	//管理者取所有表單
	@Query(value = "SELECT * FROM SCSB_SHEET ", nativeQuery = true)
	List<Sheet> getAllSheets();
	
	//管理者依條件取表單
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE "
					+" APPLICANT LIKE %?1%"
					+" AND TYPE LIKE %?2%"
					+" AND STATUS LIKE %?3%"
			, nativeQuery = true)
	List<Sheet> getSearchSheets(String name, String type, String status);
	
	//取跟自己有關的表單
	@Query(value =	" SELECT * FROM SCSB_SHEET LEFT JOIN SCSB_SHEET_APPROVAL ON SCSB_SHEET.ID = SCSB_SHEET_ID WHERE SCSB_SHEET.APPLICANT_ID = ?1 ORDER BY SCSB_SHEET_APPROVAL.CREATE_TIME ASC ", nativeQuery = true)
	List<Sheet> getSheetByApplicant(String memberId);

	//依員工編號&條件取得表單
	@Query(value =	" SELECT * FROM SCSB_SHEET LEFT JOIN SCSB_SHEET_APPROVAL ON SCSB_SHEET.ID = SCSB_SHEET_ID "
			        + "WHERE SCSB_SHEET.APPLICANT_ID = NVL(?1, SCSB_SHEET.APPLICANT_ID)"
			        + "AND (UPPER(SCSB_SHEET.APPLICANT_ID) LIKE %?2% OR UPPER(SCSB_SHEET.APPLICANT) LIKE %?2%)"
					+" AND SCSB_SHEET.TYPE = NVL(?3, SCSB_SHEET.TYPE) "
					+" AND SCSB_SHEET.STATUS LIKE %?4% "
//					+" AND (SCSB_SHEET.TITLE LIKE NVL(?5, SCSB_SHEET.TITLE) OR SCSB_SHEET.CONTENT LIKE NVL(?5, SCSB_SHEET.CONTENT))"
					+" AND SCSB_SHEET.CREATE_TIME >= NVL(?5, SCSB_SHEET.CREATE_TIME)"
					+" AND SCSB_SHEET.CREATE_TIME <= NVL(?6, SCSB_SHEET.CREATE_TIME)"
					+ " ORDER BY SCSB_SHEET_APPROVAL.CREATE_TIME ASC ", nativeQuery = true)
	List<Sheet> getSheetAndConditionByApplicant(String memberId,String name, String type, String status, Date date, Date date2);
	
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE ID = ?1", nativeQuery = true)
	Sheet  getSheetById(int id);
	
	// 依員工編號與狀態取得表單
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE STATUS = ?1 AND ( NEXT_APPROVER_ID = ?2 OR AGENT_ID = ?2 )", nativeQuery = true)
	List<Sheet> getSheetListByStatusAndCn(String status, String Cn);
	
	@Query(value = "SELECT * FROM SCSB_SHEET WHERE STATUS in (?1)", nativeQuery = true)
	List<Sheet> getSheetByStatus(List<String> status);

	//管理者依條件取表單
		@Query(value = "SELECT * FROM SCSB_SHEET WHERE "
						+" APPLICANT LIKE %?1%"
						+" AND TYPE = ?2"
						+" AND STATUS LIKE %?3%"
				, nativeQuery = true)
	List<Sheet> getSearchSheetsWithType(String applicant, String tableName, String status);

		@Query(value = "SELECT * FROM SCSB_SHEET WHERE "
				+" APPLICANT LIKE %?1%"
				+" AND TYPE = ?2"
				+" AND STATUS = ?3"
		, nativeQuery = true)
		List<Sheet> getSearchSheetsWithTypeAndStatus(String applicant, String tableName, String status);
		//管理者依條件取表單
		@Query(value = "SELECT * FROM SCSB_SHEET WHERE "
						+" APPLICANT LIKE %?1%"
						+" AND TYPE LIKE %?2%"
						+" AND STATUS = ?3"
				, nativeQuery = true)		
		List<Sheet> getSearchSheetsWithStatus(String applicant, String tableName, String status);
}
