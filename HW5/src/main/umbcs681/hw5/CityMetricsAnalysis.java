package umbcs681.hw5;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CityMetricsAnalysis {
	
	public static double replaceNullByZeroAndGet(String s) {
	    try {

	        return Double.parseDouble(s.trim());
	    } catch (Exception e) {
	        return 0.0;
	    }
	}


    public static void main(String[] args) {
        Path path = Paths.get("cityscore_summary.csv");


        try (Stream<String> lines = Files.lines(path)) {
            List<List<String>> data = lines
                    .skip(1)
                    .map(line->Stream.of( line.split(","))
                    		.collect(Collectors.toList()))
                    .collect(Collectors.toList()); 
           
            System.out.println("Data processing 1 ");
          double avgDayScore = data.stream()
                    .map(row -> replaceNullByZeroAndGet(row.get(3)))
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);
            System.out.println("average previous_day_score = " + avgDayScore);
           
            System.out.println("Data processing 2");
            Map<Boolean, Double> avgDayScoreByGroup = data.stream()
                    .collect(Collectors.partitioningBy(
                            row->replaceNullByZeroAndGet(row.get(3))<1,
                            Collectors.averagingDouble(row->replaceNullByZeroAndGet(row.get(3)))
                    ));

            System.out.println("average previous_day_score partitioned by <1");
            System.out.println("less than 1 =" + avgDayScoreByGroup.get(true));
            System.out.println("greater or equal to 1 = " + avgDayScoreByGroup.get(false));


            System.out.println("Data processing 3");
            double totalMonthScore = data.stream()
                    .map(row->replaceNullByZeroAndGet(row.get(5)))
                    .reduce(0.0, (a,b)->a+b);
            System.out.println("total previous_month_score = " + totalMonthScore);
            
        } catch (IOException e){

            System.out.println(e);
         }
    }


}
