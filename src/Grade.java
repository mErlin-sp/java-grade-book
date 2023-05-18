public record Grade(short grade) {

    @Override
    public String toString() {
        return String.valueOf(grade);
    }
}
