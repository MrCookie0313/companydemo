package com.example.service.impl;

import com.example.dao.BorrowSubOrderDao;
import com.example.model.CompanyVos;
import com.example.model.DeptVos;
import com.example.service.BorrowSubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author sunbing_vendor
 */
@Service
public class BorrowSubOrderServiceImpl implements BorrowSubOrderService {
	@Autowired
	private BorrowSubOrderDao borrowSubOrderDao;
	
	@Override
	public List<CompanyVos> findCompanyAndDepts(Map<String, Object> param){
		List<CompanyVos> list=new ArrayList<CompanyVos>();
		List<CompanyVos> companyVosList=borrowSubOrderDao.findCompanys(param);
		if(!CollectionUtils.isEmpty(companyVosList)){
			for(CompanyVos companyVo:companyVosList){
				CompanyVos companyVo2=new CompanyVos();
				companyVo2.setCompanyId(companyVo.getCompanyId());
				companyVo2.setCompanyName(companyVo.getCompanyName());
				Map<String, Object> paramMap=new HashMap<String, Object>();
				paramMap.put("companyId", companyVo.getCompanyId());
				paramMap.put("fDeptId", 0);
				companyVo2.setDeptVos(getDepts(paramMap));
				list.add(companyVo2);
			}
		}
		return list;
	}
	
	/**
	 * @descript:递归部门
	 * @param param
	 * @return
	 */
	public List<DeptVos> getDepts(Map<String, Object> param){
		List<DeptVos> deptVosList= new ArrayList<>();
		List<DeptVos> deptVos=borrowSubOrderDao.findAllDepts(param);
		if(!CollectionUtils.isEmpty(deptVos)){
			for(DeptVos deptVo:deptVos){
				DeptVos deptVo2=new DeptVos();
				deptVo2.setDeptId(deptVo.getDeptId());
				deptVo2.setDeptName(deptVo.getDeptName());
				Map<String, Object> paramMap=new HashMap<String, Object>(16);
				paramMap.put("fDeptId", deptVo.getDeptId());
				paramMap.put("companyId",deptVo.getCompanyId());
				deptVo2.setSubDeptVos(getDepts(paramMap));
				deptVosList.add(deptVo2);
			}
		}
		return deptVosList;
	}
	
 
}