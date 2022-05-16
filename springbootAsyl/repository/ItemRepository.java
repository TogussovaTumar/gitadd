package kz.AsylbekSpring.springbootAsyl.repository;

import kz.AsylbekSpring.springbootAsyl.db.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
