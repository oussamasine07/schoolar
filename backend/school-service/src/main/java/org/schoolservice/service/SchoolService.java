package org.schoolservice.service;

import io.jsonwebtoken.Claims;
import org.flywaydb.core.api.FlywayException;
import org.schoolservice.dto.request.SchoolValidationDTO;
import org.schoolservice.exception.NotFoundException;
import org.schoolservice.model.School;
import org.schoolservice.repo.SchoolRepo;
import org.schoolservice.utils.TenentUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class SchoolService {

    private final JwtService jwtService;
    private final SchoolRepo schoolRepo;
    private final SchemaService schemaService;
    private final FlywayMigrationService flywayMigrationService;
    private final TenentUtil tenentUtil;
    private final DataSource dataSource;

    public SchoolService (
            final JwtService jwtService,
            final SchoolRepo schoolRepo,
            final SchemaService schemaService,
            final FlywayMigrationService flywayMigrationService,
            final TenentUtil tenentUtil,
            final DataSource dataSource
    ) {
        this.jwtService = jwtService;
        this.schoolRepo = schoolRepo;
        this.schemaService = schemaService;
        this.flywayMigrationService = flywayMigrationService;
        this.tenentUtil = tenentUtil;
        this.dataSource = dataSource;
    }

    public ResponseEntity<List<School>> listOwnerSchools (String bearerToken) {

        Long ownerId = jwtService.extractUserId( bearerToken );

        List<School> schools = schoolRepo.findSchoolsByOwnerId(ownerId);

        return new ResponseEntity<>(schools, HttpStatus.OK);

    }

    public ResponseEntity<School> showSchoolByOwnerId (Long schoolId) {

        School foundSchool = schoolRepo.findById( schoolId ).orElseThrow(() -> new NotFoundException("this school not found"));

        return new ResponseEntity<>(foundSchool, HttpStatus.OK);
    }

    // create a new school
    public ResponseEntity<School> createNewSchool ( SchoolValidationDTO schoolValidationDTO, String token ) {
        try {
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
            String schemaName = tenentUtil.nameTenent( savedSchool.getSchoolName() );

            String createdSchema = schemaService.createSchema( schemaName );

            // migrate all tables inside this schema
            flywayMigrationService.migrate(dataSource, createdSchema);

            savedSchool.setReady( true );
            savedSchool.setTnName( savedSchool.getSchoolName() );
            savedSchool = schoolRepo.save( savedSchool );

            // TODO: make sure to run migrations for other services after school creation
            return new ResponseEntity<>(savedSchool, HttpStatus.OK);
        }
        catch (FlywayException flywayException) {
            throw flywayException;
        }
        catch (DataAccessException dataAccessException) {
            throw  dataAccessException;
        }
        catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
    }

    // update school
    public ResponseEntity<School> updateSchool (SchoolValidationDTO schoolValidationDTO, Long schoolId) {
        School foundSchool = schoolRepo.findById( schoolId )
                .orElseThrow(() -> new NotFoundException("unfound school"));

        foundSchool.setSchoolName( schoolValidationDTO.schoolName() );
        foundSchool.setEmail( schoolValidationDTO.email() );
        foundSchool.setPhone( schoolValidationDTO.phone() );
        foundSchool.setAddress( schoolValidationDTO.address() );
        foundSchool.setCity( schoolValidationDTO.city() );
        foundSchool.setTaxId( schoolValidationDTO.taxId() );
        foundSchool.setProfessionalTax( schoolValidationDTO.professionalTax() );
        foundSchool.setCommercialRegister( schoolValidationDTO.commercialRegister() );
        foundSchool.setCommonBusinessIdentifier( schoolValidationDTO.commonBusinessIdentifier() );
        foundSchool.setCnssAffiliation( schoolValidationDTO.cnssAffiliation() );

        return new ResponseEntity<>(schoolRepo.save( foundSchool ), HttpStatus.OK);
    }


}











