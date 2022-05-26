package by.liashuk.exerciseProject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity of authentication request DTO")
public class AuthenticationRequest {

    @Schema(description = "Login of user", example = "asd")
    private String login;
    @Schema(description = "Password of user", example = "asd")
    private String password;
}
