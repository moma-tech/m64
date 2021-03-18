package top.moma.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.date.DateTimeHelper;
import top.moma.support.SupportConstants;
import top.moma.support.entity.model.OfficialHoliday;
import top.moma.support.repo.OfficialHolidayRepository;

import java.time.LocalDate;

/**
 * OfficialHolidayController Official Holiday Controller
 *
 * @author ivan
 * @version 1.0 Created by ivan at 3/18/21.
 */
@RestController
@RequestMapping("/support")
public class OfficialHolidayController {

  @Autowired OfficialHolidayRepository officialHolidayRepository;

  @PostMapping("/holiday/add")
  public String addOfficialHoliday(String date) {
    LocalDate localDate = DateTimeHelper.parseDate(date, DateTimeHelper.getDateFormat(date));
    OfficialHoliday dayQuery = OfficialHoliday.builder().officialDate(localDate).build();
    Example<OfficialHoliday> dayExample = Example.of(dayQuery);
    if (officialHolidayRepository.exists(dayExample)) {
      return "Existed";
    }

    officialHolidayRepository.save(
        OfficialHoliday.builder()
            .officialDate(localDate)
            .dayType(SupportConstants.OFFICIAL_HOLIDAY)
            .build());
    return localDate.toString();
  }

  @PostMapping("/workday/add")
  public String addOfficialWorkday(String date) {
    LocalDate localDate = DateTimeHelper.parseDate(date, DateTimeHelper.getDateFormat(date));
    OfficialHoliday dayQuery = OfficialHoliday.builder().officialDate(localDate).build();
    Example<OfficialHoliday> dayExample = Example.of(dayQuery);
    if (officialHolidayRepository.exists(dayExample)) {
      return "Existed";
    }
    officialHolidayRepository.save(
        OfficialHoliday.builder()
            .officialDate(localDate)
            .dayType(SupportConstants.OFFICIAL_WORKDAY)
            .build());
    return localDate.toString();
  }

  @GetMapping("/dayCheck")
  public Integer checkIfWorkDay(String date) {
    LocalDate localDate = DateTimeHelper.parseDate(date, DateTimeHelper.getDateFormat(date));

    OfficialHoliday dayQuery = OfficialHoliday.builder().officialDate(localDate).build();
    Example<OfficialHoliday> dayExample = Example.of(dayQuery);

    OfficialHoliday officialHoliday = officialHolidayRepository.findOne(dayExample).orElse(null);
    if (ObjectHelper.isNotEmpty(officialHoliday)) {
      return officialHoliday.getDayType();
    } else {
      int dayOfWeek = DateTimeHelper.getDayOfWeek(date);
      if (dayOfWeek < 6) {
        return SupportConstants.WORKDAY;
      } else {
        return SupportConstants.WEEKEND;
      }
    }
  }
}
