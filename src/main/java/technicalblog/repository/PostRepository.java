package technicalblog.repository;

import javafx.geometry.Pos;
import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.List;

@Repository
public class PostRepository {
    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPosts() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
        List<Post> resultList = query.getResultList();
        return resultList;
    }

    public Post getOnePost() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("SELECT p from Post p where p.id = :id", Post.class);
        query.setParameter("id", 3);
        return query.getSingleResult();
    }

    public Post createPost(Post newPost) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newPost);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return newPost;
    }

    public Post getPost(Integer postId) {
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class, postId);
    }

    public void updatePost(Post updatedPost) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(updatedPost);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Post post = em.find(Post.class, postId);
            em.remove(post);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void deletePost(String postTitle) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p WHERE title =:title",Post.class);
            query.setParameter("title",postTitle);
            Post post = query.getSingleResult();
            em.remove(post);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }
}
