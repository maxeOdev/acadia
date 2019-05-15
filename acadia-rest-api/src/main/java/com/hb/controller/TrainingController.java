package com.hb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hb.model.Category;
import com.hb.model.Training;
import com.hb.service.TrainingService;

/**
 * 
 * @author anis
 *
 */
@RestController
public class TrainingController {

  @Autowired
  private TrainingService tngServ;

  /**
   * Creation of training URL
   * 
   * @param tng, training
   * @param ctg, category of the training
   * @param br, result of the check of params
   * @return the training that been created
   */
  @PostMapping("/trainings")
  public Training create(@Valid @RequestBody Training tng, @Valid @RequestBody Category ctg,
          BindingResult br) {
    if (!br.hasErrors()) {
      return tngServ.createTraining(tng, ctg, null);
    }
    return null;
  }

  /**
   * Method retrieving a training from its uuid
   * 
   * @param uuid
   * @return the training matching with the given uuid
   */
  @GetMapping("/trainings/{id}")
  public Training get(@PathVariable String uuid) {
    return tngServ.getByUuid(uuid);
  }

}
