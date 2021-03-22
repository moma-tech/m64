package top.moma.support.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.moma.m64.core.helper.ObjectHelper;
import top.moma.m64.core.helper.date.DateTimeHelper;
import top.moma.m64.core.helper.io.FileHelper;
import top.moma.m64.core.helper.json.JsonHelper;
import top.moma.support.SupportConstants;
import top.moma.support.entity.model.HolidaySetting;
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
    OfficialHoliday officialHoliday = buildHoliday(date, SupportConstants.OFFICIAL_HOLIDAY);
    if (ObjectHelper.isEmpty(officialHoliday)) {
      return "Existed";
    }
    officialHolidayRepository.save(officialHoliday);
    return officialHoliday.getOfficialDate().toString();
  }

  @PostMapping("/workday/add")
  public String addOfficialWorkday(String date) {
    OfficialHoliday officialHoliday = buildHoliday(date, SupportConstants.OFFICIAL_WORKDAY);
    if (ObjectHelper.isEmpty(officialHoliday)) {
      return "Existed";
    }
    officialHolidayRepository.save(officialHoliday);
    return officialHoliday.getOfficialDate().toString();
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

  @GetMapping("/importSetting")
  public String importSetting(String settingName) {
    if (ObjectHelper.isEmpty(settingName)) {
      settingName = "holiday-2021.json";
    }
    String content = ObjectHelper.toString(FileHelper.readBytes("classpath:" + settingName));
    HolidaySetting holidaySetting = JsonHelper.readValue(content, HolidaySetting.class);
    OfficialHoliday officialHoliday = null;
    if (ObjectHelper.isNotEmpty(holidaySetting.getHoliday())) {
      for (String holiday : holidaySetting.getHoliday()) {
        officialHoliday = buildHoliday(holiday, SupportConstants.OFFICIAL_HOLIDAY);
        if (ObjectHelper.isEmpty(officialHoliday)) {
          return "Existed";
        }
        officialHolidayRepository.save(officialHoliday);
      }
    }
    if (ObjectHelper.isNotEmpty(holidaySetting.getWorkday())) {
      for (String holiday : holidaySetting.getHoliday()) {
        officialHoliday = buildHoliday(holiday, SupportConstants.OFFICIAL_WORKDAY);
        if (ObjectHelper.isEmpty(officialHoliday)) {
          return "Existed";
        }
        officialHolidayRepository.save(officialHoliday);
      }
    }
    return null;
  }

  private OfficialHoliday buildHoliday(String date, int dayType) {
    LocalDate localDate = DateTimeHelper.parseDate(date, DateTimeHelper.getDateFormat(date));
    OfficialHoliday dayQuery = OfficialHoliday.builder().officialDate(localDate).build();
    Example<OfficialHoliday> dayExample = Example.of(dayQuery);
    if (officialHolidayRepository.exists(dayExample)) {
      return null;
    }
    return OfficialHoliday.builder().officialDate(localDate).dayType(dayType).build();
  }
}
