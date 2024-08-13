package controller.doc;

import dto.MailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import model.MailEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name= "internet utils", description="API exposed for management all utilities")
@RequestMapping("/api/v1/mailsender")
public interface MailSenderDoc {
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
    @PostMapping("")
    ResponseEntity<MailEntity> sendMail(@Valid @RequestBody MailDto userEntity);

}
