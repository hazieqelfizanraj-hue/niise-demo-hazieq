
package my.gov.imi.niise_demo_hazieq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenggunaDto {

    @NotBlank(message = "Nama is required")
    private String nama;

    @NotBlank(message = "IC number is required")
    private String icno;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;
}
