package ua.javarush.service.question;

import lombok.Getter;
import ua.javarush.model.Answer;
import ua.javarush.model.Question;

@Getter
public class CheckRightAnswerService {

    private final QuestService questService;
    private Question question;
    private Answer answer;
    private final boolean isRight;
    private String page;

    private CheckRightAnswerService(Builder builder) {
        this.questService = builder.questService;
        this.question = builder.question;
        this.answer = builder.answer;
        this.page = builder.page;
        this.isRight = builder.isRight;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CheckRightAnswerService analyzeAnswer() {
        Answer answer = questService.getAnswer(question, isRight);
        Integer nextQuestionNumber = answer.getNextQuestionNumber();
        this.answer = answer;

        if (!isRight) {
            this.page = "/losePage.jsp";

        } else if (isRight) {
            if (nextQuestionNumber != null) {
                Question nextQuestion = this.questService.continueQuest(question).get();
                this.question = nextQuestion;
                this.page = "/questUfo.jsp";

            } else if (nextQuestionNumber == null) {
                this.page = "/winPage.jsp";
            }
        }
        return this;
    }

    public static class Builder {

        private QuestService questService;
        private Question question;
        private Answer answer;

        private String page;

        private boolean isRight;

        public Builder withIsRight(boolean right) {
            isRight = right;
            return this;
        }

        private Builder() {
        }

        public Builder withQuestService(QuestService questService) {
            this.questService = questService;
            return this;
        }

        public Builder withQuestion(Question question) {
            this.question = question;
            return this;
        }

        public Builder withAnswer(Answer answer) {
            this.answer = answer;
            return this;
        }

        public Builder withPage(String page) {
            this.page = page;
            return this;
        }

        public CheckRightAnswerService build() {
            return new CheckRightAnswerService(this);
        }
    }
}
