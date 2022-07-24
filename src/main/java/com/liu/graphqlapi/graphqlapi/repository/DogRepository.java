package com.liu.graphqlapi.graphqlapi.repository;

import com.liu.graphqlapi.graphqlapi.entity.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
}
