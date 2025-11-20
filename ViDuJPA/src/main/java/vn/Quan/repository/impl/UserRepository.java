package vn.Quan.repository.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.Quan.config.JPAConfig;
import vn.Quan.entity.UserEntity;
import vn.Quan.repository.IUserPepository;

public class UserRepository implements IUserPepository{

    @Override
	public void create(UserEntity entity) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(entity);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    @Override
	public void update(UserEntity entity) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(entity);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    @Override
	public void delete(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            UserEntity entity = em.find(UserEntity.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    @Override
	public UserEntity findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(UserEntity.class, id);
    }

    @Override
	public List<UserEntity> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT u FROM UserEntity u";
        TypedQuery<UserEntity> query = em.createQuery(jpql, UserEntity.class);
        return query.getResultList();
    }

    @Override
    public boolean existsUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM UserEntity u WHERE u.username = :name";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("name", username);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM UserEntity u WHERE u.email = :email";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsPhone(String phone) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM UserEntity u WHERE u.phone = :phone";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("phone", phone);
        return query.getSingleResult() > 0;
    }
    
    @Override
    public UserEntity findByEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM UserEntity u WHERE u.email = :email";
            TypedQuery<UserEntity> query = em.createQuery(jpql, UserEntity.class);
            query.setParameter("email", email);

            List<UserEntity> list = query.getResultList();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } finally {
            em.close();
        }
    }
}
