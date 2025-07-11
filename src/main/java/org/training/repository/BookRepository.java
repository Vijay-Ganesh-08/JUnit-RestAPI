package org.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

}
