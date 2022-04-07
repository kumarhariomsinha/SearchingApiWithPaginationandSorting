package com.api.RestApi.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.RestApi.entity.movies;
import com.api.RestApi.repo.movieRepo;
import com.api.RestApi.services.movieService;



@RestController
public class MovieController {

	@Autowired
	private movieRepo movieRepos;
	@Autowired
	private movieService movieService;
	
	
	//Get Method to get all movie data
	@GetMapping("/movies")
	public List<movies> getMovies(){
		return (List<movies>) movieRepos.findAll();
	}
	
	//Get method to get movies name in sorting order w.r.t field
	@GetMapping("/{field}")
	public List<movies> getMoviesWithSort(@PathVariable String field){
		return (List<movies>) movieService.movieWithSorting(field);
	}

	//Method to implement pagination
	@GetMapping("/pagination/{offset}/{pageSize}")
	public Page<movies> getMoviesWithPage(@PathVariable int offset, @PathVariable int pageSize){
		Page<movies>movieWithPagination=  movieService.findMoviesWithPagination(offset, pageSize);
		return movieWithPagination;
	}
	//PostMethod to addMovie
	@PostMapping("/addMovie")
	public movies addMovie(@RequestBody movies movie) {
		movies mv=new movies();
		movieRepos.save(movie);
		return movie;
	}
	
	//Method for multiple field searches
	@PostMapping("/movies")
	public List<movies> searchMovies(@RequestBody movies movie){
		System.out.println(movie.getCountry());
		List<movies>filteredMovies=new ArrayList<movies>();
		List<movies>movies=new ArrayList<movies>();
		movies=(List<com.api.RestApi.entity.movies>) movieRepos.findAll();
		for(int i=0;i<movies.size();i++) {
			if((movies.get(i).getShow_id().equals(movie.getShow_id()))&&
					(movies.get(i).getType().equals(movie.getType()))&&	
					(movies.get(i).getTitle().equals(movie.getTitle()))&&
					(movies.get(i).getDirector().equals(movie.getDirector()))&& 
					(movies.get(i).getCast().equals(movie.getCast()))&&
					(movies.get(i).getCountry().equals(movie.getCountry()))&&
					(movies.get(i).getDate_added().equals(movie.getDate_added()))&&
					(movies.get(i).getRelease_year()==(movie.getRelease_year()))&&
					(movies.get(i).getRating().equals(movie.getRating()))&&
					(movies.get(i).getDuration().equals(movie.getDuration()))&&
					(movies.get(i).getListed_in().equals(movie.getListed_in()))&&	
					(movies.get(i).getDescription().equals(movie.getDescription()))	
					) {
				filteredMovies.add(movies.get(i));
			}
			
		}
		return filteredMovies;
	}
}
