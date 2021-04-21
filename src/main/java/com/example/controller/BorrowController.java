package com.example.controller;

import com.example.model.CompanyVos;
import com.example.service.BorrowSubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author sunbing_vendor
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowSubOrderService borrowSubOrderService;

    @RequestMapping(value = "/findCompany",method = RequestMethod.GET)
    public List findCompanyAndDepts() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<CompanyVos> list = borrowSubOrderService.findCompanyAndDepts(paramMap);
        return list;
    }
}