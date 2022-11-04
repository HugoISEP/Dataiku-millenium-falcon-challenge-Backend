package com.starwars.millenniumfalcononboardcomputer.controller;

import com.starwars.millenniumfalcononboardcomputer.model.dto.EmpireInformationDto;
import com.starwars.millenniumfalcononboardcomputer.service.MillenniumFalconService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MillenniumFalconController {
    private final MillenniumFalconService millenniumFalconService;

    /**
     *
     * @param empireInformationDto countdown and bounty hunters' positions
     * @return Http status code 200 with probability of the Millennium Falcon to reach the destination
     */
    @PostMapping
    public ResponseEntity<Float> computeOdds(@Valid @RequestBody EmpireInformationDto empireInformationDto) {
        return ResponseEntity.ok(millenniumFalconService.computeBestOddsToReachDestination(empireInformationDto));
    }
}
