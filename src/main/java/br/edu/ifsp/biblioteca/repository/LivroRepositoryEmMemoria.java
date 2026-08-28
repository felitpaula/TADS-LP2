package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

public class LivroRepositoryEmMemoria implements ILivroRepository {

    private final Map<Long, Livro> livros = new HashMap<Long, Livro>();
    private Long sequenciaId = 0L;

    @Override
    public Livro salvar(Livro livro) {

        if (livro.getId() == null) {
            this.sequenciaId = this.sequenciaId + 1;
            livro.setId(this.sequenciaId);
        }

        this.livros.put(livro.getId(), livro);

        return livro;
    }

    @Override
    public List<Livro> listarTodos() {

        return new ArrayList<>(this.livros.values());

    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {

        return Optional.ofNullable(this.livros.get(id));

//      Usando o operador ternário
//      Livro l = this.livros.get(id);
//      return l == null ? Optional.empty() : Optional.of(l);

//      Usando if's
//      if (l == null) {
//          return Optional.empty();
//      }
//      return Optional.of(l);
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        for (Livro livro : colecaoLivros) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(livro);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {

        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : colecaoLivros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }

        return livrosEncontrados;
    }
}
