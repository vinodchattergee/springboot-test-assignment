package net.vinlabs.springboot.assignement.repository;

import net.vinlabs.springboot.assignement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    //where Employee is not a table its the class!
    @Query("Select e from Student e where e.firstName = ?1 and e.lastName = ?2")
    Student findByJPQL(String firstName, String lastName);

    @Query("Select e from Student e where e.firstName =:firstName and e.lastName =:lastName")
    Student findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    //where employees is table!
    @Query(value = "Select * from students e where e.first_name = ?1 and e.last_name = ?2", nativeQuery = true)
    Student findByNativeSQL(String firstName, String lastName);

    @Query(value = "Select * from students e where e.first_name =:firstName and e.last_name =:lastName", nativeQuery = true)
    Student findByNativeSQLLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

}