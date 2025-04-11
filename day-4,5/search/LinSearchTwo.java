
class LinearSearch {

    // Method to find the first sentence containing a specific word
    public String findSentenceWithWord(String[] sentences, String targetWord) {
        int n = sentences.length;

        // Iterate through the list of sentences
        for (int i = 0; i < n; i++) {
            // For each sentence, check if it contains the specific word
            if (sentences[i].contains(targetWord)) {
                // If the word is found, return the current sentence
                return sentences[i];
            }
        }

        // If no sentence contains the word, return "Not Found"
        return "Not Found";
    }
}

public class LinSearchTwo {
    public static void main(String[] args) {
        String[] sentences = {
                "The quick brown fox jumps over the lazy dog.",
                "A journey of a thousand miles begins with a single step.",
                "The only way to do great work is to love what you do.",
                "All that glitters is not gold.",
                "To be or not to be, that is the question."
        };
        String targetWord = "love";
        LinearSearch searcher = new LinearSearch();

        String result = searcher.findSentenceWithWord(sentences, targetWord);
        System.out.println("Sentence containing the word \"" + targetWord + "\": " + result);
    }
}

