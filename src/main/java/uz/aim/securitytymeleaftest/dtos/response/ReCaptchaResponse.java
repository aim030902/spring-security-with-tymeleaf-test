package uz.aim.securitytymeleaftest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReCaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostname;

}
