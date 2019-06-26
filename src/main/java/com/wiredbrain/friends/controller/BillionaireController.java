package com.wiredbrain.friends.controller;

import com.wiredbrain.friends.model.Billionaire;
import com.wiredbrain.friends.service.BillionaireService;
import com.wiredbrain.friends.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BillionaireController {

    @Autowired
    BillionaireService billionaireService;

    @PostMapping("/billionaire")
    public Billionaire create(@RequestBody Billionaire b) throws ValidationException {
        if (b.getId() == 0 && b.getFirstName() != null && b.getLastName() != null) {
            return billionaireService.save(b);
        } else {
            throw new ValidationException("billionaire cannot be created from create method");
        }
    }

    @PostMapping("/billionaire2")
    public Billionaire create2(@Valid @RequestBody Billionaire b) {
        return billionaireService.save(b);
    }

    // for @Notblank in Billionaire.java, handler error for create2()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return fieldErrors
                .stream()
                .map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    //-------use controller exception handler

    //@ExceptionHandler(ValidationException.class)
    //ResponseEntity<String> exceptionHandler(ValidationException e) {
    //    return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    //}

    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(ValidationException.class)
    //ErrorMessage exceptionHandler(ValidationException e) {
    //    return new ErrorMessage("400", e.getMessage());
    //}

    @GetMapping("/billionaire")
    public Iterable<Billionaire> read() {
        return billionaireService.findAll();
    }

    @PutMapping("/billionaire")
    ResponseEntity<Billionaire> update(@RequestBody Billionaire b) {
        if (billionaireService.findById(b.getId()).isPresent()) {
            return new ResponseEntity(billionaireService.save(b), HttpStatus.OK);
        } else {
            return new ResponseEntity(b, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/billionaire/{id}")
    public void delete(@PathVariable Integer id) {
        billionaireService.deleteById(id);
    }

    @GetMapping("/billionaire/{id}")
    Optional<Billionaire> findById(@PathVariable Integer id) {
        return billionaireService.findById(id);
    }

    @GetMapping("/billionaire/search")
    Iterable<Billionaire> findByQuery(
            @RequestParam(value = "first", required = false) String firstName,
            @RequestParam(value = "last", required = false) String lastName) {
        if (firstName != null && lastName != null) {
            return billionaireService.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            return billionaireService.findByFirstName(firstName);
        } else if (lastName != null) {
            return billionaireService.findByLastName(lastName);
        } else {
            return billionaireService.findAll();
        }
    }

    @GetMapping("/wrong")
    public Billionaire somethingIsWrong() throws ValidationException {
        throw new ValidationException("Something is wrong");
    }
}
