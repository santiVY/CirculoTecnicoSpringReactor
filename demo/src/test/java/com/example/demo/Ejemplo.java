package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import model.Movie;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Ejemplo {

    /*
    *   Realizar merge con otra lista de tres peliculas nuevas
        Adicionar un campo director
        Filtrar por las peliculas que tiempos mayores a 120 segundos
        Filtrar las peliculas con score mayor a 8
        Filtrar por un director especifico
    * */

    private List<Movie> creatMovies1(){
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = Movie.builder()
                .name("Volver al futuro")
                .durationInMin(140)
                .score(8.5)
                .director("Luis Fernando")
                .build();
        Movie movie2 = Movie.builder()
                .name("Anabell")
                .durationInMin(130)
                .score(8.67)
                .director("Gabriel Perez")
                .build();
        Movie movie3 = Movie.builder()
                .name("Camino hacia el terror")
                .durationInMin(160)
                .score(7.5)
                .director("Luisa Giraldo")
                .build();

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        return movies;
    }

    private List<Movie> creatMovies2(){
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = Movie.builder()
                .name("Mas alla del mañana")
                .durationInMin(100)
                .score(9.5)
                .director("Laura Mejia")
                .build();
        Movie movie2 = Movie.builder()
                .name("El conjuro")
                .durationInMin(125)
                .score(7.67)
                .director("Jorge Aguilar")
                .build();

        movies.add(movie1);
        movies.add(movie2);

        return movies;
    }

    @Test
    public void homework(){

        List<Movie> movies1 = creatMovies1();
        List<Movie> movies2 =creatMovies2();

        Flux<Movie> fluxMovie1 = Flux.fromIterable(movies1);
        Flux<Movie> fluxMovie2 = Flux.fromIterable(movies2);

        System.out.println("Merge realizado");
        Flux<Movie> fluxMergeMovie = Flux.merge(fluxMovie1, fluxMovie2);
        fluxMergeMovie.subscribe(movies -> System.out.println(movies));

        System.out.println("Peliculas con duracion mayor que 120 minutos");
        Flux.merge(fluxMovie1, fluxMovie2)
                .filter(movie -> movie.getDurationInMin() > 120)
                .subscribe(movies -> System.out.println(movies));

        System.out.println("Peliculas con score superior a 8");
        Flux.merge(fluxMovie1, fluxMovie2)
                .filter(movie -> movie.getScore() > 8)
                .subscribe(movies -> System.out.println(movies));

        String director = "Laura Mejia";

        System.out.println("Pelicula filtrando por director especifico");
        Flux.merge(fluxMovie1, fluxMovie2)
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .subscribe(movies -> System.out.println(movies));
    }
}
