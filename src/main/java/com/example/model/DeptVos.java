package com.example.model;
 
import java.io.Serializable;
import java.util.List;
 
public class DeptVos  implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7648136453963080696L;
	private Long companyId;
	private Long fDeptId;
	private Long deptId;
	private String deptName;
	private List<DeptVos> subDeptVos;
	
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getfDeptId() {
		return fDeptId;
	}
	public void setfDeptId(Long fDeptId) {
		this.fDeptId = fDeptId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<DeptVos> getSubDeptVos() {
		return subDeptVos;
	}
	public void setSubDeptVos(List<DeptVos> subDeptVos) {
		this.subDeptVos = subDeptVos;
	}
	
	@Override
	public String toString() {
		return "DeptVos [deptId=" + deptId + ", deptName=" + deptName
				+ ", subDeptVos=" + subDeptVos + "]";
	}
	
	
}