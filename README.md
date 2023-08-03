# Sample BurpSuite Extension

## Introduction

This repository contains the full code example that demonstrates how to build an extension using `Burp Suite`'s new `Montoya API`. For a step-by-step tutorial on creating this Burp extension, please visit the blog post: [How to Create a Burp Suite Extension Using the New Montoya API](https://marduc812.com/2023/08/02/create-a-burp-suite-extension-using-the-new-montoya-api/).

## What's Covered

The tutorial covers the following aspects of creating a custom extension:

- Preparing the environment
- Creating and loading a custom extension
- Extending the standard intercept functionality
- Creating a Burp Tab
- Using persistence to update the value

## Extension Functionality

The extension adds a custom delay between each proxied request. Users can adjust the delay time from a custom tab in Burp Suite.

