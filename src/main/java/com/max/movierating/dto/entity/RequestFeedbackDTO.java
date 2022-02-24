package com.max.movierating.dto.entity;

import com.max.movierating.entity.Feedback;
import com.max.movierating.entity.FeedbackType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestFeedbackDTO {

    @NotBlank(message = "Title may not be empty")
    private String title;

    @NotBlank(message = "Text may not be empty")
    private String text;

    @NotNull(message = "FeedbackType may not be empty")
    private FeedbackType feedbackType;

    public Feedback toFeedback() {
        return Feedback
                .builder()
                .title(title)
                .text(text)
                .feedbackType(feedbackType)
                .build();
    }
}
