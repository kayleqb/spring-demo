package edu.uwp.appfactory.demo.controllers;

import edu.uwp.appfactory.demo.entities.Demo;
import edu.uwp.appfactory.demo.mappers.DemoMapper;
import edu.uwp.appfactory.demo.repositories.DemoRepository;
import edu.uwp.appfactory.demo.requestObjects.DemoRequest;
import edu.uwp.appfactory.demo.responseObjects.DemoResponse;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// controller class
// takes in the request info from the router, and uses the repo's to access db objects
@Controller
public class DemoController {
    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    public DemoController(DemoRepository demoRepository, DemoMapper demoMapper) {
        this.demoRepository = demoRepository;
        this.demoMapper = demoMapper;
    }

    // create a demo object
    public boolean create(DemoRequest demoRequest) {
        if (!demoRepository.existsByName(demoRequest.getName())) {
            Demo demo = new Demo(demoRequest.getName());
            demoRepository.save(demo);
            return true;
        } else {
            return false;
        }
    }

    // get only one demo object
    public Demo getOne(DemoRequest demoRequest) {
        Optional<Demo> demo = demoRepository.findByName(demoRequest.getName());
        return demo.orElse(null);
    }

    // get only one demo object but it only sends the name, not id, but using mapper
    public DemoResponse getOneOnlyName(DemoRequest demoRequest) {
        Optional<Demo> demo = demoRepository.findByName(demoRequest.getName());
        return demo.map(demoMapper::map).orElse(null);
    }

    // get all demo objects
    public List<Demo> getAll() {
        return (List<Demo>) demoRepository.findAll();
    }

    public List<Demo> findAnyByUUID(UUID uuid) {
        return demoRepository.findAnyByUUID(uuid);
    }

    // update a demo object in the db by finding it with the objects id
    public Demo updateByUUID(UUID uuid, DemoRequest demoRequest) {
        Optional<Demo> demoOptional = demoRepository.findById(uuid);
        if (demoOptional.isPresent()) {
            Demo demo = demoOptional.get();
            demo.setName(demoRequest.getName());
            demoRepository.save(demo);
            return demo;
        } else
            return null;
    }

    // delete a demo object using the objects uuid
    public boolean deleteById(UUID uuid) {
        Optional<Demo> demoOptional = demoRepository.findById(uuid);
        if (demoOptional.isPresent()) {
            Demo demo = demoOptional.get();
            demoRepository.delete(demo);
            return true;
        } else {
            return false;
        }
    }
}
