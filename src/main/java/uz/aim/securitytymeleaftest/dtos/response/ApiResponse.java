package uz.aim.securitytymeleaftest.dtos.response;

import lombok.*;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:02
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private String message;
    private T data;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
