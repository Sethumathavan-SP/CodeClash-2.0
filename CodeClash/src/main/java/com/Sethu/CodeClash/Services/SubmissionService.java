package com.Sethu.CodeClash.Services;

import com.Sethu.CodeClash.models.SubmissionInput;
import com.Sethu.CodeClash.models.SubmissionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {

    public SubmissionResponse evaluateSubmission(SubmissionInput input) {
        SubmissionResponse response = new SubmissionResponse();

        try {
            // TODO: Implement actual code execution and testing
            // This is a placeholder for the evaluation logic
            response.setStatus(SubmissionResponse.Status.ACCEPTED);
            response.setMessage("Code accepted!");
            response.setFailedTests(new ArrayList<>());
        } catch (Exception e) {
            response.setStatus(SubmissionResponse.Status.RUNTIME_ERROR);
            response.setMessage(e.getMessage());
            response.setFailedTests(new ArrayList<>());
        }

        return response;
    }
}

