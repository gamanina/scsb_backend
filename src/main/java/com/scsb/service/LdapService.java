package com.scsb.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scsb.itd.ldap.ldapOperator;
import com.scsb.model.Ldap;
import com.scsb.model.SecurityEquals;
import com.scsb.util.LogUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LdapService 
{
	// 假資料資訊部A
	public List<Ldap> fakeItClassA()
	{
		Gson gson = new Gson();
		String jsonStr = "[{'supervisorName':'2*6','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'2763','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'余*琦','cn':'3387','employmentStatus':'D2','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d3387,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003dhrAdvMgr,ou\\u003dadviceService,o\\u003dflow,c\\u003dtw','cn\\u003dbookgroup2,ou\\u003dbookgroup,o\\u003dapgroup,c\\u003dtw','cn\\u003dbookgroup1,ou\\u003dbookgroup,o\\u003dapgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dtestFlow1-3,ou\\u003dtestFlow1,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003diOpenSysGroup,o\\u003dapgroup,c\\u003dtw','cn\\u003dolnGroup,o\\u003dapgroup,c\\u003dtw'],'givenName':'顏*正','cn':'26689','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d26689,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw','cn\\u003dCOVID19_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'曾*玉','cn':'15865','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d15865,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003dmobilepayment_A,ou\\u003dtwmp,o\\u003dapgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'鍾*雪','cn':'15784','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d15784,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'王*寅','cn':'12858','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d12858,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'李*翰','cn':'11622','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11622,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dstemBusinessSYS,o\\u003dapgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'周*志','cn':'11509','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11509,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003dgolfgroup,ou\\u003dnews_group,o\\u003dapgroup,c\\u003dtw','cn\\u003dGolfClubGroup2,ou\\u003dGolfClub,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dtestFlow1-4,ou\\u003dtestFlow1,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'翁*耀','cn':'11487','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11487,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'陳*棟','cn':'11444','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11444,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003da038_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003da043_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'尹*修','cn':'9041','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d9041,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'榮*信','departmentNumberName':'資訊研發處','cardtitle':'資深協理','mail':'gamanina55@gmail.com','supervisorAcno':'28886','securityEquals':['cn\\u003da048_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003da068_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003dWelfareCommitteeGroup3,ou\\u003dWelfareCommittee,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'狄*力','cn':'6823','employmentStatus':'A1','title':'資訊研發處主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d6823,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003da039_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003da042_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'李*正','cn':'8150','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d8150,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'}]";
		List<Ldap> ldap = gson.fromJson(jsonStr, new TypeToken<ArrayList<Ldap>>(){}.getType());
		return ldap;
	}
	
	// 假資料資訊部C
	public List<Ldap> fakeItClassC()
	{
		Gson gson = new Gson();
		String jsonStr = "[{'supervisorName':'2*6','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'2763','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'余*琦','cn':'3387','employmentStatus':'D2','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d3387,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003dhrAdvMgr,ou\\u003dadviceService,o\\u003dflow,c\\u003dtw','cn\\u003dbookgroup2,ou\\u003dbookgroup,o\\u003dapgroup,c\\u003dtw','cn\\u003dbookgroup1,ou\\u003dbookgroup,o\\u003dapgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dtestFlow1-3,ou\\u003dtestFlow1,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003diOpenSysGroup,o\\u003dapgroup,c\\u003dtw','cn\\u003dolnGroup,o\\u003dapgroup,c\\u003dtw'],'givenName':'顏*正','cn':'26689','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d26689,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw','cn\\u003dCOVID19_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'曾*玉','cn':'15865','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d15865,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003dmobilepayment_A,ou\\u003dtwmp,o\\u003dapgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'鍾*雪','cn':'15784','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d15784,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'王*寅','cn':'12858','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d12858,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'李*翰','cn':'11622','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11622,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dstemBusinessSYS,o\\u003dapgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'周*志','cn':'11509','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11509,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003dgolfgroup,ou\\u003dnews_group,o\\u003dapgroup,c\\u003dtw','cn\\u003dGolfClubGroup2,ou\\u003dGolfClub,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dtestFlow1-4,ou\\u003dtestFlow1,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'翁*耀','cn':'11487','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11487,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'陳*棟','cn':'11444','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d11444,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003da038_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003da043_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'尹*修','cn':'9041','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d9041,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'榮*信','departmentNumberName':'資訊研發處','cardtitle':'資深協理','mail':'gamanina55@gmail.com','supervisorAcno':'28886','securityEquals':['cn\\u003da048_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003da068_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003dWelfareCommitteeGroup3,ou\\u003dWelfareCommittee,ou\\u003dannounce,o\\u003dflow,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw','cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'狄*力','cn':'6823','employmentStatus':'A1','title':'資訊研發處主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d6823,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'},{'supervisorName':'狄*力','departmentNumberName':'資訊研發處','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'6823','securityEquals':['cn\\u003da039_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003da042_ngrp,o\\u003dnotesgroup,c\\u003dtw','cn\\u003d214_lgrp,o\\u003dlocationgroup,c\\u003dtw'],'givenName':'李*正','cn':'8150','employmentStatus':'A1','title':'業務系統開發主管','titleno':'A','departmentNumber':'011300','entryDN':'cn\\u003d8150,ou\\u003d010900,ou\\u003dscsb03,o\\u003dscsb,c\\u003dtw'}]";
		List<Ldap> ldap = gson.fromJson(jsonStr, new TypeToken<ArrayList<Ldap>>(){}.getType());
		return ldap;
	}
	
	// 假資料公關部ABC
	public List<Ldap> fakePrClassAll()
	{
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Ldap>>(){}.getType();
		String jsonStrA = "[{'supervisorName':'彭*貴','departmentNumberName':'總管理處策略規劃—公關暨行銷廣告','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'4502','givenName':'周*霨','departmentNumber':'012100','cn':'17621','employmentStatus':'A1','title':'公關廣告主管','titleno':'A','entryDN':'cn\\u003d17621,ou\\u003d010100,o\\u003dscsb,c\\u003dtw'}]";
		List<Ldap> ldapA = gson.fromJson(jsonStrA, type);
		String jsonStrB = "[{'supervisorName':'周*霨','departmentNumberName':'總管理處策略規劃—公關暨行銷廣告','cardtitle':'經理','mail':'gamanina55@gmail.com','supervisorAcno':'17621','givenName':'黃*志','departmentNumber':'012100','cn':'18881','employmentStatus':'A1','title':'行銷廣告資深專員','titleno':'B','entryDN':'cn\\u003d18881,ou\\u003d010100,o\\u003dscsb,c\\u003dtw'}]";
		List<Ldap> ldapB = gson.fromJson(jsonStrB, type);
		String jsonStrC = "[{'supervisorName':'周*霨','departmentNumberName':'總管理處策略規劃—公關暨行銷廣告','cardtitle':'資深專員','mail':'gamanina55@gmail.com','supervisorAcno':'17621','securityEquals':['cn\\u003dSAS_grp,o\\u003dapgroup,c\\u003dtw'],'givenName':'周*媛','cn':'22004','employmentStatus':'A1','title':'行銷廣告專員','titleno':'','departmentNumber':'012100','entryDN':'cn\\u003d22004,ou\\u003d010100,o\\u003dscsb,c\\u003dtw'}]";
		List<Ldap> ldapC = gson.fromJson(jsonStrC, type);
		return Arrays.asList(ldapA.get(0), ldapB.get(0), ldapC.get(0));
	}		
		
	// 依編號取得該行員資料
	public Ldap getFakeDataByEmpNo(String empNo) throws Exception
	{
		List<Ldap> list = new ArrayList<Ldap>();
		list.addAll(fakeItClassA());
		list.addAll(fakeItClassC());
		list.addAll(fakePrClassAll());
		for(Ldap lLdap:list) {
			if(lLdap.getCn().equals(empNo)) {
				return lLdap;
			}
		}
		return null;
	}
	/**
	 * 依編號取得該行員資料
	 * @param empNo
	 * @return
	 * @throws Exception
	 */
	public Ldap getDataByEmpNo(String empNo) throws Exception
	{
		Gson gson = new Gson();
		ldapOperator ldapOper = new ldapOperator();
		
		Map<String, Object> ldapMap = (Map<String, Object>)ldapOper.queryUser(empNo);
		System.out.println("=====ldapMap======");
		System.out.println(ldapMap.toString());
		System.out.println("=====ldapMap======");
		String jsonStr = gson.toJson(ldapMap);
		Ldap ldap = gson.fromJson(jsonStr, Ldap.class);
		
		// [20220301] 從LDAP取回的email
		String emailLdap = ldap.getMail();
		// [20220301] 修正後的行員mail
		String fixEmail = ldap.getCn() + "@notes.scsb.com.tw";// 行員Email, 若要寄到Notes 則為行編 + @notes.scsb.com.tw
		ldap.setMail(fixEmail);
		
		LogUtil.setActionLog("getEmail", "emailLdap: " + emailLdap + "; fixEmail: " + fixEmail);
		
		// Set SecurityEquals值
		// Set SecurityEquals值
				Map<String, String> securityEqualsMap = new HashMap<String, String>();
				if (ldap.getSecurityEquals()!=null) {
					if (ldap.getSecurityEquals().size() > 0)
					{
						String securityEquals = ldap.getSecurityEquals().get(0);
						String[] strArray = securityEquals.split(",");
						for (String str : strArray)
						{
							List<String> equal = Arrays.asList(str.split("="));
							if (equal.size() == 2)
							{
								securityEqualsMap.put(equal.get(0), equal.get(1));
							}
						}
					}
				}
				
				SecurityEquals se = new SecurityEquals();
				se.setCn(securityEqualsMap.get("cn"));
				se.setOu(securityEqualsMap.get("ou"));
				se.setO(securityEqualsMap.get("o"));
				se.setC(securityEqualsMap.get("c"));
				ldap.setSeObject(se);
				return ldap;
	}
	
	/**
	 * 依部門代碼與層級取得該部門的該層級的行員清單
	 * @param dn
	 * @param titleNo
	 * @return
	 * @throws Exception
	 */
	public List<Ldap> getDataByDepartmentNumberAndTitleNo(String dn, String titleNo) throws Exception
	{
		Gson gson = new Gson();
		ldapOperator ldapOper = new ldapOperator();
		
		List list = (List)ldapOper.queryApprovers(dn, titleNo);
		String jsonStr = gson.toJson(list);
		List<Ldap> ldap = gson.fromJson(jsonStr, new TypeToken<ArrayList<Ldap>>(){}.getType());
		
		ldap.forEach(l -> {
			// [20220301] 從LDAP取回的email
			String emailLdap = l.getMail();
			// [20220301] 修正後的行員mail
			String fixEmail = l.getCn() + "@notes.scsb.com.tw";// 行員Email, 若要寄到Notes 則為行編 + @notes.scsb.com.tw
			l.setMail(fixEmail);
			
//			LogUtil.setActionLog("getEmail", "emailLdap: " + emailLdap + "; fixEmail: " + fixEmail);
		});

		return ldap;
	}
	
	/**
	 * ldap帳號密碼登入方式
	 * @param zhho
	 * @param mima
	 * @return
	 * @throws Exception
	 */
	public Ldap login(String zhho, String mima) throws Exception
	{
		Gson gson = new Gson();
		ldapOperator ldapOper = new ldapOperator();
		
		// 先取得使用者DN
		String userdn = ldapOper.getUserDirectoryName(zhho);
		// log in
		Map<String, Object> ldapMap = ldapOper.userLoginLdap(userdn, mima);
		String jsonStr = gson.toJson(ldapMap);
		Ldap ldap = gson.fromJson(jsonStr, Ldap.class);
		// [20220301] 從LDAP取回的email
		String emailLdap = ldap.getMail();
		// [20220301] 修正後的行員mail
		String fixEmail = ldap.getCn() + "@notes.scsb.com.tw";// 行員Email, 若要寄到Notes 則為行編 + @notes.scsb.com.tw
		ldap.setMail(fixEmail);
		
		LogUtil.setActionLog("getEmail" + zhho, "emailLdap: " + emailLdap + "; fixEmail: " + fixEmail);
		
		// Set SecurityEquals值
		Map<String, String> securityEqualsMap = new HashMap<String, String>();
		if (ldap.getSecurityEquals().size() > 0)
		{
			String securityEquals = ldap.getSecurityEquals().get(0);
			String[] strArray = securityEquals.split(",");
			for (String str : strArray)
			{
				List<String> equal = Arrays.asList(str.split("="));
				if (equal.size() == 2)
				{
					securityEqualsMap.put(equal.get(0), equal.get(1));
				}
			}
		}
		SecurityEquals se = new SecurityEquals();
		se.setCn(securityEqualsMap.get("cn"));
		se.setOu(securityEqualsMap.get("ou"));
		se.setO(securityEqualsMap.get("o"));
		se.setC(securityEqualsMap.get("c"));
		ldap.setSeObject(se);
		return ldap;
	}
	
	/**
	 *  取得使用者的部門的所有人員
	 * @param ldap
	 * @return
	 * @throws Exception
	 */
	public List<Ldap> getDepartmentPeople(Ldap ldap) throws Exception
	{
		List<Ldap> finalList = new ArrayList<>();
		List<Ldap> a = getDataByDepartmentNumberAndTitleNo(ldap.getDepartmentNumber(), "A");
		List<Ldap> b = getDataByDepartmentNumberAndTitleNo(ldap.getDepartmentNumber(), "B");
		List<Ldap> c = getDataByDepartmentNumberAndTitleNo(ldap.getDepartmentNumber(), " ");
		finalList.addAll(a);
		finalList.addAll(b);
//		finalList.addAll(c);
		log.info("c:{}",c.toString());
		log.info("finalList:{}",finalList.toString());
		return finalList;
	}
	
	/**
	 *  取得公關部門的所有人員
	 * @return
	 * @throws Exception
	 */
	public List<Ldap> getPrDepartmentPeople() throws Exception
	{
		List<Ldap> finalList = new ArrayList<>();
		List<Ldap> a = getDataByDepartmentNumberAndTitleNo("012100", "A");
		List<Ldap> b = getDataByDepartmentNumberAndTitleNo("012100", "B");
		List<Ldap> c = getDataByDepartmentNumberAndTitleNo("012100", " ");
		finalList.addAll(a);
		finalList.addAll(b);
		finalList.addAll(c);
		return finalList;
	}
	
	/**
	 * 判斷是否有登入權限
	 * @param userdn
	 * @param emppwd
	 * @return
	 * @throws Exception
	 */
	public Ldap getAuthorityForSystem(String user,String emppwd) throws Exception
	{
		ldapOperator ldapOper = new ldapOperator();
		String userdn = ldapOper.getUserDirectoryName(user);
		Map<String, Object> map = ldapOper.userLoginLdap(userdn, emppwd);

		if(map.isEmpty()) {
			
			return null;
		}
		Ldap ldap = getDataByEmpNo(user);
		return ldap;
	}
	
	/**
	 * 登入行員是否為A級 or B級主管
	 * @param ldap
	 * @return
	 * @throws Exception 
	 */
	public boolean isManager(Ldap ldap) throws Exception {
		String depNo = ldap.getDepartmentNumber();
		// 取得A級主管清單
		List<Ldap> a = getDataByDepartmentNumberAndTitleNo(depNo, "A");
		// 取得B級主管清單
		List<Ldap> b = getDataByDepartmentNumberAndTitleNo(depNo, "B");
		// 合併List
		a.addAll(b);
		// 查找清單中員編是否匹配登入行員員編
		boolean result = a.stream().anyMatch(matchLdap -> ldap.getCn() != null && ldap.getCn().equals(matchLdap.getCn()));
		return result;
	}
}
