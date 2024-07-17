package br.com.geova.literalura.model;

import br.com.geova.literalura.dto.DadosAutor;
import br.com.geova.literalura.dto.DadosLivro;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    private String idioma;
    private int numeroDownloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.autor = new Autor(dadosLivro.autor().get(0));
        this.idioma = dadosLivro.idioma().get(0);
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    public Livro(Long id, String titulo, Autor autor, String idioma, int numeroDownloads) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(int numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "\n================== Livro ==================" +
                "\nTítulo: " + titulo + "\n" +
                autor + "\nIdioma: " + idioma +
                "\nNúmero de downloads: " + numeroDownloads +
                "\n===========================================\n";

    }
}


