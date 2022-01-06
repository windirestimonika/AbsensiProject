package com.sinaukoding.absensi.service;

import com.sinaukoding.absensi.entity.Position;
import com.sinaukoding.absensi.dao.BaseDAO;
import com.sinaukoding.absensi.dao.PositionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService extends BaseService<Position> {

    @Autowired
    private PositionDAO dao;

    @Override
    protected BaseDAO<Position> getDAO() {
        return dao;
    }

    public Position findByName(Position param) {
        return dao.findByName(param);
    }
}
