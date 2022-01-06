package com.sinaukoding.absensi.service;

import com.sinaukoding.absensi.entity.Company;
import com.sinaukoding.absensi.dao.BaseDAO;
import com.sinaukoding.absensi.dao.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends BaseService<Company> {

    @Autowired
    private CompanyDAO dao;

    @Override
    protected BaseDAO<Company> getDAO() {
        return dao;
    }

    public Company findByName(Company param) {
        return dao.findByName(param);
    }
}