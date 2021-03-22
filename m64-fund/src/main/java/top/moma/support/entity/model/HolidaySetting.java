package top.moma.support.entity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * HolidaySetting
 * Holiday Setting File
 *
 * @author Ivan
 * @version 1.0
 * Created by Ivan at 2021/3/22.
 **/
@NoArgsConstructor
@Data
public class HolidaySetting {
    @JsonProperty("holiday")
    private List<String> holiday;
    @JsonProperty("workday")
    private List<String> workday;
}