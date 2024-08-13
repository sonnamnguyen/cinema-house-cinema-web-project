/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author admin
 */
public class MovieAndMovieCategory {
    private MovieCategory idCategory;
    private Movie idMovie;

    public MovieAndMovieCategory() {
    }

    public MovieAndMovieCategory(MovieCategory idCategory, Movie idMovie) {
        this.idCategory = idCategory;
        this.idMovie = idMovie;
    }

    public MovieCategory getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(MovieCategory idCategory) {
        this.idCategory = idCategory;
    }

    public Movie getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Movie idMovie) {
        this.idMovie = idMovie;
    }

    
    
}
