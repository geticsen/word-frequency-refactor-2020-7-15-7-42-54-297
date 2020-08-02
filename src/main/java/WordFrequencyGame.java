import java.util.*;

public class WordFrequencyGame {

    public static final String WORD_DELIMITER = "\\s+";
    public static final String LINE_DELIMITER = "\n";
    public static final String SPACE_DELIMITER = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentense) {

        if (sentense.split(WORD_DELIMITER).length == 1) {
            return sentense + " 1";
        } else {

            try {

                String[] words = sentense.split(WORD_DELIMITER);

                List<Input> inputList = new ArrayList<>();
                for (String s : words) {
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }

                Map<String, List<Input>> map = getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_DELIMITER);
                for (Input w : inputList) {
                    String s = w.getValue() + SPACE_DELIMITER + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {

            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
