package br.com.geova.literalura.repository;

import br.com.geova.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT i FROM Livro i WHERE i.idioma LIKE %:idioma%")
    List<Livro> findAllByIdioma(String idioma);
}
