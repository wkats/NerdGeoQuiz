package geoquiz.nerd.wkats.com.nerdgeoquiz;

/**
 * Created by TI on 25/11/2014.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;


    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int _question) {
        mQuestion = _question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean _trueQuestion) {
        mTrueQuestion = _trueQuestion;
    }

    public TrueFalse(int _question, boolean _trueQuestion) {
        mQuestion = _question;
        mTrueQuestion = _trueQuestion;
    }

}
