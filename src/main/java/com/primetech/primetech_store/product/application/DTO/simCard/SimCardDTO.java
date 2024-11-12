package com.primetech.primetech_store.product.application.DTO.simCard;

import com.primetech.primetech_store.product.domain.models.SimCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimCardDTO {
    private Boolean isDualSim;
    private Integer simSlots;
    private Boolean esim;
    private String simType;

    public static SimCardDTO from(SimCard simCard) {
        return new SimCardDTO(
                simCard.isDualSim(),
                simCard.getSimSlots(),
                simCard.isEsim(),
                simCard.getSimType()
        );
    }
}
