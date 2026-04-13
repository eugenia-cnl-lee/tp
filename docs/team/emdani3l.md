# Emry Daniel - Project Portfolio Page

## Overview
**InternTrackr** is a centralized CLI-based internship management system designed for students navigating the peak recruitment season. It provides a streamlined interface to track application statuses, manage upcoming deadlines, and store recruiter contacts, ensuring that no opportunity is lost in a sea of emails.

## Summary of Contributions

### 1. New Features
* **Status Management (`status` command)**:
    * Acts as the primary state-management engine for the application. I implemented strict index bounds-checking and a status whitelist to ensure that data remains consistent for analytical features like `overview` and to prevent runtime crashes.
* **Advanced Filtering (`filter` command)**:
    * Developed a normalization layer that handles case-insensitive user inputs, providing a resilient "search-like" experience. The logic is deeply integrated with the archival system, ensuring that filtered results only display active applications unless a reset is triggered.
* **Search Functionality (`find` command)**:
    * Engineered a high-utility search that scans both **Company** and **Role** fields simultaneously using lowercase partial matching. I specifically designed the output to map results back to their original list indices, allowing users to perform follow-up actions like `status` or `archive` directly from the search view.
* **Safety Confirmation (`clear` command)**:
    * Designed a defensive, blocking workflow that interrupts the standard non-interactive command flow to prioritize data safety. This was a non-trivial implementation that requires the command to wait for and validate a specific interactive response before committing a permanent, non-recoverable wipe to the local storage.

### 2. Code Contributed
* [Link to RepoSense Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=emdani3l&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

### 3. Contributions to team-based tasks

* **During Tutorials**:
  * Encouraged group to attempt in class activities (UML diagram drawings)

* **Outside Tutorials**:
  * Alerted groupmates when they had outstanding task(s) due soon


### 4. Review/mentoring contributions

**Some PRs Reviewed**

*[PR 1](https://github.com/AY2526S2-CS2113-T14-1/tp/pull/104#pullrequestreview-4051515222)*
*[PR 2](https://github.com/AY2526S2-CS2113-T14-1/tp/pull/76#pullrequestreview-4011729253)*
*[PR 3](https://github.com/AY2526S2-CS2113-T14-1/tp/pull/44#pullrequestreview-3967573085)*
*[PR 4](https://github.com/AY2526S2-CS2113-T14-1/tp/pull/18#pullrequestreview-3934536109)*
---
### 5. Contributions to the User Guide

For the User Guide, I was responsible for authoring the documentation for the application's core search, filtering, and state-management commands (`status`, `filter`, `find`, and `clear`). My focus was on making the CLI intuitive for end-users:
* **Clear Formatting & Examples:** Structured the documentation to provide immediate value, including explicit command formats, parameter explanations, and practical examples for everyday use.
* **Edge Case & Constraint Documentation:** Clearly outlined system constraints, such as the strict whitelist of accepted inputs for the `status` command and how the `find` command handles case-insensitivity across both company and role fields.
* **Safety Instructions:** Wrote detailed, step-by-step instructions for the destructive `clear` command, ensuring users fully understand the two-step confirmation process required to prevent accidental data loss.

### 6. Contributions to the Developer Guide

I authored the technical documentation detailing the architecture, implementation logic, and design rationale behind my assigned features. My contributions were aimed at helping future developers understand the codebase and the engineering trade-offs made:
* **Implementation Logic:** Documented the complete lifecycle of the `status`, `filter`, `find`, and `clear` commands. I detailed the separation of concerns between the Parser (input validation and prefix extraction) and the Command (execution and model updates).
* **UML Diagrams:** Designed and integrated complex visual aids, including Sequence Diagrams illustrating the strict validation-then-update pipelines, and Object Diagrams showing how the system state isolates active versus archived applications during a `filter` operation.
* **Design Considerations:** Justified key architectural decisions. I explained the reasoning behind utilizing a normalization engine for the `status` command to ensure data integrity, the choice to exclude archived applications from the `filter` view for a cleaner user experience, and the necessity of a blocking UI prompt for the `clear` command to prioritize data safety over execution speed.

