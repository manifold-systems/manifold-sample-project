package abc.stuff;

public class SampleClass {
    private String _privateField;

    public SampleClass() {
        this("private data");
    }

    private SampleClass(String data) {
        _privateField = data;
    }

    private String privateMethod() {
        return _privateField;
    }
}
