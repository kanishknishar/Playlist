# Playlist Simulator

Console-based simulation of a music player application demonstrating LinkedList navigation and object-oriented design.

## Features

- **Playlist Management**: Add/remove songs, bidirectional navigation
- **Library System**: Create albums, add songs with duration tracking
- **Interactive Controls**: Play, pause, skip, rewind, remove current song
- **Sorting**: Order playlist by song length (ascending/descending)
- **Search**: Check song presence in playlist

## Core Classes

| Class | Purpose |
|-------|---------|
| `Main` | User interface and menu system |
| `Playlist` | LinkedList-based song queue with iterator controls |
| `Album` | Song collection container |
| `Song` | Individual track with title and duration |

## Key Implementation Details

- **ListIterator Navigation**: Bidirectional playlist traversal with direction tracking
- **Stream API**: Efficient song searching and filtering
- **Lambda Expressions**: Custom sorting comparators
- **Modern Java**: Implicit class declaration and streamlined main method (from JDK 24.)

## Usage

Run `Main.java` to access the interactive menu with 11 options:
- Playback controls (play, next, previous, rewind)
- Playlist operations (add, remove, sort, search)
- Library management (create albums, add songs)

## Technical Highlights

- Iterator pattern for playlist navigation
- Composition relationships between Album and Song
- Defensive programming with null checks
- Stream-based collection operations
