# Workout List App - Coding Assignment

## Overview

Welcome! This assignment is designed to evaluate your Android development skills, including your ability to work with JSON data, build clean UI, implement user interactions, and write testable, maintainable code.

You'll build an Android app that reads a local JSON file and displays a list of workouts. Users should be able to edit workout details.
You'll have one week to complete this assignment. While we understand everyone works at their own pace, we recommend spending no more than 4–6 hours on it. We’re not looking for a fully polished or production-ready app.  So, focus on clean, understandable code and demonstrating your thought process.

---

## ✨ Requirements

### Core Functionality

1. **Load & Display Workouts**
   - Load the provided JSON file (`workouts.json`).
   - Display the list of workouts in a scrollable list.

2. **Edit Workouts**
   - Users should be able to tap on a workout to view and edit its details in a separate screen.
   - Changes should be retained in-memory during the session.
---

## 🛠 Technical Expectations

- Use **Kotlin** or **Java** as the base language.
- Use **Jetpack Compose** or **XML** for UI.
- Add **unit tests** and/or **UI tests** to validate core functionality.

---

## 🎯 Bonus Points

- Persist workout changes.
- Deliver a polished and responsive UI.
- Scalable code structure.

---

## 🧪 Testing

Please include tests for:
- JSON parsing and data modeling
- Core logic (e.g., editing and updating workout details)
- UI interaction (if applicable)

---

## 📝 Developer Notes

Please fill out the section below before submitting your solution.

### What did you focus on and why?

For this assessment I really wanted to focus on laying the groundwork for a fully functioning, multi-module, clean architecture.
Its been quite some time since I worked on brand new app so I wanted to experiment with a few things to both learn and have fun with new 
features of Android and compose. One of the core focuses I had for this was to be efficient but also follow best practices. There are definitely
some shortcuts I chose not to take that might have allowed me more time for other things, (like UI polish), but I take pride in doing the small 
things right, even if it takes longer.

### What would you improve with more time?

The User Interface! I'm definitely a programmer when it comes to UI and I tend to lean towards the bare bones proof of concept for new projects. 
If I had more time I would have loved to add some UI polish including animated navigations transitions, better colors, better usage of screen 
real estate and better iconography. 

I also would have taken a more persistant approach with data. This type of app lends itself well to using Room persistence and would have been
nice to store and load the workouts into the Room database. This would have allowed for persistence across app closes as well as allowed for 
more robust workout editing.

### How did you approach testing?

I attempted to use pure JVM testing, but unfortunately due to my usage of Hilt, I couldn't quite get that to work right. I was able to create basic
unit tests around the parsing of the supplied json file. There isn't a whole lot to test due to Kotlinx Serialization's zero code parsers, (basically
just annotated data classes), but I wanted to have something for that. The ViewModel testing was a different story however, and took way more time than
I was anticipating. I did get it working in the end and learned a few things about Hilt mocking along the way. In general my testing strategy is more 
robust, and I think given more time I would have focused on that aspect more.

---

## 📁 Provided Files

- `workouts.json`
- `README.md`

---

## 🚀 Submission Instructions

1. Complete the assignment and update this `README.md` with your responses above.
2. Upload your project to a private GitHub repository or share it via a zip file.
3. Make sure your project builds and runs cleanly.

Have fun building! 💪
