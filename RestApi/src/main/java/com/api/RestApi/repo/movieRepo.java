package com.api.RestApi.repo;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.api.RestApi.entity.movies;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Repository
public interface movieRepo extends PagingAndSortingRepository<movies,Integer> {
	

}
