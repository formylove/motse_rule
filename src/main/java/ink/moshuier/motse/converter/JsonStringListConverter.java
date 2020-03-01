package ink.moshuier.motse.converter;

import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class JsonStringListConverter extends AbstractJsonConverter<List<String>> {
}
