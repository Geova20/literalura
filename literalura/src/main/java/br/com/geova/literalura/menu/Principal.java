package br.com.geova.literalura.menu;

import br.com.geova.literalura.dto.DadosLivro;
import br.com.geova.literalura.dto.Resultados;
import br.com.geova.literalura.model.Autor;
import br.com.geova.literalura.model.Livro;
import br.com.geova.literalura.repository.AutorRepository;
import br.com.geova.literalura.repository.LivroRepository;
import br.com.geova.literalura.service.ConsumoApi;
import br.com.geova.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    ConsumoApi consumo = new ConsumoApi();
    ConverteDados conversao = new ConverteDados();
    List<Autor> listaAutores = new ArrayList<>();
    List<Livro> listaLivros = new ArrayList<>();
    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }


    public void mostraMenu() {
        int opcao = -1;
        while(opcao != 0){
            String menu = """
                =======================================================
                Escolha uma opção: 
                
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar liivros em um determinado idioma
                0 - Sair
                
                =======================================================
                """;
            System.out.println(menu);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch(opcao) {
                case 1:
                   buscarLivrosPorTitulo();
                   break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresregistrados();
                    break;
                case 4:
                    listarAutoresVivosNoAnoEscolhido();
                    break;
                case 5:
                    listarLivrosNoIdiomaEscolhido();
                    break;
                case 0:
                    System.out.println("Encerrando aplicação...");
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        leitor.close();
    }

    private Resultados getDadosLivro() {
        System.out.println("Insira o nome do livro que deseja buscar: ");
        var nomeLivro = leitor.nextLine();
        var json = consumo.consomeDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        return conversao.obterDados(json, Resultados.class);
    }

    private void buscarLivrosPorTitulo() {
        Resultados dadosLivro = getDadosLivro();

        if(dadosLivro.resultados() != null && !dadosLivro.resultados().isEmpty()){
            DadosLivro livroPesquisado = dadosLivro.resultados().get(0);
            Livro livro = new Livro(livroPesquisado);
            System.out.println(livro);
            livroRepository.save(livro);

        } else {
            System.out.println("Nenhum livro com o nome descrito foi encontrado");
        }

    }

    private void listarLivrosRegistrados() {
        listaLivros = livroRepository.findAll();
        if(listaLivros.isEmpty()){
            System.out.println("Nenhum livro registrado até o momento");
        } else {
            listaLivros.forEach(System.out::println);
        }
    }

    private void listarAutoresregistrados() {
        listaAutores = autorRepository.findAll();
        if(listaAutores.isEmpty()){
            System.out.println("Nenhum autor registrado até o momento");
        } else {
            listaLivros.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosNoAnoEscolhido() {
        System.out.println("Digite um ano para a busca: ");
        Integer ano = leitor.nextInt();
        listaAutores = autorRepository.findAllByAno(ano);

        if(listaAutores.isEmpty()) {
            System.out.println("Nenhum autor registrado vivo no ano informado: ");
        } else{
            listaAutores.forEach(System.out::println);
        }
    }

    private void listarLivrosNoIdiomaEscolhido() {
        System.out.println("""
                Escolha o idioma para busca:
                Pt - Português
                En - Inglês
                Es - Espanhol
                Fr - Francês
                """);
        String idiomaEscolhido = leitor.nextLine();
        listaLivros = livroRepository.findAllByIdioma(idiomaEscolhido);

        if (listaLivros.isEmpty()){
            System.out.println("Nenhum livro com o idioma escolhido registrado");
        } else {
            listaLivros.forEach(System.out::println);
        }
    }

}
