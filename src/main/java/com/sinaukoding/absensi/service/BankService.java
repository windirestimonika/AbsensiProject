package com.sinaukoding.absensi.service;

import com.sinaukoding.absensi.entity.Bank;
import com.sinaukoding.absensi.dao.BaseDAO;
import com.sinaukoding.absensi.dao.BankDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService extends BaseService<Bank> {

    @Autowired
    private BankDAO dao;

    @Override
    protected BaseDAO<Bank> getDAO() {
        return dao;
    }

    public Bank findByName(Bank param) {
        return dao.findByName(param);
    }
}
