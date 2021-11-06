package com.challenge.tenpo.controller;

import com.challenge.tenpo.model.ChallengeModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/challenge")
public class ChallengeController {

    @PostMapping
    public ResponseEntity<ChallengeModel> addNumbers(@RequestBody ChallengeModel challengeModel) {
        ChallengeModel response = new ChallengeModel();
        response.setNumberOne(challengeModel.getNumberOne());
        response.setNumberTwo(challengeModel.getNumberTwo());
        response.setResult(challengeModel.getNumberOne() + challengeModel.getNumberTwo());
        return ResponseEntity.ok(response);
    }
}
