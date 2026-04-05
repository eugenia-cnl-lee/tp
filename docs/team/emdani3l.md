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
* [Link to RepoSense Dashboard] *(https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=emdani3l&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)*

### 3. Contributions to the User Guide (Extracts)

> #### **Updating application status: `status`**
> Updates the status of an existing application at the given index.
> **Format:** `status INDEX s/STATUS`
> * **Example:** `status 1 s/"Interview"`
>
> #### **Filtering by status: `filter`**
> Shows only applications matching a specific recruitment stage.
> **Format:** `filter s/STATUS`
> * **Example:** `filter s/"Pending"`
>
> #### **Finding applications: `find`**
> Finds applications whose company name or role contains a specified keyword.
> **Format:** `find KEYWORD`
> * **Example:** `find Shopee`
>
> #### **Clearing all data: `clear`**
> Wipes all applications from the list. Requires an explicit `yes` confirmation.
> **Format:** `clear`

### 4. Contributions to the Developer Guide (Extracts)

> #### **Status and Filter Implementation**
> These features utilize a **normalization engine** to provide a resilient user experience. When a user inputs a status, the system invokes `Application#getNormalizedStatus()` to convert it to a canonical format. This ensures consistent matching for the `filter` command and accurate data aggregation for the `overview` feature. The filtering logic also performs a pre-search archival check via `Application#isArchived()` to maintain view consistency.
>
> #### **Find Feature Implementation**
> The `find` command performs a partial-match search across both **Company** and **Role** fields. The logic iterates through the `ApplicationList` and uses lowercase comparison to maintain search flexibility. A key technical challenge was preserving the **original 1-based indices** in the filtered output, which I solved by tracking and displaying the application's actual position in the active list alongside the search result.
>
> #### **Clear Feature Implementation**
> To prevent catastrophic data loss, the `clear` command implements a **two-step confirmation workflow**. The `ClearCommand#execute()` method triggers a blocking UI prompt via `Ui#readCommand()` that waits for a specific "yes" input. This design choice prioritizes data safety over execution speed, ensuring that a simple typo cannot accidentally delete a user's entire application history.


### 5. Contributions to team-based tasks

* **During Tutorials**:
  * Encouraged group to attempt in class activities (UML diagram drawings)

* **Outside Tutorials**:
  * Alerted groupmates when they had outstanding task(s) due soon


### 6. Review/mentoring contributions

**Some PRs Reviewed**

*(https://github.com/AY2526S2-CS2113-T14-1/tp/pull/104#pullrequestreview-4051515222)*
*(https://github.com/AY2526S2-CS2113-T14-1/tp/pull/76#pullrequestreview-4011729253)*
*(https://github.com/AY2526S2-CS2113-T14-1/tp/pull/44#pullrequestreview-3967573085)*
*(https://github.com/AY2526S2-CS2113-T14-1/tp/pull/18#pullrequestreview-3934536109*)
---
