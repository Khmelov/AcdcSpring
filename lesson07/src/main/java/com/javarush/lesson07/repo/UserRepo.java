package com.javarush.lesson07.repo;


import com.javarush.lesson07.entity.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UserRepo extends JpaRepository<User, Long> {

    default void showUsersByPage(int size){
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable=PageRequest.of(0,size,sort);
        while (!pageable.isUnpaged()){
            Page<User> page = findAll(pageable);
            List<User> content = page.getContent();
            System.out.println(content);
            System.out.println("====");
            pageable=page.nextPageable();
        }

    }


    @Modifying
    @Query("update User u set u.password=:password where u.id=:id")
    void updatePassword(@Param("id") Long id, @Param("password") String password);



    @Query("select u from User u where u.login like concat('%', :login, '%') and u.role like concat('%', :role, '%')")
    List<User> findUserByWith(
            @Param("login") String login,
            @Param("role") String role
    );



    @Override
    void flush();

    @Override
    <S extends User> S saveAndFlush(S entity);

    @Override
    <S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    default void deleteInBatch(Iterable<User> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    void deleteAllInBatch(Iterable<User> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    User getOne(Long aLong);

    @Override
    User getById(Long aLong);

    @Override
    User getReferenceById(Long aLong);

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Override
    List<User> findAll();

    @Override
    List<User> findAllById(Iterable<Long> longs);

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends User> entities);

    @Override
    void deleteAll();

    @Override
    List<User> findAll(Sort sort);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends User> long count(Example<S> example);

    @Override
    <S extends User> boolean exists(Example<S> example);

    @Override
    <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
