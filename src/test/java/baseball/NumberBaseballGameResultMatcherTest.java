package baseball;

import numberbaseballgame.NumberBaseballGameResultMatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NumberBaseballGameResultMatcherTest {
    private static NumberBaseballGameResultMatcher numberBaseballGameResultMatcher;
    private static ArrayList<Integer> answerNumberList;
    private static ArrayList<Integer> guessNumberList;

    @BeforeAll
    static void init(){
        numberBaseballGameResultMatcher = new NumberBaseballGameResultMatcher();
        answerNumberList = new ArrayList<>();
        guessNumberList = new ArrayList<>();
    }

    @Test
    @DisplayName("정답을 맞춘 경우")
    void correct(){
        String expectedSystemOut = "3 strikes\n세 개의 숫자를 모두 맞췄습니다. 게임 종료\n";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        answerNumberList.clear();;
        guessNumberList.clear();;

        answerNumberList.add(1);
        answerNumberList.add(2);
        answerNumberList.add(3);

        guessNumberList.add(1);
        guessNumberList.add(2);
        guessNumberList.add(3);

        assertTrue(numberBaseballGameResultMatcher.match(answerNumberList,guessNumberList));
        assertEquals(expectedSystemOut,out.toString());
    }

    @Test
    @DisplayName("모두 틀린 경우")
    void nothing(){
        String expectedSystemOut = "Nothing\n";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        answerNumberList.clear();;
        guessNumberList.clear();;

        answerNumberList.add(1);
        answerNumberList.add(2);
        answerNumberList.add(3);

        guessNumberList.add(4);
        guessNumberList.add(5);
        guessNumberList.add(6);

        assertFalse(numberBaseballGameResultMatcher.match(answerNumberList,guessNumberList));
        assertEquals(expectedSystemOut,out.toString());
    }

    @Test
    @DisplayName("스트라이크만 존재")
    void onlyStrikes(){
        String expectedSystemOut = "2 strikes\n";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        answerNumberList.clear();;
        guessNumberList.clear();;

        answerNumberList.add(1);
        answerNumberList.add(2);
        answerNumberList.add(3);

        guessNumberList.add(1);
        guessNumberList.add(2);
        guessNumberList.add(5);

        assertFalse(numberBaseballGameResultMatcher.match(answerNumberList,guessNumberList));
        assertEquals(expectedSystemOut,out.toString());
    }

    @Test
    @DisplayName("볼만 존재")
    void onlyBalls(){
        String expectedSystemOut = "2 balls\n";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        answerNumberList.clear();;
        guessNumberList.clear();;

        answerNumberList.add(1);
        answerNumberList.add(2);
        answerNumberList.add(3);

        guessNumberList.add(4);
        guessNumberList.add(1);
        guessNumberList.add(2);

        assertFalse(numberBaseballGameResultMatcher.match(answerNumberList,guessNumberList));
        assertEquals(expectedSystemOut,out.toString());
    }

    @Test
    @DisplayName("스트라이크, 볼 모두 있는 경우")
    void strikesAndBalls(){
        String expectedSystemOut = "1 strikes, 2 balls\n";
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        answerNumberList.clear();;
        guessNumberList.clear();;

        answerNumberList.add(1);
        answerNumberList.add(2);
        answerNumberList.add(3);

        guessNumberList.add(1);
        guessNumberList.add(3);
        guessNumberList.add(2);

        assertFalse(numberBaseballGameResultMatcher.match(answerNumberList,guessNumberList));
        assertEquals(expectedSystemOut,out.toString());
    }
}