package az.mdm.depo.dto;


import lombok.*;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private int code;
    private String technicalMessage;
    private String userMessage;
    private Calendar timestamp;
}
