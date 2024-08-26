package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DifferPropertyDescription {

    public static final String NO_VALUE = "noValue";
    private String propertyName;
    private Object preferValue;
    private Object newValue;

}
