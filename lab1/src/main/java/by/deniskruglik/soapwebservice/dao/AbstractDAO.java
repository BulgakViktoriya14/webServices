package by.deniskruglik.soapwebservice.dao;

import by.deniskruglik.soapwebservice.service.models.AbstractModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDAO<T extends AbstractModel> implements DAO<T> {
    private final static String PERSISTENCE_UNIT_NAME = "SOCIAL_NETWORK";

    protected EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

    private Class<T> clazz;

    public AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T create(T obj) {
        em.getTransaction().begin();
        T objectFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objectFromDB;
    }

    @Override
    public void delete(T obj) {
        em.getTransaction().begin();
        em.remove(getById(obj.getId()));
        em.getTransaction().commit();
    }

    @Override
    public T getById(Long id) {
        return em.find(clazz, id);
    }

    @Override
    public void update(T obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> namedQuery = em.createNamedQuery(clazz.getName() + ".getAll", clazz);
        return namedQuery.getResultList();
    }
}
