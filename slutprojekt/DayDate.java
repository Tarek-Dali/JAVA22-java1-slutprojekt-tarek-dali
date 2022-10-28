package slutprojekt;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

class DayDate {
	static LocalDate date = LocalDate.now();
	static LocalDate whenIsMonday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
}
