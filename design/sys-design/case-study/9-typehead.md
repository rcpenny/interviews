# Designing Typeahead Suggestion
Let's design a real-time suggestion service, which will recommend terms to users as they enter text for searching.

Similar Services: Auto-suggestions, Typeahead search
Difficulty: Medium

## 1. What is Typeahead Suggestion?
Typeahead suggestions enable users to search for known and frequently searched terms. As the user types into the search box, it tries to predict the query based on the characters the user has entered and gives a list of suggestions to complete the query. Typeahead suggestions help the user to articulate their search queries better. It’s not about speeding up the search process but rather about guiding the users and lending them a helping hand in constructing their search query.

## 2. Requirements and Goals of the System
Functional Requirements: As the user types in their query, our service should suggest top 10 terms starting with whatever the user has typed.

Non-function Requirements: The suggestions should appear in real-time. The user should be able to see the suggestions within 200ms.
