package moe.xing.baseutils.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Qi xingchen on 16-9-27.
 * <p>
 * Test for {@link DateUtils}
 */
public class DateUtilsTest {
    @Test
    public void getPastDateString() throws Exception {
        assertEquals(DateUtils.formatTo4y_MM_dd(Calendar.getInstance().getTime()), DateUtils.getPastDateString(0));


    }

    @Test
    public void getUnixStart() throws Exception {
        assertEquals("1970-1-1", DateUtils.getUnixStart());
    }

    @Test
    public void addMonth() throws Exception {
        Calendar calendar = Calendar.getInstance();
        int months = getDateMonths(calendar);

        calendar = Calendar.getInstance();
        DateUtils.addMonth(calendar, 1);
        assertEquals(months + 1, getDateMonths(calendar));

        calendar = Calendar.getInstance();
        DateUtils.addMonth(calendar, -1);
        assertEquals(months - 1, getDateMonths(calendar));

        calendar = Calendar.getInstance();
        DateUtils.addMonth(calendar, 0);
        assertEquals(months, getDateMonths(calendar));

        calendar = Calendar.getInstance();
        DateUtils.addMonth(calendar, 12);
        assertEquals(months + 12, getDateMonths(calendar));

        calendar = Calendar.getInstance();
        DateUtils.addMonth(calendar, 13);
        assertEquals(months + 13, getDateMonths(calendar));

    }

    private int getDateMonths(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return year * 12 + month;
    }


    @Test
    public void getNow() throws Exception {
        assertNotNull(DateUtils.getNow());
    }

    @Test
    public void getTodayAndClearTime() throws Exception {
        Date date = DateUtils.getTodayAndClearTime();
        assertEquals(0, date.getHours());
        assertEquals(0, date.getMinutes());
        assertEquals(0, date.getSeconds());
        assertEquals(DateUtils.formatTo4y_MM_dd(date), DateUtils.getPastDateString(0));
        assertEquals(DateUtils.formatToHH_mm_ss(date), "00:00:00");
    }

    @Test
    public void getDate() throws Exception {
        assertEquals(100, DateUtils.getDate(100).getTime());
        assertEquals(14524, DateUtils.getDate(14524).getTime());
        assertEquals(-5, DateUtils.getDate(-5).getTime());
    }

    @Test
    public void clearTime() throws Exception {
        Date date = Calendar.getInstance().getTime();
        assertEquals(DateUtils.formatToHH_mm_ss(DateUtils.clearTime(date)), "00:00:00");
    }

    @Test
    public void clearTime1() throws Exception {
        Calendar date = Calendar.getInstance();
        DateUtils.clearTime(date);
        assertEquals(DateUtils.formatToHH_mm_ss(date.getTime()), "00:00:00");
    }

    @Test
    public void getCalendarAndClearTime() throws Exception {
        Date date = Calendar.getInstance().getTime();
        assertEquals(DateUtils.formatToHH_mm_ss(DateUtils.getCalendarAndClearTime(date).getTime()), "00:00:00");
    }

    @Test
    public void getCalendar() throws Exception {
        Date date = Calendar.getInstance().getTime();
        assertEquals(date, DateUtils.getCalendar(date).getTime());
    }

    @Test
    public void betweenDateByDay() throws Exception {
        assertEquals(0, DateUtils.betweenDateByDay(DateUtils.getTodayAndClearTime(), DateUtils.getNow()));
    }

    @Test
    public void format() throws Exception {
        Date date = DateUtils.parse("2016-04-04", DateUtils.yyyy_MM_dd);
        assertEquals("2016-04-04", DateUtils.format(date, DateUtils.yyyy_MM_dd));
        assertEquals("2016-04-04 00:00:00", DateUtils.format(date, DateUtils.yyyy_MM_dd_HH_mm_ss));
        assertEquals("2016-04", DateUtils.format(date, "YYYY-MM"));
        assertEquals("2016", DateUtils.format(date, "YYYY"));
        assertEquals("04-04", DateUtils.format(date, "MM-dd"));
        assertEquals("4-4", DateUtils.format(date, "M-d"));
        assertEquals("04-4", DateUtils.format(date, "MM-d"));
    }

    @Test
    public void formatTo4yMMddHHmmss() throws Exception {
        Date date = DateUtils.parse("2016-04-04 12:3:54", DateUtils.yyyy_MM_dd_HH_mm_ss);
        assertEquals("20160404120354", DateUtils.formatTo4yMMddHHmmss(date));
    }

    @Test
    public void formatTo4y_MM_dd() throws Exception {
        Date date = DateUtils.parse("2016-04-04 12:3:54", DateUtils.yyyy_MM_dd_HH_mm_ss);
        assertEquals("2016-04-04", DateUtils.formatTo4y_MM_dd(date));
    }

    @Test
    public void formatTo4y_MM_dd_HH_mm_ss() throws Exception {
        Date date = DateUtils.parse("2016-04-04 12:3:54", DateUtils.yyyy_MM_dd_HH_mm_ss);
        assertEquals("2016-04-04 12:03:54", DateUtils.formatTo4y_MM_dd_HH_mm_ss(date));
    }

    @Test
    public void formatToHH_mm_ss() throws Exception {
        Date date = DateUtils.parse("2016-04-04 12:3:54", DateUtils.yyyy_MM_dd_HH_mm_ss);
        assertEquals("12:03:54", DateUtils.formatToHH_mm_ss(date));
    }

    @Test
    public void formatToHH_mm() throws Exception {
        Date date = DateUtils.parse("2016-04-04 12:3:54", DateUtils.yyyy_MM_dd_HH_mm_ss);
        assertEquals("12:03", DateUtils.formatToHH_mm(date));
    }

    @Test
    public void parse() throws Exception {
    }

    @Test
    public void getCalendar1() throws Exception {
        Calendar calendar = DateUtils.getCalendar(2016, 4);
        assertEquals(2016, calendar.get(Calendar.YEAR));
        assertEquals(3, calendar.get(Calendar.MONTH));

        calendar = DateUtils.getCalendar(2016, 13);
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(0, calendar.get(Calendar.MONTH));
    }

    @Test
    public void getCalendar2() throws Exception {
        Calendar calendar = DateUtils.getCalendar(2016, 4, 6);
        assertEquals(2016, calendar.get(Calendar.YEAR));
        assertEquals(3, calendar.get(Calendar.MONTH));
        assertEquals(6, calendar.get(Calendar.DAY_OF_MONTH));

        calendar = DateUtils.getCalendar(2016, 13, 6);
        assertEquals(2017, calendar.get(Calendar.YEAR));
        assertEquals(0, calendar.get(Calendar.MONTH));
        assertEquals(6, calendar.get(Calendar.DAY_OF_MONTH));

        calendar = DateUtils.getCalendar(2016, 3, 32);
        assertEquals(2016, calendar.get(Calendar.YEAR));
        assertEquals(3, calendar.get(Calendar.MONTH));
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void getFLDatesInMonth() throws Exception {
        Date[] dates = DateUtils.getFLDatesInMonth(2016, 2);
        assertEquals("2016-02-01", DateUtils.formatTo4y_MM_dd(dates[0]));
        assertEquals("2016-02-29", DateUtils.formatTo4y_MM_dd(dates[1]));

        dates = DateUtils.getFLDatesInMonth(2016, 4);
        assertEquals("2016-04-01", DateUtils.formatTo4y_MM_dd(dates[0]));
        assertEquals("2016-04-30", DateUtils.formatTo4y_MM_dd(dates[1]));
    }

    @Test
    public void isDateInMonth() throws Exception {
        Date date = DateUtils.parse("2016-04-30", DateUtils.yyyy_MM_dd);
        assertEquals(true, DateUtils.isDateInMonth(date, 4, 4));

        date = DateUtils.parse("2016-04-31", DateUtils.yyyy_MM_dd);
        assertEquals(false, DateUtils.isDateInMonth(date, 4, 4));

        date = DateUtils.parse("2012-06-30", DateUtils.yyyy_MM_dd);
        assertEquals(true, DateUtils.isDateInMonth(date, 2, 9));
    }

    @Test
    public void isDateInMonth1() throws Exception {
        Date date = DateUtils.parse("2016-04-30", DateUtils.yyyy_MM_dd);
        assertEquals(true, DateUtils.isDateInMonth(date, 4, 4, 2016));

        date = DateUtils.parse("2016-04-31", DateUtils.yyyy_MM_dd);
        assertEquals(false, DateUtils.isDateInMonth(date, 4, 4, 2016));

        date = DateUtils.parse("2012-06-30", DateUtils.yyyy_MM_dd);
        assertEquals(false, DateUtils.isDateInMonth(date, 2, 9, 2016));
        assertEquals(true, DateUtils.isDateInMonth(date, 2, 9, 2012));
    }

    @Test
    public void getWeek() throws Exception {
        assertEquals("日", DateUtils.getWeek(DateUtils.parse("2016-11-13", DateUtils.yyyy_MM_dd)));
        assertEquals("五", DateUtils.getWeek(DateUtils.parse("2016-11-11", DateUtils.yyyy_MM_dd)));
        assertEquals("一", DateUtils.getWeek(DateUtils.parse("2016-11-14", DateUtils.yyyy_MM_dd)));
    }

    @Test
    public void getDaysOfMonth() throws Exception {
        assertEquals(29, DateUtils.getDaysOfMonth(2016, 2));
        assertEquals(28, DateUtils.getDaysOfMonth(2015, 2));
        assertEquals(30, DateUtils.getDaysOfMonth(2016, 4));
        assertEquals(31, DateUtils.getDaysOfMonth(2016, 5));
    }

    @Test
    public void getFirstDayOfMonth() throws Exception {
        assertEquals(DateUtils.parse("2016-04-01", DateUtils.yyyy_MM_dd),
                DateUtils.getFirstDayOfMonth(DateUtils.parse("2016-04-22", DateUtils.yyyy_MM_dd)));
    }

    @Test
    public void getLastDayOfMonth() throws Exception {
        assertEquals(DateUtils.parse("2016-04-30", DateUtils.yyyy_MM_dd),
                DateUtils.getLastDayOfMonth(DateUtils.parse("2016-04-22", DateUtils.yyyy_MM_dd)));
    }

}