package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies/")
public class MovieController {

    @Autowired
    MoviesRepository moviesRepository;

    @GetMapping("")
    public List<Movie> getAll() {
        return moviesRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id) {
        return moviesRepository.getById(id);
    }

    @PostMapping("")
    public int addMovie(@RequestBody List<Movie> movies) {
        return moviesRepository.save(movies);
    }

    @PutMapping("/{id}")
    public int updateMovie(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = moviesRepository.getById(id);


        if (movie != null) {
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());
            moviesRepository.update(movie);
            return 1;
        } else {
            return -1;
        }
    }


    @PatchMapping("/{id}")
    public int partialUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = moviesRepository.getById(id);

        if (movie != null) {

            if(updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if(updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

            moviesRepository.update(movie);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return moviesRepository.delete(id);
    }

}
