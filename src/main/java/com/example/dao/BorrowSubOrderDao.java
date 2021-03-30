package com.example.dao;
 
import com.example.model.CompanyVos;
import com.example.model.DeptVos;

import java.util.List;
import java.util.Map;
 

 
/**
 * @author sunbing_vendor
 */
public interface BorrowSubOrderDao {
	
	
	List<CompanyVos> findCompanys(Map<String, Object> param);
	
	
	List<DeptVos> findAllDepts(Map<String, Object> param);
	
	
}