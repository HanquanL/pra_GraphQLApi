package com.liu.graphqlapi.graphqlapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.liu.graphqlapi.graphqlapi.entity.Dog;
import com.liu.graphqlapi.graphqlapi.exception.DogNotFoundException;
import com.liu.graphqlapi.graphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs(){
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()){
            return optionalDog.get();
        }else{
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
