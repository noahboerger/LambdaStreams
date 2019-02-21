public class Record {
    private int id;
    private String hostname;
    private int downtimeInMinutes;
    private String severity;
    private String attackType;
    private int source;
    private int shift;

    public Record(int id, String hostname, int downtimeInMinutes, String severity, String attackType, int source, int shift) {
        this.id = id;
        this.hostname = hostname;
        this.downtimeInMinutes = downtimeInMinutes;
        this.severity = severity;
        this.attackType = attackType;
        this.source = source;
        this.shift = shift;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";").append(hostname).append(";").append(downtimeInMinutes).append(";");
        stringBuilder.append(severity).append(";").append(attackType).append(";").append(source).append(";");
        stringBuilder.append(shift);
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public String getHostname() {
        return hostname;
    }

    public int getDowntimeInMinutes() {
        return downtimeInMinutes;
    }

    public String getSeverity() {
        return severity;
    }

    public String getAttackType() {
        return attackType;
    }

    public int getSource() {
        return source;
    }

    public int getShift() {
        return shift;
    }
}