public class Record {
    private int id;
    private int waitingTimeInMinutes;
    private int serviceDesk;
    private int shift;
    private String dayOfWeek;
    private Ticket ticket;
    private String premiumService;

    public Record(int id, int waitingTimeInMinutes, int serviceDesk, int shift, String dayOfWeek, Ticket ticket, String premiumService) {
        this.id = id;
        this.waitingTimeInMinutes = waitingTimeInMinutes;
        this.serviceDesk = serviceDesk;
        this.shift = shift;
        this.dayOfWeek = dayOfWeek;
        this.ticket = ticket;
        this.premiumService = premiumService;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";").append(waitingTimeInMinutes).append(";").append(serviceDesk).append(";").append(shift).append(";");
        stringBuilder.append(dayOfWeek).append(";").append(ticket.getDestination()).append(";");
        stringBuilder.append(ticket.getType()).append(";").append(ticket.getPrice()).append(";").append(premiumService);
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public int getWaitingTimeInMinutes() {
        return waitingTimeInMinutes;
    }

    public int getServiceDesk() {
        return serviceDesk;
    }

    public int getShift() {
        return shift;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getPremiumService() {
        return premiumService;
    }
}