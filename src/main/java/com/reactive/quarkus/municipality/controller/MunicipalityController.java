package com.reactive.quarkus.municipality.controller;

import com.reactive.quarkus.municipality.converter.dto.MunicipalityRequestDto;
import com.reactive.quarkus.municipality.converter.dto.MunicipalityResponseDto;
import com.reactive.quarkus.municipality.converter.dto.SaveMunicipalityResponseDto;
import com.reactive.quarkus.municipality.service.MunicipalityService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.Set;

import static com.reactive.quarkus.municipality.utility.ProcessResponses.processTheResultFromService;


@Path("/municipality")
public final class MunicipalityController {
    private final MunicipalityService municipalityService;

    public MunicipalityController(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }
    @GET
    @Path("/getAllMunicipalities")
    @ResponseStatus(200)
    @Operation(summary = "Get all people", description = "Fetch all people.")
    @APIResponse(responseCode = "200", description = "Successfully retrieved the list of people",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MunicipalityResponseDto.class)))
    public Multi<Set<MunicipalityResponseDto>> getAllMunicipalities() {
        return municipalityService.getAllMunicipalities();
    }
    @POST
    @Path("/createMunicipality")
    @Operation(summary = "Create a new municipality", description = "Create a new municipality record.")
    @APIResponse(responseCode = "201", description = "Municipality successfully created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaveMunicipalityResponseDto.class)))
    @APIResponse(responseCode = "400", description = "Invalid input data")
    public Uni<Response> createMunicipality(@Valid MunicipalityRequestDto municipalityRequestDto) {
        return processTheResultFromService(municipalityService.createMunicipality(municipalityRequestDto), Response.Status.CREATED);
    }

}
