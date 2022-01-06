package com.sinaukoding.absensi.dao;

import com.sinaukoding.absensi.entity.Position;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PositionDAO extends BaseDAO<Position> {
    @Override
    public List<Predicate> predicates(Position param, CriteriaBuilder builder, Root<Position> root, boolean isCount){
        List<Predicate> predicates = super.predicates(param, builder, root,isCount);

        if (param != null){
            if (param.getName() != null){
                predicates.add(builder.like(root.get("name"), "%" + param.getName() + "%"));
            }
        }

        return predicates;
    }

    public Position findByName(Position param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Position> query = builder.createQuery(Position.class);

        Root<Position> root = query.from(Position.class);

        Predicate p = builder.equal(root.get("name"), param.getName());
        query.where(p);

        TypedQuery<Position> result = entityManager.createQuery(query);
        List<Position> resultList = result.getResultList();

        return resultList.size() > 0 ? resultList.get(0) : new Position();
    }
}
