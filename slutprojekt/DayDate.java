package slutprojekt;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

class DayDate {
	static LocalDate currentDate = LocalDate.now();
	static LocalDate whenIsMonday = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
}
