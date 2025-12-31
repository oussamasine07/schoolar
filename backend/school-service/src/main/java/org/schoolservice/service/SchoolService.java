package org.schoolservice.service;

import io.jsonwebtoken.Claims;
import org.schoolservice.dto.request.SchoolValidationDTO;
import org.schoolservice.model.School;
import org.schoolservice.repo.SchoolRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    private final JwtService jwtService;
    private final SchoolRepo schoolRepo;
    private final SchemaService schemaService;

    public SchoolService (
            final JwtService jwtService,
            final SchoolRepo schoolRepo,
            final SchemaService schemaService
    ) {
        this.jwtService = jwtService;
        this.schoolRepo = schoolRepo;
        this.schemaService = schemaService;
    }

    // create a new school
    public ResponseEntity<School> createNewSchool ( SchoolValidationDTO schoolValidationDTO, String token ) {
        // create school
        School newSchool = new School();

        token = token.split(" ")[1];

        Claims claims = jwtService.extractAllClaims( token );
        Long ownerId = claims.get("id", Long.class);
        newSchool.setOwnerId(ownerId);

        newSchool.setSchoolName( schoolValidationDTO.schoolName() );
        newSchool.setEmail( schoolValidationDTO.email() );
        newSchool.setPhone( schoolValidationDTO.phone() );
        newSchool.setAddress( schoolValidationDTO.address() );
        newSchool.setCity( schoolValidationDTO.city() );
        newSchool.setTaxId( schoolValidationDTO.taxId() );
        newSchool.setProfessionalTax( schoolValidationDTO.professionalTax() );
        newSchool.setCommercialRegister( schoolValidationDTO.commercialRegister() );
        newSchool.setCommonBusinessIdentifier( schoolValidationDTO.commonBusinessIdentifier() );
        newSchool.setCnssAffiliation( schoolValidationDTO.cnssAffiliation() );
        newSchool.setActive( true );

        // save school
        School savedSchool = schoolRepo.save( newSchool );

        // create schema name, it should be like so (tn_school_name)
        String schemaName = "tn_" + savedSchool.getSchoolName().replace(" ", "_");
        schemaService.createSchema( schemaName );

        // migrate all tables inside this schema
        return new ResponseEntity<>(savedSchool, HttpStatus.OK);

    }

}











