package org.val.integration.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.IVAN;
import static org.val.integration.util.TestObjects.PETR;
import static org.val.integration.util.TestObjects.SERGEY;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.val.dao.UserDao;
import org.val.entity.User;
import org.val.integration.IntegrationTestBase;
import org.val.util.HibernateUtil;

class UserDaoIT extends IntegrationTestBase {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    UserDao userDao = new UserDao();

    @Test
    void testFindAll_WhenUsersAreGiven_ShouldReturnExactSize() {

        List<User> userList = List.of(IVAN, PETR, SERGEY);
        userList.forEach(userDao::save);

        assertThat(userDao.findAll()).hasSize(3);
        assertThat(userDao.findAll().get(0).getName()).isEqualTo("Ivan");
    }

    @Test
    void testFindById_WhenUserHasAdded_ShouldReturnUser() {
        try (var sessionFactory = HibernateUtil.getSessionFactory();
                var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(ADMIN);
            session.getTransaction().commit();

            session.save(IVAN);
            session.getTransaction().commit();
            Optional<User> user = userDao.findById(IVAN.getId());

            assertThat(user).isPresent();
            assertThat(user.get()).isEqualTo(IVAN);
            session.getTransaction().rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testDelete_WhenUserDeleted_ShouldReturnTrue() {
        userDao.save(IVAN);
        boolean isDeleted = userDao.delete(IVAN.getId());

        assertThat(isDeleted).isTrue();
    }

    @Test
    void testUpdated_WhenUserUpdated_ShouldReturnUpdatedUser() {
        userDao.save(IVAN);
        IVAN.setName("Valera");
        userDao.update(IVAN);
        Optional<User> user = userDao.findById(IVAN.getId());

        assertThat(user).isPresent();
        // assertThat((AssertProvider<Boolean>) () -> user.get().getName().equals("Valera"));
        Optional<User> resultUser = userDao.findById(IVAN.getId());
        assertThat(resultUser).isPresent();

        Predicate<User> predicate = u -> u.getName().equals("Valera");
        assertThat(predicate).accepts(IVAN);
    }

    @Test
    void save() {
    }
}