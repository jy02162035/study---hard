package lambda;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestTime {
	public static void main(String[] args) throws Exception {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		ExecutorService pool = Executors.newFixedThreadPool(10);
		Callable<LocalDate> task = () -> {
			return LocalDate.parse("20191111", dateTimeFormatter);
		};
		List<Future<LocalDate>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<LocalDate> future : results) {
			System.out.println(future.get());
		}
		pool.shutdown();
	}
}
