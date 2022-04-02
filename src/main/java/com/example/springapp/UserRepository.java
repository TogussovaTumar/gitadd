package com.example.springapp;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //1 task
    List<User> findByEmailEndsWith(String email);

    //2 task
    List<User> findTop2ByNameStartsWith(String name);

    //3 task
    List<User> findBySurnameContaining(String surname);

    //4 task
    @Query(value= "select *from users order by id asc;", nativeQuery = true)
    List<User> orderByIdAsc();

    //5 task
    @Query(value= "select *from users order by id desc limit(2)", nativeQuery = true)
    List<User> showlastUsers();

    //6 task
    @Query(value= "select *from users order by name desc", nativeQuery = true)
    List<User> sortByName();

    //7 task
    List <User> findByEmailNotContaining(String e);

    //10 task
    @Query(value="select distinct on(name) * from users ", nativeQuery = true)
    List<User> findDistinctByName();

    @Override
    List<User> findAll(Sort sort);

    @Override
    List<User> findAll();

    //9 task
    @Query(value= "select *from users where email like '%narxoz.kz%' or email like '%gmail.com%' or email like '%yandex.ru%' ",
            nativeQuery = true)
    List<User> emailLike();

    //8 task
    @Query(value= "select *from users where name=surname",
            nativeQuery = true)
    List<User> findByNameEqualsSurname();


}
