package edu.uwp.appfactory.demo.routes;

import edu.uwp.appfactory.demo.controllers.DemoController;
import edu.uwp.appfactory.demo.entities.Demo;
import edu.uwp.appfactory.demo.requestObjects.DemoRequest;
import edu.uwp.appfactory.demo.responseObjects.DemoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;

// router class
// create endpoints for api
// www.google.com/demo/all .. get all googles?
// you declare here what endpoints your api takes in, and what is returned
// all routes communicate with a controller (mostly)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/demo")
public class DemoRoutes {
    private final DemoController demoController;

    public DemoRoutes(DemoController demoController) {
        this.demoController = demoController;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DemoRequest demoRequest) {
        return demoController.create(demoRequest)
                ? ResponseEntity.status(NO_CONTENT).build()
                : ResponseEntity.status(BAD_REQUEST).body("Error");
    }

    @GetMapping("")
    public ResponseEntity<Demo> getOne(@RequestBody DemoRequest demoRequest) {
        Demo data = demoController.getOne(demoRequest);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<DemoResponse> getOneOnlyName(@RequestBody DemoRequest demoRequest) {
        DemoResponse data = demoController.getOneOnlyName(demoRequest);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Demo>> getAll() {
        List<Demo> data = demoController.getAll();
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @GetMapping("/uuid")
    public ResponseEntity<List<Demo>> findAnyByUUID(@RequestHeader("DemoUUID") final String demoUUID) {
        List<Demo> data = demoController.findAnyByUUID(UUID.fromString(demoUUID));
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @PatchMapping(value = "")
    public ResponseEntity<Demo> update(@RequestHeader("DemoUUID") final String demoUUID,
                                    @RequestBody DemoRequest demoRequest) {
        Demo data = demoController.updateByUUID(UUID.fromString(demoUUID), demoRequest);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestHeader("DemoUUID") final String demoUUID) {
        return demoController.deleteById(UUID.fromString(demoUUID))
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(400).body(null);
    }
}
