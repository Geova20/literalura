package br.com.geova.literalura.repository;

import br.com.geova.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (anoFalecimento is NULL OR anoNascimento >= :ano)")
    List<Autor> findAllByAno(Integer ano);
}
