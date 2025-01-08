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
    private EntityManager em;

    @Transactional
    public List<User> getUsers() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Transactional
    public User show(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Transactional
    public void update(int id, @Valid User user) {
        em.refresh(em.find(User.class, id));
    }

    @Transactional
    public void delete(int id) {
        em.remove(em.getReference(User.class, id));
    }
}
