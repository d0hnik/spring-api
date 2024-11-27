package validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationError {
    @NonNull
    private String code;
    private List<String> arguments;

    @Override
    public String toString() {
        return "ValidationError{" +
                "code='" + code + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}