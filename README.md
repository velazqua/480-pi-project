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

Both functions return a valid JSON string with the following attributes:
minAmplitude, maxAmplitude, meanAmplitude, rmsAmplitude, db, rFrequency, meanNorm, maxDelta, minDelta, meanDelta, rmsDelta, and midlineAmplitude.

Interpretation of attributes is left to the client.

Description of files:
======================
MultipleClients.java: contains implementation using both microphone and robot APIs.

PiInfo.java: Our public API interface

PiInfoServer.java: responsible for creating and running server process.

PiInfoService.java: contains implementations of our APIs.

PiSampleClient.java: small sample client program using our API.

Policy: RMI policy.

RaspiRobotInterface.java: robots public API.

SoundReporter.java: class responsible for recording audio and parsing it.

java-json.jar	json: Java JSON library. Optional.

start_client.bat: sample program script used to run client on Windows.

start_client.sh: sample program script used to run client on Unix machines.

start_mult_clients.sh: sample program script for mic + robot.

start_server.sh: script to create server process.


How to Run it
===============

Run the following on your command line:
  - git clone https://github.com/velazqua/480-pi-project.git

Compile all java files: (works in Unix)
  - javac -cp ".:./java-json.jar" *.java

To run the server:
  - ./start_server.sh (Unix)

Run sample client program:
  - ./start_client.sh (Unix) (Tested on Ubuntu. Works)
  - ./start_client.bat (Windows) (Untested)

Run client program that interacts with microphone and robot:
  - ./start_mult_clients.sh (Unix)

Note: IP is hardcoded into the sampleClient program. Our IP is 137.207.74.151 with port number 2024. 
Our Pi is connected to the CS-WL-2 network.
