package form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Andrey on 27.04.2017.
 */
public class AddWarehouseForm {

    @NotEmpty
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotEmpty

    private String address;
}
