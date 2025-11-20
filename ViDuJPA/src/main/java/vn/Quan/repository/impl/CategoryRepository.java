package vn.Quan.repository.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.Quan.config.JPAConfig;
import vn.Quan.entity.CategoryEntity;
import vn.Quan.repository.ICategoryRepository;

public class CategoryRepository implements ICategoryRepository{

    @Override
	public void create(CategoryEntity entity) {
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
	public void update(CategoryEntity entity) {
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
	public void delete(int cateId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            CategoryEntity entity = em.find(CategoryEntity.class, cateId);
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
	public CategoryEntity findById(int cateId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(CategoryEntity.class, cateId);
    }

    @Override
	public List<CategoryEntity> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<CategoryEntity> query =
                em.createNamedQuery("Category.findAll", CategoryEntity.class);
        return query.getResultList();
    }

    // Lấy category theo user
    @Override
	public List<CategoryEntity> findByUserId(int userId) {
        EntityManager em = JPAConfig.getEntityManager();

        String jpql = "SELECT c FROM CategoryEntity c WHERE c.user.id = :uid";
        TypedQuery<CategoryEntity> query = em.createQuery(jpql, CategoryEntity.class);
        query.setParameter("uid", userId);

        return query.getResultList();
    }
}
