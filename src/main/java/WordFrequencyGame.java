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

                List<String> words = Arrays.asList(sentense.split(WORD_DELIMITER));
                List<WordInfo> wordInfos = new ArrayList<>();

                Set<String> uniqueWords = new HashSet<>(words);
                for (String uniqueWord:uniqueWords) {
                    int count = (int) words.stream().filter(word->{
                        return word.equals(uniqueWord);
                    }).count();
                    wordInfos.add(new WordInfo(uniqueWord,count));
                }

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_DELIMITER);
                for (WordInfo w : wordInfos) {
                    String s = w.getWord() + SPACE_DELIMITER + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }
}
