package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.EStatusExemplar;
import br.edu.ifsp.biblioteca.domain.Emprestimo;
import br.edu.ifsp.biblioteca.domain.Exemplar;
import br.edu.ifsp.biblioteca.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *
 * JpaRepository fornece os seguintes métodos (dentre outros):
 *
 * - save, findById, findAll, count,...
 *
 */
public interface ILivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    /**
     * "Containing" vira  LIKE "%...%", "IgnoreCase" vira LOWER(...).
     *
     *  SELECT * FROM livro WHERE LOWER(titulo) LIKE "%?%";
     *
     * @param trechoTitulo
     * @return
     */
    List<Livro> findByTituloContainingIgnoreCase(String trechoTitulo);

    /**
     * Retorna a lista de livros filtrados pelo trecho do título, porém, paginado.
     *
     * @param trechoTitulo
     * @param pagina
     * @return
     */
    Page<Livro> findByTituloContainingIgnoreCase(
        String trechoTitulo,
        Pageable pagina
    );

    List<Livro> findByAutoresNomeContainingIgnoreCase(String nome);

    // JPQL
    @Query(
        "SELECT DISTINCT l FROM Livro l " +
        "JOIN l.exemplares e " +
        "WHERE e.status = 'DISPONIVEL'"
    )
    List<Livro> listarLivrosDisponiveis();

    // List<Livro> buscarPorTitulo(String titulo);
}
