package com.petstore.demo.service;

import com.petstore.demo.Response.ResponseEntity;
import com.petstore.demo.model.Category;
import com.petstore.demo.model.Pet;
import com.petstore.demo.model.Tag;
import com.petstore.demo.reposiotry.CategoryRepository;
import com.petstore.demo.reposiotry.PetRepository;
import com.petstore.demo.reposiotry.TagRepository;
import com.petstore.demo.request.PetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PetSerivce {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;
    public ResponseEntity addPet(PetRequest petRequest){
        ResponseEntity responseEntity = new ResponseEntity();
        Pet pet = new Pet();
    try{

        pet.setId(petRequest.getId());
        pet.setName(petRequest.getName());
        Category category = new Category();
        category.setId(petRequest.getCategory().getId());
        category.setName(petRequest.getCategory().getName());
        category = categoryRepository.save(category);
        pet.setCategory(category);
        List<Tag> tagList = new ArrayList<>();
        Tag tag = new Tag();
        tag.setId(petRequest.getId());
        tag.setName(petRequest.getName());
        tag = tagRepository.save(tag);
        pet.setTags(tag);

        String photourls = petRequest.getPhotoUrls().stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
       pet.setPhotoUrls(photourls);
       pet.setStatus(petRequest.getStatus());
       petRepository.save(pet);

    }catch (Exception e){
        responseEntity.setStatus("500");
        responseEntity.setStatusMessage(e.getMessage());
        responseEntity.setResponse(null);
        return responseEntity;
    }
    responseEntity.setStatus("200");
    responseEntity.setStatusMessage("Data inserted");
    responseEntity.setResponse(pet);
        return responseEntity;
    }

    public ResponseEntity updatePet(PetRequest petRequest,long id){
        ResponseEntity responseEntity = new ResponseEntity();
        Optional<Pet> petDb = petRepository.findById(id) ;
        Pet pet = new Pet();
        try{
            if(petDb == null)
                throw new Exception("Value Not Found");
            pet = petDb.get();
            pet.setId(petRequest.getId());
            pet.setName(petRequest.getName());
            Category category = new Category();
            category.setId(petRequest.getCategory().getId());
            category.setName(petRequest.getCategory().getName());
            category = categoryRepository.save(category);
            pet.setCategory(category);
            List<Tag> tagList = new ArrayList<>();
            Tag tag = new Tag();
            tag.setId(petRequest.getId());
            tag.setName(petRequest.getName());
            tag = tagRepository.save(tag);
            pet.setTags(tag);

            String photourls = petRequest.getPhotoUrls().stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining("-", "{", "}"));
            pet.setPhotoUrls(photourls);
            pet.setStatus(petRequest.getStatus());
            petRepository.save(pet);

        }catch (Exception e){
            responseEntity.setStatus("500");
            responseEntity.setStatusMessage(e.getMessage());
            responseEntity.setResponse(null);
            return responseEntity;
        }
        responseEntity.setStatus("200");
        responseEntity.setStatusMessage("Data updated");
        responseEntity.setResponse(pet);
        return responseEntity;
    }


    public ResponseEntity getPet(Long id) {
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            Optional<Pet> petDb = petRepository.findById(id);
            if(petDb == null)
                throw new Exception("Value Not Found");
            Pet pet = petDb.get();
            responseEntity.setResponse(pet);
            responseEntity.setStatus("200");
            responseEntity.setStatusMessage("Data retrived for the id: "+ id);
        }catch (Exception e){

        }
        return responseEntity;
    }

    public ResponseEntity getAllPets() {
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            List<Pet> pets = petRepository.findAll();
            if(pets == null || pets.size() == 0)
                throw new Exception("No values are found");
            responseEntity.setResponse(pets);
            responseEntity.setStatus("200");
            responseEntity.setStatusMessage("Data retrived for the pets");
        }catch (Exception e){

        }
        return responseEntity;
    }

    public ResponseEntity deletePet(Long id) {
        ResponseEntity responseEntity = new ResponseEntity();
        try {
           Optional<Pet> petDb = petRepository.findById(id);
           if(petDb == null)
               throw new Exception("Value not found");
           Pet pet = petDb.get();
           Category category = pet.getCategory();
           Tag tag = pet.getTags();
           categoryRepository.deleteById(category.getId());
           tagRepository.deleteById(tag.getId());
           petRepository.deleteById(pet.getId());

            responseEntity.setResponse(pet);
            responseEntity.setStatus("200");
            responseEntity.setStatusMessage("Data deleted for the pet: " + id);
        }catch (Exception e){

        }
        return responseEntity;
    }

    public ResponseEntity getPetbyStatus(String[] status) {
        ResponseEntity responseEntity = new ResponseEntity();
        try {
        List<Pet> pets = petRepository.findByStatus(status);
            if(pets == null || pets.size() == 0)
                throw new Exception("Values Not Found");

            responseEntity.setResponse(pets);
            responseEntity.setStatus("200");
            responseEntity.setStatusMessage("Data retrived for the status: "+ status);
        }catch (Exception e){

        }
        return responseEntity;
    }
}
