package kz.AsylbekSpring.springbootAsyl.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")

public class Item {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "publishing")
    private String publishing;
    @Column(name = "description")
    private String description;
    @Column(name = "numberOfBook")
    private int numberOfBook;
    @Column(name = "genre")
    private String genre;

}
