package pdp.uz.appapi.payload;

import lombok.Data;


import javax.validation.constraints.NotNull;

@Data
public class CustomerDto {

    @NotNull(message = "First name can't be empty")
    private String firstName;

    private String lastName;

    @NotNull(message = "phone number can't be empty")
    private String phoneNumber;

    @NotNull(message = "Address can't be empty")
    private String address;

}
