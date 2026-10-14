
package my.gov.imi.niise_demo_hazieq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenggunaAdminDto extends PenggunaDto {

    private Integer id;

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}
}

