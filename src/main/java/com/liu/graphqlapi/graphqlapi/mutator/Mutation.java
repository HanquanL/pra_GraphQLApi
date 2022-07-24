package com.liu.graphqlapi.graphqlapi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.liu.graphqlapi.graphqlapi.entity.Dog;
import com.liu.graphqlapi.graphqlapi.exception.BreedNotFoundException;
import com.liu.graphqlapi.graphqlapi.exception.DogNotFoundException;
import com.liu.graphqlapi.graphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        //loop through all dogs to check their breed
        for(Dog d:allDogs){
            if(d.getBreed().equals(breed)){
                //delete if the breed is found
                dogRepository.delete(d);
                deleted = true;
            }
        }
        //throw an exception if the breed doesn't exist
        if(!deleted){
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            //set the new name and save the updated dog
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        }else{
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
