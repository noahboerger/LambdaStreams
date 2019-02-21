import java.io.BufferedReader;
import java.io.FileReader;
import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Application {
    private List<Record> records = new ArrayList<>();

    public List<Record> loadRecords() {
        List<Record> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data\\records.csv"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] entries = line.split(";");
                int id = Integer.parseInt(entries[0]);
                int waitingTimeinMinutes = Integer.parseInt(entries[1]);
                int serviceDesk = Integer.parseInt(entries[2]);
                int shift = Integer.parseInt(entries[3]);
                String dayOfWeek = entries[4];
                String destination = entries[5];
                String type = entries[6];
                int price = Integer.parseInt(entries[7]);
                Ticket ticket = new Ticket(destination, type, price);
                String premiumService = entries[8];
                records.add(new Record(id, waitingTimeinMinutes, serviceDesk, shift, dayOfWeek, ticket, premiumService));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return records;
    }

    // count
    public void executeSQL01() {
        long count = records.stream().count();
        System.out.println("1: " + count);
    }

    /*
    // count
    public void executeSQL01() {
        writeLogfile("--- query 01 (count)");
        String sqlStatement = "SELECT COUNT(*) FROM data";
        queryDump(sqlStatement);
    }

    // count, where
    public void executeSQL02() {
        writeLogfile("--- query 02 (count, where)");
        String sqlStatement = "SELECT COUNT(*) FROM data " +
                "WHERE serviceDesk = 8 AND type = 'w'";
        queryDump(sqlStatement);
    }

    // count, where, in
    public void executeSQL03() {
        writeLogfile("--- query 03 (count, where, in)");
        String sqlStatement = "SELECT COUNT(*) FROM data " +
                "WHERE serviceDesk = 4 AND shift = 1 AND type = 'm' " +
                "AND dayOfWeek IN ('fri','sat','sun')";
        queryDump(sqlStatement);
    }

    // count, where, not in
    public void executeSQL04() {
        writeLogfile("--- query 04 (count, where, not in)");
        String sqlStatement = "SELECT COUNT(*) FROM data " +
                "WHERE serviceDesk = 8 AND shift = 2 AND type = 'r' " +
                "AND dayOfWeek NOT IN ('mon','fri','sat')";
        queryDump(sqlStatement);
    }

    // sum, where, in
    public void executeSQL05() {
        writeLogfile("--- query 05 (sum, where, in)");
        String sqlStatement = "SELECT SUM(waitingTimeInMinutes) FROM data " +
                "WHERE serviceDesk IN (1,2,7,8) " +
                "AND shift = 1 AND type IN ('s','r') " +
                "AND dayOfWeek IN ('sat','sun')";
        queryDump(sqlStatement);
    }

    // avg, where, not in
    public void executeSQL06() {
        writeLogfile("--- query 06 (avg, where, not in)");
        String sqlStatement = "SELECT AVG(waitingTimeInMinutes) FROM data " +
                "WHERE serviceDesk IN (1,2,3) " +
                "AND shift IN (1,4) AND type = 'm' " +
                "AND dayOfWeek NOT IN ('mon','fri')";
        queryDump(sqlStatement);
    }

    // id, where, in, order by desc limit
    public void executeSQL07() {
        writeLogfile("--- query 07 (id, where, in, order by desc limit)");
        String sqlStatement = "SELECT serviceDesk FROM data " +
                "WHERE shift = 2 AND type = 'r' " +
                "AND dayOfWeek IN ('sat','sun') AND destination = 'b' " +
                "AND waitingTimeInMinutes = 10 " +
                "ORDER BY dayOfWeek DESC LIMIT 3";
        queryDump(sqlStatement);
    }

    // id, where, in, order by desc, order by asc
    public void executeSQL08() {
        writeLogfile("--- query 08 (id, where, in, order by desc, order by asc)");
        String sqlStatement = "SELECT serviceDesk FROM data " +
                "WHERE shift = 1 AND type = 'm' " +
                "AND dayOfWeek = 'mon' AND destination IN ('a','f') " +
                "AND waitingTimeInMinutes = 10 " +
                "AND premiumService = 'yes' " +
                "ORDER BY serviceDesk DESC, destination";
        queryDump(sqlStatement);
    }

    // count, group by
    public void executeSQL09() {
        writeLogfile("--- query 09 (count, group by)");
        String sqlStatement = "SELECT dayOfWeek,COUNT(*) FROM data " +
                "GROUP BY dayOfWeek";
        queryDump(sqlStatement);
    }

    // count, where, group by
    public void executeSQL10() {
        writeLogfile("--- query 10 (count, where, group by)");
        String sqlStatement = "SELECT destination,COUNT(*) FROM data " +
                "WHERE type = 'm' " +
                "AND premiumService = 'yes' " +
                "GROUP BY destination";
        queryDump(sqlStatement);
    }

    // count, where, in, group by
    public void executeSQL11() {
        writeLogfile("--- query 11 (count, where, in, group by)");
        String sqlStatement = "SELECT dayOfWeek,COUNT(*) FROM data " +
                "WHERE shift = 3 AND destination = 'c' " +
                "AND type IN ('s','r') " +
                "GROUP BY dayOfWeek";
        queryDump(sqlStatement);
    }

    // count, where, not in, group by
    public void executeSQL12() {
        writeLogfile("--- query 12 (count, where, not in, group by)");
        String sqlStatement = "SELECT type,COUNT(*) FROM data " +
                "WHERE destination = 'b' AND shift = '4' " +
                "AND dayOfWeek NOT IN ('tue','wed') " +
                "GROUP BY type";
        queryDump(sqlStatement);
    }

    // sum, where, not in, in, group by
    public void executeSQL13() {
        writeLogfile("--- query 13 (sum, where, not in, in, group by)");
        String sqlStatement = "SELECT serviceDesk,SUM(price) FROM data " +
                "WHERE type NOT IN ('s','r') " +
                "AND shift IN (1,2) " +
                "AND premiumService = 'yes' " +
                "GROUP BY serviceDesk";
        queryDump(sqlStatement);
    }

    // avg, where, in, in, group by
    public void executeSQL14() {
        writeLogfile("--- query 14 (avg, where, in, in, group by)");
        String sqlStatement = "SELECT shift,AVG(waitingTimeInMinutes) FROM data " +
                "WHERE premiumService = 'no' " +
                "AND dayOfWeek IN ('fri','mon') AND shift IN (1,4) " +
                "GROUP BY shift";
        queryDump(sqlStatement);
    }
     */

//    // count, where
//    public void executeSQL02() {
//        long count = records.stream()
//                .filter(x -> x.getSeverity().equals("major"))
//                .filter(x -> x.getAttackType().equals("e"))
//                .filter(x -> x.getSource() <= 2)
//                .filter(x -> x.getShift() == 4)
//                .count();
//        System.out.println("2: " + count);
//    }
//
//    // count, where, in
//    public void executeSQL03() {
//        long count = records.stream()
//                .filter(x -> x.getSeverity().equals("major") || x.getSeverity().equals("critical"))
//                .filter(x -> x.getAttackType().equals("b"))
//                .filter(x -> x.getSource() == 4)
//                .filter(x -> x.getShift() >= 3)
//                .count();
//        System.out.println("3: " + count);
//    }
//
//    // count, where, not in
//    public void executeSQL04() {
//        long count = records.stream()
//                .filter(x -> x.getSeverity().equals("critical"))
//                .filter(x -> !x.getAttackType().equals("b") && !x.getAttackType().equals("c") && !x.getAttackType().equals("g"))
//                .filter(x -> x.getSource() > 2)
//                .filter(x -> x.getShift() <= 2)
//                .count();
//        System.out.println("4: " + count);
//    }
//
//    // id, where, in, order by desc limit
//    public void executeSQL05() {
//        long sum = records.stream()
//                .filter(x -> x.getSeverity().equals("major") || x.getSeverity().equals("critical"))
//                .filter(x -> x.getAttackType().equals("b") || x.getAttackType().equals("c"))
//                .filter(x -> x.getSource() > 2)
//                .filter(x -> x.getShift() <= 2)
//                .map(Record::getDowntimeInMinutes)
//                .reduce(0, (x, y) -> x + y);
//        System.out.println("5: " + sum);
//    }
//
//    // id, where, in, order by desc, order by asc
//    public void executeSQL06() {
//        int avg = (int) records.stream()
//                .filter(x -> x.getSeverity().equals("minor") || x.getSeverity().equals("major"))
//                .filter(x -> !x.getAttackType().equals("c") && !x.getAttackType().equals("d") && !x.getAttackType().equals("e"))
//                .filter(x -> x.getSource() == 2)
//                .filter(x -> x.getShift() == 1)
//                .mapToInt(Record::getDowntimeInMinutes)
//                .summaryStatistics()
//                .getAverage();
//        System.out.println("6: " + avg);
//    }
//
//    // count, group by
//    /*
//    Diese Funktion sollte stimmen, allerdings sind die ersten drei Zahlen wahrscheinlich
//    aufgrund von unterschiedlichen Sortieralgorithmen unterschiedlich.
//    Das liegt daran, dass sehr viele Zahlen 240 downtime haben und keine 2. Sortierung vorliegt
//    Bei Filterung der Daten in Excel komme ich auf dasselbe Ergebnis wie in der folgenden Funktion
//     */
//    public void executeSQL07() {
//        List<Integer> idList = records.stream()
//                .filter(x -> x.getSeverity().equals("minor"))
//                .filter(x -> x.getAttackType().equals("a") || x.getAttackType().equals("b"))
//                .filter(x -> x.getSource() == 1)
//                .filter(x -> x.getShift() == 4)
//                .filter(x -> x.getDowntimeInMinutes() >= 195)
//                .sorted(Comparator.comparing(Record::getDowntimeInMinutes).reversed())
//                .limit(3)
//                .map(Record::getId)
//                .collect(Collectors.toList());
//        System.out.println("\n7: " + idList);
//    }
//
//    // count, where, group by
//    public void executeSQL08() {
//        List<Integer> idList = records.stream()
//                .filter(x -> x.getSeverity().equals("minor") || x.getSeverity().equals("major"))
//                .filter(x -> x.getAttackType().equals("c"))
//                .filter(x -> x.getSource() == 2)
//                .filter(x -> x.getShift() == 1)
//                .filter(x -> x.getId() <= 500)
//                .sorted(Comparator.comparing(Record::getSeverity).reversed()
//                        .thenComparing(Record::getDowntimeInMinutes))
//                .map(Record::getId)
//                .collect(Collectors.toList());
//        System.out.println("8: " + idList);
//    }
//
//    // count, where, in, group by
//    public void executeSQL09() {
//        Map<String, Long> result = records.stream()
//                .collect(Collectors.groupingBy(Record::getSeverity, Collectors.counting()));
//        System.out.println("9: " + result);
//    }
//
//    // count, where, not in, group by
//    public void executeSQL10() {
//        Map<Integer, Long> result = records.stream()
//                .filter(x -> x.getAttackType().equals("d"))
//                .filter(x -> x.getSeverity().equals("major"))
//                .collect(Collectors.groupingBy(Record::getShift, Collectors.counting()));
//        System.out.println("10: " + result);
//    }
//
//    // sum, where, not in, in, group by
//    public void executeSQL11() {
//        Map<String, Long> result = records.stream()
//                .filter(x -> x.getAttackType().equals("a") || x.getAttackType().equals("b") || x.getAttackType().equals("c"))
//                .filter(x -> x.getSource() == 3)
//                .collect(Collectors.groupingBy(Record::getAttackType, Collectors.counting()));
//        System.out.println("11: " + result);
//    }
//
//    // avg, where, in, in, group by
//    public void executeSQL12() {
//        Map<Integer, Long> result = records.stream()
//                .filter(x -> !x.getAttackType().equals("b") && !x.getAttackType().equals("d") && !x.getAttackType().equals("e"))
//                .filter(x -> x.getShift() >= 2)
//                .filter(x -> x.getDowntimeInMinutes() >= 30 && x.getDowntimeInMinutes() <= 90)
//                .collect(Collectors.groupingBy(Record::getSource, Collectors.counting()));
//        System.out.println("12: " + result);
//    }
//
//    public void executeSQL13() {
//        Map<String, Integer> result = records.stream()
//                .filter(x -> !x.getAttackType().equals("b") && !x.getAttackType().equals("d") && !x.getAttackType().equals("e"))
//                .filter(x -> x.getShift() == 1)
//                .filter(x -> x.getSource() == 1 || x.getSource() == 3)
//                .collect(Collectors.groupingBy(Record::getAttackType, Collectors.summingInt(Record::getDowntimeInMinutes)));
//        System.out.println("13: " + result);
//    }
//
//    public void executeSQL14() {
//        Map<String, Integer> result = records.stream()
//                .filter(x -> x.getSeverity().equals("minor") || x.getSeverity().equals("major"))
//                .filter(x -> x.getAttackType().equals("a") || x.getAttackType().equals("b") || x.getAttackType().equals("c"))
//                .filter(x -> x.getSource() == 1)
//                .filter(x -> x.getShift() >= 3)
//                .collect(Collectors.groupingBy(Record::getAttackType, Collectors.collectingAndThen(Collectors.averagingInt(Record::getDowntimeInMinutes), x -> x.intValue())));
//
//        System.out.println("14: " + result);
//    }

    public void execute() {
        records = loadRecords();
        executeSQL01();
       /* executeSQL02();
        executeSQL03();
        executeSQL04();
        executeSQL05();
        executeSQL06();
        executeSQL07();
        executeSQL08();
        executeSQL09();
        executeSQL10();
        executeSQL11();
        executeSQL12();
        executeSQL13();
        executeSQL14();*/
    }

    public static void main(String... args) {
        Application app = new Application();
        app.execute();
    }
}