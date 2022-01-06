package com.sinaukoding.absensi.service;

import com.sinaukoding.absensi.entity.Division;
import com.sinaukoding.absensi.dao.BaseDAO;
import com.sinaukoding.absensi.dao.DivisionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionService extends BaseService<Division> {

    @Autowired
    private DivisionDAO dao;

    @Override
    protected BaseDAO<Division> getDAO() {
        return dao;
    }

    public Division findByName(Division param) {
        return dao.findByName(param);
    }
}
