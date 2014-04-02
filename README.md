60-480 - Noise analyzer
============================
Server waits for requests from clients to get the audio levels in a fixed location
using a microphone. We return a json with information such as amplitude, decibels,
etc.

Public API function calls:

  - public String getNoise (int seconds)
    - Analyzes audio (in real time) for that many seconds.

  - public String getNoiseInterval (int seconds, int numIntervals)
    - Analyzes audio (in real time) for that many seconds, for that
  many intervals.

How to Run it
===============
Run the following on your command line:
  - git clone https://github.com/velazqua/480-pi-project.git

Compile all java files: (works in Unix)
  - javac -cp ".:./java-json.jar" *.java

Run sample client program:
  - ./start_client.sh 137.207.74.151 (Unix)
  - ./start_client.bat 137.207.74.151 (Windows)
