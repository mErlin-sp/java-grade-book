public record Student(String name, String group) {
    @Override
    public String toString() {
        return name + " " + group;
    }
}
