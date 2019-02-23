import java.io.BufferedReader;
import java.io.FileReader;
import java.net.SecureCacheResponse;
import java.util.*;
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
        long solution = records.stream()
                .count();
        System.out.println("1: " + solution);
    }

    // count, where
    public void executeSQL02() {
        long solution = records.stream()
                .filter(x -> x.getServiceDesk() == 8)
                .filter(x -> x.getTicket().getType().equals("w"))
                .count();
        System.out.println("2: " + solution);
    }

    // count, where, in
    public void executeSQL03() {
        long solution = records.stream()
                .filter(x -> x.getServiceDesk() == 4)
                .filter(x -> x.getShift() == 1)
                .filter(x -> x.getTicket().getType().equals("m"))
                .filter(x -> x.getDayOfWeek().equals("fri") || x.getDayOfWeek().equals("sat") || x.getDayOfWeek().equals("sun"))
                .count();
        System.out.println("3: " + solution);
    }

    // count, where, not in
    public void executeSQL04() {
        long solution = records.stream()
                .filter(x -> x.getServiceDesk() == 8)
                .filter(x -> x.getShift() == 2)
                .filter(x -> x.getTicket().getType().equals("r"))
                .filter(x -> !x.getDayOfWeek().equals("mon") || !x.getDayOfWeek().equals("fri") || !x.getDayOfWeek().equals("sat"))
                .count();
        System.out.println("4: " + solution);
    }

    // sum, where, in
    public void executeSQL05() {
        long solution = records.stream()
                .filter(x -> x.getServiceDesk() == 1 || x.getServiceDesk() == 2 || x.getServiceDesk() == 7 || x.getServiceDesk() == 8)
                .filter(x -> x.getShift() == 1)
                .filter(x -> x.getTicket().getType().equals("s") || x.getTicket().getType().equals("r"))
                .filter(x -> !x.getDayOfWeek().equals("sat") || !x.getDayOfWeek().equals("sun"))
                .mapToInt(Record::getWaitingTimeInMinutes).sum();
        System.out.println("5: " + solution);
    }

    // avg, where, not in
    public void executeSQL06() {
        double solution = records.stream()
                .filter(x -> x.getServiceDesk() == 1 || x.getServiceDesk() == 2 || x.getServiceDesk() == 3)
                .filter(x -> x.getShift() == 1 || x.getShift() == 4)
                .filter(x -> x.getTicket().getType().equals("m"))
                .filter(x -> !x.getDayOfWeek().equals("mon") || !x.getDayOfWeek().equals("fri"))
                .mapToInt(Record::getWaitingTimeInMinutes).average().getAsDouble();
        System.out.println("6: " + solution);
    }

    // id, where, in, order by desc limit
    public void executeSQL07() {
        List<Integer> solution = records.stream()
                .filter(x -> x.getShift() == 2)
                .filter(x -> x.getTicket().getType().equals("r"))
                .filter(x -> x.getDayOfWeek().equals("sat") || x.getDayOfWeek().equals("sun"))
                .filter(x -> x.getTicket().getDestination().equals("b"))
                .filter(x -> x.getWaitingTimeInMinutes() == 10)
                .sorted(Comparator.comparing(Record::getDayOfWeek).reversed())
                .limit(3)
                .map(Record::getServiceDesk)
                .collect(Collectors.toList());
        System.out.println("7: " + solution);
    }

    // id, where, in, order by desc, order by asc
    public void executeSQL08() {
        List<Integer> solution = records.stream()
                .filter(x -> x.getShift() == 1)
                .filter(x -> x.getTicket().getType().equals("m"))
                .filter(x -> x.getDayOfWeek().equals("mon"))
                .filter(x -> x.getTicket().getDestination().equals("a") || x.getTicket().getDestination().equals("f"))
                .filter(x -> x.getWaitingTimeInMinutes() == 10)
                .filter(x -> x.getPremiumService().equals("yes"))
                .sorted(Comparator.comparing(Record::getServiceDesk).thenComparing(x -> x.getTicket().getDestination()))
                .map(Record::getServiceDesk)
                .collect(Collectors.toList());
        System.out.println("8: " + solution);
    }

    // count, group by
    public void executeSQL09() {
        Map<String, Long> solution = records.stream()
                .collect(Collectors.groupingBy(Record::getDayOfWeek, Collectors.counting()));
        System.out.println("9: " + solution);
    }

    // count, group by
    public void executeSQL10() {
        Map<String, Long> solution = records.stream()
                .filter(x -> x.getTicket().getType().equals("m"))
                .filter(x -> x.getPremiumService().equals("yes"))
                .collect(Collectors.groupingBy(x -> x.getTicket().getDestination(), Collectors.counting()));
        System.out.println("10: " + solution);
    }

    // count, where, in, group by
    public void executeSQL11() {
        Map<String, Long> solution = records.stream()
                .filter(x -> x.getShift() == 3)
                .filter(x -> x.getTicket().getDestination().equals("c"))
                .filter(x -> x.getTicket().getType().equals("s") || x.getTicket().getType().equals("r"))
                .collect(Collectors.groupingBy(x -> x.getDayOfWeek(), Collectors.counting()));
        System.out.println("11: " + solution);
    }

    // count, where, not in, group by
    public void executeSQL12() {
        Map<String, Long> solution = records.stream()
                .filter(x -> x.getTicket().getDestination().equals("b"))
                .filter(x -> x.getShift() == 4)
                .filter(x -> !x.getDayOfWeek().equals("tue") && !x.getDayOfWeek().equals("wed"))
                .collect(Collectors.groupingBy(x -> x.getTicket().getType(), Collectors.counting()));
        System.out.println("12: " + solution);
    }

    // sum, where, not in, in, group by //TODO: falsches Mapping
    public void executeSQL13() {
        Map<Integer, Integer> solution = records.stream()
                .filter(x -> !x.getTicket().getType().equals("s") && !x.getTicket().getType().equals("r"))
                .filter(x -> x.getShift() == 1 || x.getShift() == 2)
                .filter(x -> x.getPremiumService().equals("yes"))
                .collect(Collectors.groupingBy(Record::getServiceDesk, Collectors.summingInt(Record::getId)));
        System.out.println("13: " + solution);
    }

    // avg, where, in, in, group by
    public void executeSQL14() {
        Map<Integer, Double> solution = records.stream()
                .filter(x -> x.getPremiumService().equals("no"))
                .filter(x -> x.getDayOfWeek().equals("fri") || x.getDayOfWeek().equals("mon"))
                .filter(x -> x.getShift() == 1 || x.getShift() == 4)
                .collect(Collectors.groupingBy(Record::getShift, Collectors.averagingInt(Record::getWaitingTimeInMinutes)));
        System.out.println("14: " + solution);
    }

    public void execute() {
        records = loadRecords();

        executeSQL01();
        executeSQL02();
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
        executeSQL14();
    }

    public static void main(String... args) {
        Application application = new Application();
        application.execute();
    }
}