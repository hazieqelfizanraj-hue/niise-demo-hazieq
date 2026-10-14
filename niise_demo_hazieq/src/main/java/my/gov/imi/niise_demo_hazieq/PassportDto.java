package my.gov.imi.niise_demo_hazieq;

import jakarta.validation.constraints.NotBlank;

public class PassportDto {

    private Integer id;

    @NotBlank(message = "Full name is required")
    private String fullname;

    @NotBlank(message = "IC number is required")
    private String icno;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getIcno() { return icno; }
    public void setIcno(String icno) { this.icno = icno; }
}
