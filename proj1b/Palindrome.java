public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> dq = wordToDeque(word);
        if (dq.size() <= 1 || dq.isEmpty()) {
            return true;
        }
        while (dq.size() > 1){
            if (dq.removeFirst() != dq.removeLast()){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindromeOne(String word, CharacterComparator cc) {
        if (word == null || word.length() == 1) {
            return true;
        }
        int len = word.length();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
