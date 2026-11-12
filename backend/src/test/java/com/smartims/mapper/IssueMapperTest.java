
package com.smartims.mapper;

import com.smartims.dto.IssueResponse;
import com.smartims.entity.Issue;
import com.smartims.entity.Project;
import com.smartims.enums.IssueStatus;
import com.smartims.enums.Severity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssueMapperTest {

    @Test
    void shouldDisplayCreatedForUntriagedIssue() {

        Project project = Project.builder()
                .id(1L)
                .name("SmartIMS Project")
                .build();

        Issue issue = Issue.builder()
                .id(101L)
                .title("Login failure")
                .description("User cannot log in")
                .status(IssueStatus.OPEN)
                .priorityLevel("-")
                .triaged(false)
                .project(project)
                .build();

        IssueResponse response = IssueMapper.toResponse(issue);

        assertEquals("CREATED", response.getStatus());
        assertEquals("SmartIMS Project", response.getProjectName());
    }

    @Test
    void shouldDisplayOpenForTriagedIssue() {

        Project project = Project.builder()
                .id(1L)
                .name("SmartIMS Project")
                .build();

        Issue issue = Issue.builder()
                .id(102L)
                .title("Server issue")
                .description("Server response is slow")
                .severity(Severity.HIGH)
                .status(IssueStatus.OPEN)
                .priorityLevel("HIGH")
                .triaged(true)
                .project(project)
                .build();

        IssueResponse response = IssueMapper.toResponse(issue);

        assertEquals("OPEN", response.getStatus());
        assertEquals("HIGH", response.getSeverity());
    }
}
