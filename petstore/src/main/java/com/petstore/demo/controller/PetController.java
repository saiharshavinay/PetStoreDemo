package com.petstore.demo.controller;

import com.petstore.demo.Response.ResponseEntity;
import com.petstore.demo.reposiotry.PetRepository;
import com.petstore.demo.request.PetRequest;
import com.petstore.demo.service.PetSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class PetController {

    @Autowired
    PetSerivce petSerivce;
    @PostMapping("/pet")
    public @ResponseBody  ResponseEntity addPet(@RequestBody PetRequest petRequest){
        ResponseEntity responseEntity = new ResponseEntity();
        return  petSerivce.addPet(petRequest);

    }

    @PutMapping("/pet")
    public @ResponseBody  ResponseEntity updatePet(@RequestBody PetRequest petRequest,@PathVariable Long id){
        ResponseEntity responseEntity = new ResponseEntity();
        return  petSerivce.updatePet(petRequest,id);

    }

    @GetMapping("/pet/{id}")
    public @ResponseBody  ResponseEntity getPet(@PathVariable Long id){
        ResponseEntity responseEntity = new ResponseEntity();
        return  petSerivce.getPet(id);

    }
    @GetMapping("/pets/{status}")
    public @ResponseBody  ResponseEntity getPet(@PathVariable String[] status){
        ResponseEntity responseEntity = new ResponseEntity();
        return  petSerivce.getPetbyStatus(status);

    }
    @GetMapping("/pet")
    public @ResponseBody  ResponseEntity getAllPets(){
        ResponseEntity responseEntity = new ResponseEntity();
        return  petSerivce.getAllPets();
    }


    //Can do soft deletes instead of hard deletes

    @DeleteMapping("/pet/{id}")
    public @ResponseBody ResponseEntity deletePet(@PathVariable Long id){
        ResponseEntity responseEntity = new ResponseEntity();
        return petSerivce.deletePet(id);

    }


}
