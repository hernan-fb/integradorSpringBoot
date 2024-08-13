package com.ms.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {

    @NotBlank
    @NotEmpty
    @Size(min=8,max = 10)
    private String name;

    @NotBlank
    @NotEmpty
    @Size(min=8,max = 10)
    private String location;

    @NotBlank
    @NotEmpty
    @Size(min=8,max = 10)
    private String information;

}
