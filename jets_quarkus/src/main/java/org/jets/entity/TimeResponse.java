package org.jets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

    @Entity
    public class TimeResponse {

        @Id
        @GeneratedValue
        private Long id;

        public int year;
        public int month;
        public int day;
        public int hour;
        public int minute;
        public int seconds;
        public int milliSeconds;

        public String dateTime;
        public String date;
        public String time;
        public String timeZone;
        public String dayOfWeek;

        public boolean dstActive;


        public TimeResponse() {
        }

        public TimeResponse(Long id, int year, int month, int day, int hour, int minute, int seconds, int milliSeconds, String dateTime, String date, String time, String timeZone, String dayOfWeek, boolean dstActive) {
            this.id = id;
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.seconds = seconds;
            this.milliSeconds = milliSeconds;
            this.dateTime = dateTime;
            this.date = date;
            this.time = time;
            this.timeZone = timeZone;
            this.dayOfWeek = dayOfWeek;
            this.dstActive = dstActive;
        }


        public Long getId() {
            return id;
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSeconds() {
            return seconds;
        }

        public int getMilliSeconds() {
            return milliSeconds;
        }

        public String getDateTime() {
            return dateTime;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public boolean isDstActive() {
            return dstActive;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public void setMilliSeconds(int milliSeconds) {
            this.milliSeconds = milliSeconds;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }

        public void setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public void setDstActive(boolean dstActive) {
            this.dstActive = dstActive;
        }


        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            TimeResponse that = (TimeResponse) o;
            return year == that.year && month == that.month && day == that.day && hour == that.hour && minute == that.minute && seconds == that.seconds && milliSeconds == that.milliSeconds && dstActive == that.dstActive && Objects.equals(id, that.id) && Objects.equals(dateTime, that.dateTime) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(timeZone, that.timeZone) && Objects.equals(dayOfWeek, that.dayOfWeek);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, year, month, day, hour, minute, seconds, milliSeconds, dateTime, date, time, timeZone, dayOfWeek, dstActive);
        }


        @Override
        public String toString() {
            return "TimeResponse{" +
                    "id=" + id +
                    ", year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    ", hour=" + hour +
                    ", minute=" + minute +
                    ", seconds=" + seconds +
                    ", milliSeconds=" + milliSeconds +
                    ", dateTime='" + dateTime + '\'' +
                    ", date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    ", timeZone='" + timeZone + '\'' +
                    ", dayOfWeek='" + dayOfWeek + '\'' +
                    ", dstActive=" + dstActive +
                    '}';
        }
    }



