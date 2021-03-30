package com.example.service;


import com.example.model.CompanyVos;

import java.util.List;
import java.util.Map;
 
/**
 * @author sunbing_vendor
 */
public interface BorrowSubOrderService {
	
       public List<CompanyVos> findCompanyAndDepts(Map<String, Object> param);
		
	
}