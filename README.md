## About the Project
This project was developed as part of my CIS coursework at Holland College PEI in 2024.

# Intermediate Java Squash Skills Assessment App

## Project Overview
The Squash Skills Assessment App is a tool designed to facilitate the evaluation and tracking of squash players' skills. It allows assessors to input player skill metrics, calculate scores based on predefined criteria, and provide meaningful feedback regarding their performance. The application aims to support both amateur and professional squash training environments.

This project was initially developed by **BJ MacLean**, with additional modifications by **Sherri Ashton**. Credit goes to BJ MacLean for providing the foundational code and structure.

---

## Features
- **Dynamic Skill Scoring**: Calculate scores dynamically based on various squash skill metrics.
- **Customizable Skill Types**: Supports a flexible structure for adding or updating skill types.
- **Assessment Management**: Add and view assessments with detailed information.
- **Intuitive Menu**: Simple menu-based navigation for user-friendly interaction.

---

## How It Works
1. **Add Assessments**: Users can input assessment details, including athlete and assessor names, status (amateur or professional), and scores for various skill types.
2. **View Assessments**: Retrieve and display all recorded assessments with calculated scores and performance descriptions.
3. **Skill Type Overview**: Display the list of predefined skill types with their descriptions, valid ranges, and scoring factors.

---

## Key Classes
### 1. `Assessment`
Handles the core logic for:
- Gathering assessment details.
- Calculating scores dynamically using skill factors.
- Generating assessment codes based on input attributes.

### 2. `SkillType`
Defines the properties of individual skill types, including:
- Description
- Minimum and maximum valid values
- Scoring factor

### 3. `Controller`
Manages the main application flow, including:
- Adding new assessments.
- Displaying existing assessments.
- Interfacing with the `SkillTypeUtil` for skill type management.

---

## Unit Testing
The project includes unit tests (`AssessmentJUnitTest`) to ensure the correctness of key functionalities like:
- Score calculation
- Assessment initialization

---

## Credits
- **BJ MacLean**: Provided the initial codebase and foundational structure.
- **Sherri Ashton**: Modified the project to improve functionality, remove unused constants, and dynamically adjust score calculations and outputs.

---

## Future Improvements
- Add a persistent database to store and manage assessments.
- Enhance the user interface for better navigation and usability.
- Introduce graphical data visualization to track skill improvements over time.

