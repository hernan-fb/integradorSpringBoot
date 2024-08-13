package dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    @NotBlank
    @NotEmpty
    @Size(min=6)
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "the document should be an e-mail address")
    private String to;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]*$",message = "the document should be a string")
    private String subject;

    @NotBlank
    @NotEmpty
    @Size(min=6)
    private String body;

    private String attachments;
}
