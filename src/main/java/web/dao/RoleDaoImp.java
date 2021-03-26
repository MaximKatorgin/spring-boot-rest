package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getByRoleName(String roleName) {
        return entityManager.createQuery("select r from Role r where r.role = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }
}
