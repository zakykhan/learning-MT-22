package com.sagapattern.userService.projection;
//Projection means query handler

import com.sagapattern.commonService.model.CardDetails;
import com.sagapattern.commonService.model.User;
import com.sagapattern.commonService.queries.GetUserPaymentDetailsQuery;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query){

        //Ideally get the details from DB not to put static

        CardDetails cardDetails = CardDetails.builder()
                .name("Zaky Khan")
                .cardNumber("123456789")
                .validTillMonth(2)
                .validUntilYear(2023)
                .cvv(123)
                .build();

        return User.builder()
                .userId(query.getUserId())
                .cardDetails(cardDetails)
                .firstName("Zaky")
                .lastName("Khan")
                .build();
    }
}
