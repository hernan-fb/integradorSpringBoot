package com.ms.hotel.dto;

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
    @Size(min=8,max = 100)
    private String name;

    @NotBlank
    @NotEmpty
    @Size(min=8,max = 100)
    private String location;

    @NotBlank
    @NotEmpty
    @Size(min=8,max = 100)
    private String information;

}
