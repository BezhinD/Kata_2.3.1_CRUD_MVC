package basepackage.dao;

import basepackage.models.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.util.List;

@Component
public class UserDAO extends Persistence {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Transactional
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, @Valid User user) {
        entityManager.find(User.class, id);
        entityManager.merge(user);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
