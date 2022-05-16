package kz.AsylbekSpring.springbootAsyl.repository;

import kz.AsylbekSpring.springbootAsyl.db.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Library, Long> {
}
