package data.entity;

public enum ExecuteStatus {
    OK("+OK"),ERR("-ERR");

    private String value;

    ExecuteStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
