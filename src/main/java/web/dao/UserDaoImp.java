package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user, Long id) {
        User tmpUser = entityManager.getReference(User.class, id);
        tmpUser.setName(user.getName());
        tmpUser.setAge(user.getAge());
        tmpUser.setLastName(user.getLastName());
        tmpUser.setPassword(user.getPassword());
        tmpUser.setEmail(user.getEmail());
        tmpUser.setRoles(user.getRoles());
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createNativeQuery("select * from user", User.class).getResultList();
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name).getSingleResult();
    }

    public List<Role> getRolesList() {
        return entityManager.createNativeQuery("select * from role", Role.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return  entityManager.find(User.class, id);
    }
}
