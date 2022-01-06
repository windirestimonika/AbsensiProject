package com.sinaukoding.absensi.dao;

import com.sinaukoding.absensi.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAO extends BaseDAO<User> {
    @Override
    public List<Predicate> predicates(User param, CriteriaBuilder builder, Root<User> root, boolean isCount){
        List<Predicate> predicates = super.predicates(param, builder, root,isCount);

        if (param != null){
            if(param.getUsername() != null){
                predicates.add(builder.equal(root.get("username"), param.getUsername()));
            }

            if (param.getProfileName() != null){
                predicates.add(builder.like(root.get("profileName"), "%" + param.getProfileName() + "%"));
            }

            if (param.getNickName() != null){
                predicates.add(builder.like(root.get("nickName"), "%" + param.getNickName() + "%"));
            }

            if (param.getReligion() != null){
                predicates.add(builder.like(root.get("religion"), "%" + param.getReligion() + "%"));
            }

            if (param.getActive() != null){
                predicates.add(builder.like(root.get("active"), "%" + param.getActive() + "%"));
            }
        }

        if (!isCount){
            root.fetch("bank", JoinType.INNER);
            root.fetch("company", JoinType.INNER);
            root.fetch("division", JoinType.INNER);
            root.fetch("position", JoinType.INNER);
        }

        return predicates;
    }
}
