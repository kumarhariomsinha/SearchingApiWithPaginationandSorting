package com.api.RestApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.RestApi.entity.movies;
import com.api.RestApi.repo.movieRepo;

@Service
public class movieService {
	@Autowired
	private movieRepo movieRepos;
 public List<movies>movieWithSorting(String field){
	return (List<movies>) movieRepos.findAll(Sort.by(Sort.Direction.ASC,field));
	 
 }
 
 public Page<movies> findMoviesWithPagination(int offset,int pageSize){
	 Page<movies>movie=movieRepos.findAll(PageRequest.of(offset, pageSize));
	return movie;
 }
 
}
