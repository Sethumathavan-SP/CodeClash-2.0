package com.Sethu.CodeClash.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponse {
    private Status status;
    private List<FailedTest> failedTests;
    private String message;

    public enum Status {
        ACCEPTED, WRONG_ANSWER, RUNTIME_ERROR, COMPILATION_ERROR
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FailedTest {
        private String input;
        private String expectedOutput;
        private String actualOutput;

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getExpectedOutput() {
            return expectedOutput;
        }

        public void setExpectedOutput(String expectedOutput) {
            this.expectedOutput = expectedOutput;
        }

        public String getActualOutput() {
            return actualOutput;
        }

        public void setActualOutput(String actualOutput) {
            this.actualOutput = actualOutput;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<FailedTest> getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(List<FailedTest> failedTests) {
        this.failedTests = failedTests;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

