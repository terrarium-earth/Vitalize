package earth.terrarium.vitalize.util.extensions;

import java.lang.annotation.*;

/**
 * Indicates somethin is bein ignored
 */

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Ignored {
}
