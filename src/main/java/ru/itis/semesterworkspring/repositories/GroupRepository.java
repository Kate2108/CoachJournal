package ru.itis.semesterworkspring.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterworkspring.models.Group;

import javax.persistence.*;
import java.util.List;

@Repository
public class GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Group> findAll(Long id) {
        TypedQuery<Group> typedQuery = entityManager.createQuery(
                "select gr from Group gr where account.id = :id", Group.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getResultList();
    }
    public Group findGroupByName(Long id, String name) {
        try {
            TypedQuery<Group> typedQuery = entityManager.createQuery(
                    "select gr from Group gr where account.id = :id and gr.name = :name", Group.class);
            typedQuery.setParameter("id", id);
            typedQuery.setParameter("name", name);
            return typedQuery.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
    @Transactional
    public void deleteGroup(Long id, String name){
        Query query = entityManager.createQuery("delete from Group as gr where account.id = :id and gr.name = :name");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }
    @Transactional
    public void updateGroup(Long id, String name, int newCount){
        Query query = entityManager.createQuery("update Group gr set gr.countOfMembers =:newCount where account.id = :id and gr.name = :name");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("newCount", newCount);
        query.executeUpdate();
    }
    @Transactional
    public void saveGroup(Group group){
        entityManager.persist(group);
    }
}
