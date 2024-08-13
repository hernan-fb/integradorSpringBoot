package com.api.email.emailsender.controller.doc;

import com.api.email.emailsender.entity.EmailDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name= "internet utils", description="API exposed for management all utilities")
@RequestMapping("/api/v1/mailsender")
public interface EmailDoc {
    @Operation(summary="send Mail", description="This operation is for the sending of an e-mail")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping("/sendMail")
    ResponseEntity<String> sendMail(@Valid @RequestBody EmailDetails userEntity);

    @Operation(summary="send Mail With Attachment", description="This operation is for the sending of an e-mail With Attachment")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description="user created",
            content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500",
                    description="internal server error",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404",
                    description="end point not found",
                    content= @Content(mediaType= MediaType.APPLICATION_JSON_VALUE))})
    @PostMapping("/sendMailWithAttachment")
    ResponseEntity<String> sendMailWithAttachment(@Valid @RequestBody EmailDetails userEntity);

}
