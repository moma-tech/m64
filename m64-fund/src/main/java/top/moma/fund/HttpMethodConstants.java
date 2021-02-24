package top.moma.fund;

import java.lang.annotation.Annotation;

/**
 * HttpMethodConstants
 *
 * @author ivan
 * @version 1.0 Created by ivan at 2/24/21.
 */
public interface HttpMethodConstants {
  Class<? extends Annotation> GET = retrofit2.http.GET.class;
  Class<? extends Annotation> POST = retrofit2.http.POST.class;
}
