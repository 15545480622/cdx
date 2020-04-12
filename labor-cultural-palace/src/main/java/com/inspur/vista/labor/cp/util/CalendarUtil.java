package com.inspur.vista.labor.cp.util;

import com.inspur.vista.labor.cp.dao.SpecialDateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Title: CalendarUtil
 * @Description: //TODO
 * @Author: gengpeng
 * @CreateDate: 2019/10/12 13:39
 * @Version: 1.0
 */
public class CalendarUtil {

    private final Logger logger = LoggerFactory.getLogger(CalendarUtil.class);

    /**
     * 法定节假日
     */
    private static final int LAWHOLIDAY = 1;

    /**
     * 额外工作的周末
     */
    private static final int EXTRAWORKDAY = 2;

    /**
     * 法律规定的放假日期
     */
    private List<String> lawHolidays = new ArrayList<>();

    /**
     * 由于放假需要额外工作的周末
     */
    private List<String> extraWorkdays = new ArrayList<>();

    /**
     * 相当于线程锁,用于线程安全
     */
    private final static Object SYNC_LOCK = new Object();

    /**
     * 启动类set入，调用下面set方法
     */
    private static ApplicationContext applicationContext;

    private static volatile CalendarUtil defaultInstance;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static CalendarUtil getInstance() {
        if (defaultInstance == null) {
            synchronized (CalendarUtil.class) {
                if (defaultInstance == null) {
                    defaultInstance = new CalendarUtil();
                }
            }
        }
        return defaultInstance;
    }

    public void initDateList() {
        synchronized (SYNC_LOCK) {
            SpecialDateMapper specialDateMapper = applicationContext.getBean(SpecialDateMapper.class);
            List<String> lawHolidayList = specialDateMapper.listByDateType(LAWHOLIDAY);
            if (!lawHolidayList.isEmpty()) {
                lawHolidays.clear();
                lawHolidays.addAll(lawHolidayList);
                logger.info("初始化法定节假日列表完成，共{}条", lawHolidays.size());
            } else {
                logger.info("初始化法定节假日列表完成，共0条");
            }
            List<String> extraWorkdayList = specialDateMapper.listByDateType(EXTRAWORKDAY);
            if (!extraWorkdayList.isEmpty()) {
                extraWorkdays.clear();
                extraWorkdays.addAll(extraWorkdayList);
                logger.info("初始化额外工作的周末列表完成，共{}条", extraWorkdays.size());
            } else {
                logger.info("初始化额外工作的周末列表完成，共0条");
            }
        }
    }

    /**
     * @author qyw
     * @description 判断是否是法定假日
     * @date Created in 21:03 2019/1/31
     **/
    public boolean isLawHoliday(String calendar) {
        return lawHolidays.contains(calendar);
    }

    /**
     * @author qyw
     * @description 判断是否是周末
     * @date Created in 21:03 2019/1/31
     **/
    public boolean isWeekends(String calendar) {
        // 先将字符串类型的日期转换为Calendar类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(calendar);
        } catch (ParseException e) {
            logger.error("格式化失败", e);
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    /**
     * @author qyw
     * @description 判断是否是需要额外补班的周末
     * @date Created in 21:06 2019/1/31
     **/
    public boolean isExtraWorkday(String calendar) {
        return extraWorkdays.contains(calendar);
    }

    /**
     * @author qyw
     * @description 判断是否是休息日（包含法定节假日和不需要补班的周末）
     * @date Created in 21:06 2019/1/31
     **/
    public boolean isHoliday(String calendar) {
        // 首先法定节假日必定是休息日
        if (this.isLawHoliday(calendar)) {
            return true;
        }
        // 排除法定节假日外的非周末必定是工作日
        if (!this.isWeekends(calendar)) {
            return false;
        }
        // 所有周末中只有非补班的才是休息日
        if (this.isExtraWorkday(calendar)) {
            return false;
        }
        return true;
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return
     */
    public List<String> getDays(Date start, Date end) {

        // 返回的日期集合
        List<String> days = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
        cd.setTime(start);
        cd.add(Calendar.DATE, 1);
        start = cd.getTime();
        while (start.getTime() <= end.getTime()) {
            days.add(dateFormat.format(start));
            cd.setTime(start);
            cd.add(Calendar.DATE, 1);//增加一天 放入集合
            start = cd.getTime();
        }

        return days;
    }
}
